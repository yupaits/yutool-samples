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
                                 @PathVariable String storeGroup) throws UploadException {
        UploadProps uploadProps = new UploadProps();
        uploadProps.setStoreGroup(storeGroup);
        return uploadTemplate.resultUpload(file, uploadProps);
    }

    @ApiOperation("批量上传文件并返回访问路径")
    @PostMapping("/batch")
    public Result<Map<String, String>> uploadBatch(@RequestParam("files")List<MultipartFile> files,
                                                   @PathVariable String storeGroup) throws UploadException {
        UploadProps uploadProps = new UploadProps();
        uploadProps.setStoreGroup(storeGroup);
        return uploadTemplate.resultUploadBatch(files, uploadProps);
    }

    @ApiOperation("上传图片并返回访问路径")
    @PostMapping("/image")
    public Result<String> uploadImage(@RequestParam("image") MultipartFile image,
                                      @PathVariable String storeGroup,
                                      @RequestParam(required = false, defaultValue = "false") boolean thumb,
                                      @RequestParam(required = false, defaultValue = "0") int width,
                                      @RequestParam(required = false, defaultValue = "0") int height,
                                      @RequestParam(required = false, defaultValue = "100") double percent) throws UploadException {
        UploadProps uploadProps = new UploadProps(storeGroup, thumb, width, height, percent);
        return uploadTemplate.resultUploadImage(image, uploadProps);
    }

    @ApiOperation("批量上传图片并返回访问路径")
    @PostMapping("/image/batch")
    public Result<Map<String, String>> uploadBatchImage(@RequestParam("images")List<MultipartFile> images,
                                                 @PathVariable String storeGroup,
                                                 @RequestParam(required = false, defaultValue = "false") boolean thumb,
                                                 @RequestParam(required = false, defaultValue = "0") int width,
                                                 @RequestParam(required = false, defaultValue = "0") int height,
                                                 @RequestParam(required = false, defaultValue = "100") double percent) throws UploadException {
        UploadProps uploadProps = new UploadProps(storeGroup, thumb, width, height, percent);
        return uploadTemplate.resultUploadBatchImage(images, uploadProps);
    }

    @ApiOperation("下载文件")
    @GetMapping("/**")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = false, defaultValue = "false") boolean thumb,
                         @RequestParam(required = false, defaultValue = "0") int width,
                         @RequestParam(required = false, defaultValue = "0") int height,
                         @RequestParam(required = false, defaultValue = "1.0") float quality,
                         @RequestParam(required = false, defaultValue = "1.0") double scale,
                         @RequestParam(required = false, defaultValue = "false") boolean withWatermark,
                         @RequestParam(required = false) WatermarkType watermarkType,
                         @RequestParam(required = false) String watermarkPic,
                         @RequestParam(required = false) String watermarkText,
                         @RequestParam(required = false, defaultValue = "1.0") float watermarkOpacity,
                         @RequestParam(required = false) Positions watermarkPos) throws IOException, DownloadException {
        DownloadProps downloadProps = new DownloadProps(thumb, width, height, quality, scale, withWatermark,
                watermarkType, watermarkPic, watermarkText, watermarkOpacity, watermarkPos);
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
