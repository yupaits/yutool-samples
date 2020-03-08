package com.yupaits.sample.post.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.sample.post.dto.PostCreate;
import com.yupaits.sample.post.dto.PostUpdate;
import com.yupaits.sample.post.query.PostQuery;
import com.yupaits.sample.post.service.PostService;
import com.yupaits.sample.post.vo.PostVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.orm.annotation.PageQueryDefault;
import com.yupaits.yutool.orm.support.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/page")
    public Result<IPage<PostVo>> getPostPage(@PageQueryDefault PageQuery<PostQuery> pageQuery,
                                             PostQuery query) throws BusinessException {
        pageQuery.setQuery(query);
        return postService.resultPage(pageQuery);
    }

    @ApiOperation("获取文章列表")
    @PostMapping("/list")
    public Result<List<PostVo>> getPostList(PostQuery postQuery) throws BusinessException {
        return postService.resultList(postQuery.buildNewLambdaQuery());
    }
}
