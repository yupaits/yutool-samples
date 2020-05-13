package com.yupaits.sample.yutool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.metadata.query.MetaObjectQuery;
import com.yupaits.yutool.metadata.service.MetaObjectService;
import com.yupaits.yutool.metadata.vo.MetaObjectVo;
import com.yupaits.yutool.orm.annotation.PageQueryDefault;
import com.yupaits.yutool.orm.support.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public MetaController(MetaObjectService metaObjectService) {
        this.metaObjectService = metaObjectService;
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
}
