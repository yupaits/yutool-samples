package com.yupaits.sample.post.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.sample.post.model.Post;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.orm.base.BaseDto;
import com.yupaits.yutool.orm.base.BaseQuery;
import com.yupaits.yutool.orm.base.BaseVo;
import com.yupaits.yutool.orm.base.IBaseService;
import com.yupaits.yutool.orm.support.PageQuery;

import java.util.List;

/**
 * @author yupaits
 * @date 2019/8/22
 */
public interface PostService extends IBaseService {

    /**
     * 获取分页数据
     * @param page 当前页码
     * @param size 每页数量
     * @param pageQuery 分页查询对象
     * @param <Vo> Vo类型
     * @return 分页数据
     */
    <Vo extends BaseVo, Q extends BaseQuery<Post>> Result<IPage<Vo>> resultPage(int page, int size, PageQuery<Q> pageQuery);

    /**
     * 根据QueryWrapper获取Vo对象列表
     * @param wrapper QueryWrapper对象
     * @param <Vo> Vo类型
     * @return Vo对象列表
     */
    <Vo extends BaseVo> Result<List<Vo>> resultList(Wrapper<Post> wrapper);

    /**
     * 根据ID获取Vo对象
     * @param id ID
     * @param <Vo> Vo类型
     * @return Vo对象
     * @throws BusinessException 抛出BusinessException
     */
    <Vo extends BaseVo> Result<Vo> resultById(Long id) throws BusinessException;

    /**
     * 保存Dto对象
     * @param dto Dto对象
     * @param <Dto> Dto类型
     * @return 保存结果
     * @throws BusinessException 抛出BusinessException
     */
    <Dto extends BaseDto> Result resultSaveDto(Dto dto) throws BusinessException;
}
