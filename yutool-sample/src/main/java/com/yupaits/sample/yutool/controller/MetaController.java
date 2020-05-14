package com.yupaits.sample.yutool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.result.ResultCode;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.metadata.dto.*;
import com.yupaits.yutool.metadata.model.MetaFieldSecure;
import com.yupaits.yutool.metadata.query.MetaObjectQuery;
import com.yupaits.yutool.metadata.service.*;
import com.yupaits.yutool.metadata.vo.*;
import com.yupaits.yutool.orm.annotation.PageQueryDefault;
import com.yupaits.yutool.orm.support.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 自定义对象Controller
 * @author yupaits
 * @date 2020/5/13
 */
@Api(tags = "自定义对象接口")
@RestController
@RequestMapping("/meta")
public class MetaController {

    private final MetaObjectService metaObjectService;
    private final MetaObjFieldService metaObjFieldService;
    private final MetaFieldSecureService metaFieldSecureService;
    private final MetaJudgeRuleService metaJudgeRuleService;
    private final MetaFieldTrackService metaFieldTrackService;
    private final GlobalOptionsService globalOptionsService;

    @Autowired
    public MetaController(MetaObjectService metaObjectService, MetaObjFieldService metaObjFieldService,
                          MetaFieldSecureService metaFieldSecureService, MetaJudgeRuleService metaJudgeRuleService,
                          MetaFieldTrackService metaFieldTrackService, GlobalOptionsService globalOptionsService) {
        this.metaObjectService = metaObjectService;
        this.metaObjFieldService = metaObjFieldService;
        this.metaFieldSecureService = metaFieldSecureService;
        this.metaJudgeRuleService = metaJudgeRuleService;
        this.metaFieldTrackService = metaFieldTrackService;
        this.globalOptionsService = globalOptionsService;
    }

    @ApiOperation("同步自定义对象信息")
    @GetMapping("/sync")
    public Result sync() {
        metaObjectService.sync();
        return ResultWrapper.success();
    }

    @ApiOperation("获取自定义对象分页信息")
    @GetMapping("/obj/page")
    public Result<IPage<MetaObjectVo>> pageMetaObject(@PageQueryDefault PageQuery<MetaObjectQuery> pageQuery,
                                                      MetaObjectQuery query) throws BusinessException {
        pageQuery.setQuery(query);
        return metaObjectService.pageMetaObject(pageQuery);
    }

    @ApiOperation(("获取自定义对象列表"))
    @GetMapping("/obj/list")
    public Result<List<MetaObjectVo>> listMetaObject(MetaObjectQuery query) throws BusinessException {
        return ResultWrapper.success(metaObjectService.listMetaObject(query));
    }

    @ApiOperation("保存自定义对象")
    @PostMapping("/obj")
    public Result saveMetaObject(@RequestBody MetaObjectDto metaObjectDto) throws BusinessException {
        return metaObjectService.saveMetaObject(metaObjectDto) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SAVE_FAIL);
    }

    @ApiOperation("删除自定义对象")
    @DeleteMapping("/obj/{objId}")
    public Result deleteMetaObject(@ApiParam(value = "自定义对象ID", required = true) @PathVariable Long objId) throws BusinessException {
        return metaObjectService.deleteMetaObject(objId) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.DELETE_FAIL);
    }

    @ApiOperation("设置对象启用跟踪字段历史")
    @PutMapping("/obj/{objId}/track")
    public Result setObjTrack(@ApiParam(value = "自定义对象ID", required = true) @PathVariable Long objId,
                                    @ApiParam(value = "是否启用跟踪", defaultValue = "false") @RequestParam(value = "tracked", defaultValue = "false") Boolean tracked) throws BusinessException {
        return metaObjectService.setFieldsTrackEnabled(objId, tracked) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SET_FAIL);
    }

    @ApiOperation("设置启用跟踪历史的字段")
    @PutMapping("/obj/{objId}/fields/track")
    public Result setObjFieldsTrack(@ApiParam(value = "自定义对象ID", required = true) @PathVariable Long objId,
                                    @ApiParam(value = "启用跟踪的字段ID集合") @RequestBody Collection<Long> fieldIds) throws BusinessException {
        metaObjFieldService.setFieldsTrack(objId, fieldIds);
        return ResultWrapper.success();
    }

    @ApiOperation("获取指定自定义对象的字段信息列表")
    @GetMapping("/obj/{objId}/fields")
    public Result<List<MetaObjFieldVo>> listObjFields(@ApiParam(value = "自定义对象ID", required = true) @PathVariable Long objId) {
        return ResultWrapper.success(metaObjFieldService.listVoByObjId(objId));
    }

    @ApiOperation("保存自定义字段")
    @PostMapping("/field")
    public Result saveMetaObjField(@RequestBody MetaObjFieldDto metaObjFieldDto) throws BusinessException {
        return metaObjFieldService.saveObjField(metaObjFieldDto) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SAVE_FAIL);
    }

    @ApiOperation("删除自定义字段")
    @DeleteMapping("/field/{fieldId}")
    public Result deleteMetaObjField(@ApiParam(value = "自定义字段ID", required = true) @PathVariable Long fieldId) {
        return metaObjFieldService.deleteObjField(fieldId) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.DELETE_FAIL);
    }

    @ApiOperation("设置启用字段")
    @PutMapping("/field/{fieldId}/enabled")
    public Result setFieldEnabled(@ApiParam(value = "自定义字段ID", required = true) @PathVariable Long fieldId,
                                  @ApiParam(value = "启用标记", defaultValue = "false") @RequestParam(value = "enabled", defaultValue = "false") Boolean enabled) throws BusinessException {
        return metaObjFieldService.setFieldEnabled(fieldId, enabled) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SET_FAIL);
    }

    @ApiOperation("获取指定自定义字段安全性列表")
    @GetMapping("/field/{fieldId}/secure")
    public Result<List<MetaFieldSecure>> listMetaFieldSecure(@ApiParam(value = "自定义字段ID", required = true) @PathVariable Long fieldId) {
        return ResultWrapper.success(metaFieldSecureService.listByFieldId(fieldId));
    }

    @ApiOperation("设置字段安全性")
    @PutMapping("/field/{fieldId}/secure")
    public Result setFieldSecure(@ApiParam(value = "自定义字段ID", required = true) @PathVariable Long fieldId,
                                 @ApiParam(value = "字段安全性配置集合") @RequestBody Collection<FieldSecureDto> secureInfo) throws BusinessException {
        metaFieldSecureService.setFieldSecure(fieldId, secureInfo);
        return ResultWrapper.success();
    }

    @ApiOperation("获取指定对象指定字段的跟踪历史记录列表")
    @GetMapping("/obj/{objId}/pk/{pk}/field/{fieldId}/track/list")
    public Result<List<MetaFieldTrackVo>> listFieldTrack(@ApiParam(value = "自定义对象ID", required = true) @PathVariable Long objId,
                                                         @ApiParam(value = "目标对象主键值", required = true) @PathVariable Long pk,
                                                         @ApiParam(value = "自定义字段Id", required = true) @PathVariable Long fieldId) {
        return ResultWrapper.success(metaFieldTrackService.listFieldTrackVo(objId, pk, fieldId));
    }

    @ApiOperation("审核字段更新记录")
    @PutMapping("/field/track/{trackId}/audit")
    public Result auditFieldTrack(@ApiParam(value = "字段跟踪记录ID", required = true) @PathVariable Long trackId,
                                  @ApiParam(value = "是否审核通过", defaultValue = "false") @RequestParam(value = "approved", defaultValue = "false") Boolean approved) throws BusinessException {
        return metaFieldTrackService.auditFieldTrack(trackId, approved) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.AUDIT_FAIL);
    }

    @ApiOperation("获取指定自定义对象的验证规则列表")
    @GetMapping("/obj/{objId}/judge/rule/list")
    public Result<List<MetaJudgeRuleVo>> listObjJudgeRule(@ApiParam(value = "自定义对象ID", required = true) @PathVariable Long objId) {
        return ResultWrapper.success(metaJudgeRuleService.listVoByObjId(objId));
    }

    @ApiOperation("保存验证规则")
    @PostMapping("/judge/rule")
    public Result saveMetaJudgeRule(@RequestBody MetaJudgeRuleDto metaJudgeRuleDto) throws BusinessException {
        return metaJudgeRuleService.saveMetaJudgeRule(metaJudgeRuleDto) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SAVE_FAIL);
    }

    @ApiOperation("删除验证规则")
    @DeleteMapping("/judge/rule/{ruleId}")
    public Result deleteJudgeRule(@ApiParam(value = "验证规则ID", required = true) @PathVariable Long ruleId) {
        return metaJudgeRuleService.deleteMetaJudgeRule(ruleId) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.DELETE_FAIL);
    }

    @ApiOperation("设置启用验证规则")
    @PutMapping("/judge/rule/{ruleId}/enabled")
    public Result setJudgeRuleEnabled(@ApiParam(value = "验证规则ID", required = true) @PathVariable Long ruleId,
                                      @ApiParam(value = "启用标记", defaultValue = "false") @RequestParam(value = "enabled", defaultValue = "false") Boolean enabled) throws BusinessException {
        return metaJudgeRuleService.setJudgeRuleEnabled(ruleId, enabled) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SET_FAIL);
    }

    @ApiOperation("获取所有全局选项列表")
    @GetMapping("/global/options/list")
    public Result<List<GlobalOptionsVo>> listAllGlobalOptions() {
        return ResultWrapper.success(globalOptionsService.listAllGlobalOptions());
    }

    @ApiOperation("根据ID获取指定的全局选项")
    @GetMapping("/global/options/{id}")
    public Result<GlobalOptionsVo> getGlobalOptionsById(@ApiParam(value = "全局选项ID", required = true) @PathVariable Long id) throws BusinessException {
        return ResultWrapper.success(globalOptionsService.getGlobalOptionsById(id));
    }

    @ApiOperation("保存全局选项")
    @PostMapping("/global/options")
    public Result saveGlobalOptions(@RequestBody GlobalOptionsDto globalOptionsDto) throws BusinessException {
        return globalOptionsService.saveGlobalOptions(globalOptionsDto) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.SAVE_FAIL);
    }

    @ApiOperation("删除全局选项")
    @DeleteMapping("/global/options/{id}")
    public Result deleteGlobalOptions(@ApiParam(value = "全局选项ID", required = true) @PathVariable Long id) throws BusinessException {
        return globalOptionsService.deleteGlobalOptionsById(id) ? ResultWrapper.success() : ResultWrapper.fail(ResultCode.DELETE_FAIL);
    }
}
