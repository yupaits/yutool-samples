package com.yupaits.sample.post.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupaits.sample.post.dto.PostCreate;
import com.yupaits.sample.post.dto.PostUpdate;
import com.yupaits.sample.post.model.Post;
import com.yupaits.sample.post.service.PostService;
import com.yupaits.sample.post.vo.PostVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.orm.support.AggregateProps;
import com.yupaits.yutool.orm.support.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@RestController
@RequestMapping("/post")
@Api(tags = "文章接口")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("新增文章")
    @PostMapping("")
    public Result addPost(@RequestBody PostCreate postCreate) throws BusinessException {
        return postService.resultSaveDto(postCreate);
    }

    @ApiOperation("修改文章")
    @PutMapping("/{postId}")
    public Result updatePost(@RequestBody PostUpdate postUpdate) throws BusinessException {
        return postService.resultSaveDto(postUpdate);
    }

    @ApiOperation("根据ID获取文章")
    @GetMapping("/{postId}")
    public Result<PostVo> getPost(@PathVariable Long postId) throws BusinessException {
        return postService.resultById(postId);
    }

    @ApiOperation("获取文章分页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10", dataType = "int", paramType = "query")})
    @PostMapping("/page")
    public Result<IPage<PostVo>> getPostPage(@RequestParam(required = false, defaultValue = "1") int page,
                                             @RequestParam(required = false, defaultValue = "10") int size,
                                             @RequestBody(required = false) PageQuery pageQuery) {
        Page<Post> pager = new Page<>(page, size);
        pageQuery.collate();
        if (CollectionUtils.isNotEmpty(pageQuery.getOrders())) {
            pager.setOrders(pageQuery.getOrders());
        }
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        if (MapUtils.isNotEmpty(pageQuery.getQuery())) {
            pageQuery.getQuery().forEach((key, value) -> {

            });
        }
        if (CollectionUtils.isNotEmpty(pageQuery.getAggregates())) {
            AggregateProps aggregateProps = new AggregateProps();
            aggregateProps.setAggregates(pageQuery.getAggregates());
            return postService.resultPage(pager, wrapper, aggregateProps);
        }
        return postService.resultPage(pager, wrapper);
    }

    @ApiOperation("获取文章列表")
    @PostMapping("/list")
    public Result<List<PostVo>> getPostList(@RequestBody(required = false) Map<String, Object> query) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        if (MapUtils.isNotEmpty(query)) {
            query.forEach((key, value) -> {

            });
        }
        return postService.resultList(wrapper);
    }
}
