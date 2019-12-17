package com.yupaits.sample.oauth2.client.controller;

import com.yupaits.sample.post.dto.PostCreate;
import com.yupaits.sample.post.dto.PostUpdate;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.utils.ValidateUtils;
import com.yupaits.yutool.orm.support.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yupaits
 * @date 2019/8/26
 */
@RestController
@RequestMapping("/post")
@Api(tags = "文章接口")
public class PostController {
    public static final String POST_SERVER_URL = "http://localhost:8092/post";

    private final OAuth2RestTemplate restTemplate;

    @Autowired
    public PostController(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ApiOperation("新增文章")
    @PostMapping("")
    public Result addPost(@RequestBody PostCreate postCreate) {
        return restTemplate.postForObject(POST_SERVER_URL, postCreate, Result.class);
    }

    @ApiOperation("修改文章")
    @PutMapping("/{postId}")
    public Result updatePost(@PathVariable Long postId, @RequestBody PostUpdate postUpdate) {
        String url = POST_SERVER_URL + "/{postId}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<PostUpdate> entity = new HttpEntity<>(postUpdate, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, entity, Result.class, ValidateUtils.idValid(postId) ? postId : postUpdate.getId()).getBody();
    }

    @ApiOperation("根据ID获取文章")
    @GetMapping("/{postId}")
    public Result getPost(@PathVariable Long postId) {
        String url = POST_SERVER_URL + "/{postId}";
        return restTemplate.getForObject(url, Result.class);
    }

    @ApiOperation("获取文章分页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10", dataType = "int", paramType = "query")})
    @PostMapping("/page")
    public Result getPostPage(@RequestParam(required = false, defaultValue = "1") int page,
                                             @RequestParam(required = false, defaultValue = "10") int size,
                                             @RequestBody(required = false) PageQuery pageQuery) {
        String url = POST_SERVER_URL + "/page?page={page}&size={size}";
        return restTemplate.postForObject(url, pageQuery, Result.class, page, size);
    }

    @ApiOperation("获取文章列表")
    @PostMapping("/list")
    public Result getPostList(@RequestBody(required = false) Map<String, Object> query) {
        String url = POST_SERVER_URL + "/list";
        return restTemplate.postForObject(url, query, Result.class);
    }
}
