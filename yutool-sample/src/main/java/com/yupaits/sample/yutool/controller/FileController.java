package com.yupaits.sample.yutool.controller;

import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.file.core.DeleteTemplate;
import com.yupaits.yutool.file.core.DownloadTemplate;
import com.yupaits.yutool.file.core.UploadTemplate;
import com.yupaits.yutool.file.exception.DeleteException;
import com.yupaits.yutool.file.exception.DownloadException;
import com.yupaits.yutool.file.exception.UploadException;
import com.yupaits.yutool.file.support.DownloadProps;
import com.yupaits.yutool.file.support.UploadProps;
import com.yupaits.yutool.file.support.WatermarkType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 文件Controller
 * @author yupaits
 * @date 2019/8/2
 */
@RestController
@RequestMapping("/upload/{storeGroup}")
@Api(tags = "文件接口")
public class FileController {
    private static final String UPLOAD_PATH_PREFIX = "/upload";
    private static final String PATH_SEPARATOR = "/";

    private final UploadTemplate uploadTemplate;
    private final DownloadTemplate downloadTemplate;
    private final DeleteTemplate deleteTemplate;

    @Autowired
    public FileController(UploadTemplate uploadTemplate, DownloadTemplate downloadTemplate, DeleteTemplate deleteTemplate) {
        this.uploadTemplate = uploadTemplate;
        this.downloadTemplate = downloadTemplate;
        this.deleteTemplate = deleteTemplate;
    }

    @ApiOperation("上传文件并返回访问路径")
    @PostMapping("")
    public Result<String> upload(@RequestParam("file") MultipartFile file,
                                 @ApiParam(value = "文件存储分组", required = true) @PathVariable String storeGroup) throws UploadException {
        UploadProps uploadProps = new UploadProps();
        uploadProps.setStoreGroup(storeGroup);
        return uploadTemplate.resultUpload(file, uploadProps);
    }

    @ApiOperation("批量上传文件并返回访问路径")
    @PostMapping("/batch")
    public Result<Map<String, String>> uploadBatch(@RequestParam("files")List<MultipartFile> files,
                                                   @ApiParam(value = "文件存储分组", required = true) @PathVariable String storeGroup) throws UploadException {
        UploadProps uploadProps = new UploadProps();
        uploadProps.setStoreGroup(storeGroup);
        return uploadTemplate.resultUploadBatch(files, uploadProps);
    }

    @ApiOperation("上传图片并返回访问路径")
    @PostMapping("/image")
    public Result<String> uploadImage(@RequestParam("image") MultipartFile image,
                                      @ApiParam(value = "文件存储分组", required = true) @PathVariable String storeGroup,
                                      @ApiParam("是否保存缩略图") @RequestParam(required = false, defaultValue = "false") boolean thumb,
                                      @ApiParam("缩略图宽度") @RequestParam(required = false, defaultValue = "0") int width,
                                      @ApiParam("缩略图高度") @RequestParam(required = false, defaultValue = "0") int height,
                                      @ApiParam("图片缩放比例") @RequestParam(required = false, defaultValue = "1.0") double scale,
                                      @ApiParam("是否附带水印") @RequestParam(required = false, defaultValue = "false") boolean withWatermark,
                                      @ApiParam("水印类型") @RequestParam(required = false) WatermarkType watermarkType,
                                      @ApiParam("水印文件路径") @RequestParam(required = false) String watermarkPic,
                                      @ApiParam("水印文字") @RequestParam(required = false) String watermarkText,
                                      @ApiParam("水印透明度（取值范围0-1，0表示完全透明）") @RequestParam(required = false, defaultValue = "1.0") float watermarkOpacity,
                                      @ApiParam("水印位置") @RequestParam(required = false) Positions watermarkPos) throws UploadException {
        UploadProps uploadProps = UploadProps.builder()
                .storeGroup(storeGroup)
                .thumb(thumb)
                .width(width)
                .height(height)
                .scale(scale)
                .withWatermark(withWatermark)
                .watermarkType(watermarkType)
                .watermarkPic(watermarkPic)
                .watermarkText(watermarkText)
                .watermarkOpacity(watermarkOpacity)
                .watermarkPos(watermarkPos)
                .build();
        return uploadTemplate.resultUploadImage(image, uploadProps);
    }

    @ApiOperation("批量上传图片并返回访问路径")
    @PostMapping("/image/batch")
    public Result<Map<String, String>> uploadBatchImage(@RequestParam("images")List<MultipartFile> images,
                                                        @ApiParam(value = "文件存储分组", required = true) @PathVariable String storeGroup,
                                                        @ApiParam("是否保存缩略图") @RequestParam(required = false, defaultValue = "false") boolean thumb,
                                                        @ApiParam("缩略图宽度") @RequestParam(required = false, defaultValue = "0") int width,
                                                        @ApiParam("缩略图高度") @RequestParam(required = false, defaultValue = "0") int height,
                                                        @ApiParam("图片缩放比例") @RequestParam(required = false, defaultValue = "1.0") double scale,
                                                        @ApiParam("是否附带水印") @RequestParam(required = false, defaultValue = "false") boolean withWatermark,
                                                        @ApiParam("水印类型") @RequestParam(required = false) WatermarkType watermarkType,
                                                        @ApiParam("水印文件路径") @RequestParam(required = false) String watermarkPic,
                                                        @ApiParam("水印文字") @RequestParam(required = false) String watermarkText,
                                                        @ApiParam("水印透明度（取值范围0-1，0表示完全透明）") @RequestParam(required = false, defaultValue = "1.0") float watermarkOpacity,
                                                        @ApiParam("水印位置") @RequestParam(required = false) Positions watermarkPos) throws UploadException {
        UploadProps uploadProps = UploadProps.builder()
                .storeGroup(storeGroup)
                .thumb(thumb)
                .width(width)
                .height(height)
                .scale(scale)
                .withWatermark(withWatermark)
                .watermarkType(watermarkType)
                .watermarkPic(watermarkPic)
                .watermarkText(watermarkText)
                .watermarkOpacity(watermarkOpacity)
                .watermarkPos(watermarkPos)
                .build();
        return uploadTemplate.resultUploadBatchImage(images, uploadProps);
    }

    @ApiOperation("下载文件")
    @GetMapping("/**")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @ApiParam("是否下载缩略图") @RequestParam(required = false, defaultValue = "false") boolean thumb,
                         @ApiParam("缩略图宽度") @RequestParam(required = false, defaultValue = "0") int width,
                         @ApiParam("缩略图高度") @RequestParam(required = false, defaultValue = "0") int height,
                         @ApiParam("图片质量（压缩比，如0.8表示压缩比为80%）") @RequestParam(required = false, defaultValue = "1.0") float quality,
                         @ApiParam("图片缩放比例") @RequestParam(required = false, defaultValue = "1.0") double scale,
                         @ApiParam("是否附带水印") @RequestParam(required = false, defaultValue = "false") boolean withWatermark,
                         @ApiParam("水印类型") @RequestParam(required = false) WatermarkType watermarkType,
                         @ApiParam("水印文件路径") @RequestParam(required = false) String watermarkPic,
                         @ApiParam("水印文字") @RequestParam(required = false) String watermarkText,
                         @ApiParam("水印透明度（取值范围0-1，0表示完全透明）") @RequestParam(required = false, defaultValue = "1.0") float watermarkOpacity,
                         @ApiParam("水印位置") @RequestParam(required = false) Positions watermarkPos) throws IOException, DownloadException {
        DownloadProps downloadProps = DownloadProps.builder()
                .thumb(thumb)
                .width(width)
                .height(height)
                .quality(quality)
                .scale(scale)
                .withWatermark(withWatermark)
                .watermarkType(watermarkType)
                .watermarkPic(watermarkPic)
                .watermarkText(watermarkText)
                .watermarkOpacity(watermarkOpacity)
                .watermarkPos(watermarkPos)
                .build();
        String fullPath = StringUtils.substringAfter(StringUtils.substringAfter(request.getRequestURI(), UPLOAD_PATH_PREFIX), PATH_SEPARATOR);
        downloadTemplate.downloadFile(response, fullPath, downloadProps);
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/**")
    public Result delete(HttpServletRequest request) throws DeleteException {
        String fullPath = StringUtils.substringAfter(StringUtils.substringAfter(request.getRequestURI(), UPLOAD_PATH_PREFIX), PATH_SEPARATOR);
        deleteTemplate.deleteFile(fullPath);
        return ResultWrapper.success();
    }

}
