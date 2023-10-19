package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.dto.TbBlogDTO;
import com.hncpu.blog.entity.*;
import com.hncpu.blog.mapper.TbBlogCountMapper;
import com.hncpu.blog.mapper.TbBlogMapper;
import com.hncpu.blog.mapper.TbClassIfyCountMapper;
import com.hncpu.blog.service.TbBlogService;
import com.hncpu.blog.service.TbBlogTagsRelationService;
import com.hncpu.blog.service.TbTagsService;
import com.hncpu.blog.exception.BlogException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TbBlogServiceImpl extends ServiceImpl<TbBlogMapper, TbBlogEntity> implements TbBlogService  {
    @Autowired
    private TbBlogTagsRelationService tbBlogTagsRelationService;
    @Autowired
    private TbTagsService tbTagsService;
    @Autowired
    private TbBlogCountMapper tbBlogCountMapper;
    @Autowired
    private TbClassIfyCountMapper tbClassIfyCountMapper;
    /** 分页查询文章列表 并查询标签列表 */
    @Override
    public List<TbBlogDTO> selectUserInfoByGtFraction(Integer pageNum, Integer pageSize) {
        Page<TbBlogEntity> BlogPage=new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<TbBlogEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbBlogEntity::getBlogStatus,0);
        wrapper.orderByDesc(TbBlogEntity::getCreateTime);
        baseMapper.selectPage(BlogPage,wrapper);
        List<TbBlogEntity> tbBlogEntities = BlogPage.getRecords();
        return tbBlogEntities.stream().map((item) -> {
            TbBlogDTO tbBlogDTO = new TbBlogDTO();
            BeanUtils.copyProperties(item,tbBlogDTO);
            List<TbTagsEntity> tagsByBlogId = tbBlogTagsRelationService.getTagsByBlogId(item.getBlogId());
            tbBlogDTO.setTbTagsEntityList(tagsByBlogId);
            return tbBlogDTO;
        }).collect(Collectors.toList());
    }
    /** 通过ClassIfyId 来查询文章列表 */
    @Override
    public List<TbBlogEntity> queryAllByClassIfyId(Integer Id) {
        if (Id!=null){
            TbClassIfyCountEntity tbClassIfyCountEntity=new TbClassIfyCountEntity();
            tbClassIfyCountEntity.setClassifyId(Id);
            tbClassIfyCountEntity.setCreateTime(new Date());
            tbClassIfyCountMapper.insert(tbClassIfyCountEntity);
        }
        LambdaQueryWrapper<TbBlogEntity> Wrapper=new LambdaQueryWrapper<>();
        Wrapper.eq(TbBlogEntity::getBlogStatus,0);
        Wrapper.eq(TbBlogEntity::getBlogClassifyId,Id);
        Wrapper.orderByDesc(TbBlogEntity::getCreateTime);
        return baseMapper.selectList(Wrapper);
    }
    /** 通过文章ID 查询文章 */
    @Override
    public TbBlogDTO queryBlogByBlogId(Integer Id) {
        LambdaQueryWrapper<TbBlogCountEntity> countWrapper=new LambdaQueryWrapper();
        countWrapper.eq(TbBlogCountEntity::getBlogId,Id);
        TbBlogCountEntity one = tbBlogCountMapper.selectOne(countWrapper);
        if (one!=null){
            int count=one.getCountNumber()+1;
            one.setCountNumber(count);
            tbBlogCountMapper.updateById(one);
        }else{
            TbBlogCountEntity tbBlogCountEntity=new TbBlogCountEntity();
            tbBlogCountEntity.setBlogId(Id);
            tbBlogCountEntity.setCountNumber(1);
            tbBlogCountMapper.insert(tbBlogCountEntity);
        }
        TbBlogDTO tbBlogDTO=new TbBlogDTO();
        LambdaQueryWrapper<TbBlogEntity> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbBlogEntity::getBlogId,Id);
        TbBlogEntity tbBlogEntity = baseMapper.selectOne(lambdaQueryWrapper);
        List<TbTagsEntity> tagsByBlogId = tbBlogTagsRelationService.getTagsByBlogId(tbBlogEntity.getBlogId());
        BeanUtils.copyProperties(tbBlogEntity,tbBlogDTO);
        tbBlogDTO.setTbTagsEntityList(tagsByBlogId);
        return tbBlogDTO;
    }
    /** 通过TagsId 和文章Id 查询文章列表 */
    @Override
    public List<TbBlogEntity> queryBlogByTagsId(Integer TagsId){
        LambdaQueryWrapper<TbBlogTagsRelationEntity> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(TbBlogTagsRelationEntity::getTagsId,TagsId);
        List<TbBlogTagsRelationEntity> list = tbBlogTagsRelationService.list(lambdaQueryWrapper);
        return list.stream().map((item) -> {
            LambdaQueryWrapper<TbBlogEntity> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(TbBlogEntity::getBlogId, item.getBlogId());
            lambdaQueryWrapper1.eq(TbBlogEntity::getBlogStatus,0);
            return baseMapper.selectOne(lambdaQueryWrapper1);
        }).filter(Objects::nonNull).sorted(Comparator.comparing(TbBlogEntity::getCreateTime)).collect(Collectors.toList());
    }
    /** 修改文章信息 */
    @Override
    public int updateBlogById(TbBlogDTO tbBlogEntity) throws BlogException {
        tbBlogEntity.setUpdateTime(new Date());
        if (tbBlogEntity.getBlogId()==null){
            throw new BlogException("参数异常");
        }
        //先清除所有标签
        LambdaQueryWrapper<TbBlogTagsRelationEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbBlogTagsRelationEntity::getBlogId,tbBlogEntity.getBlogId());
        tbBlogTagsRelationService.remove(wrapper);
        Iterator<TbTagsEntity> iterator = tbBlogEntity.getTbTagsEntityList().iterator();
        while (iterator.hasNext()){
            TbTagsEntity Tags = iterator.next();
            LambdaQueryWrapper<TbTagsEntity> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TbTagsEntity::getTagsId,Tags.getTagsId());
            long count = tbTagsService.count(lambdaQueryWrapper);
            if (count<0){
                throw new BlogException("标签ID异常");
            }
            TbBlogTagsRelationEntity tbBlogTagsRelationEntity=new TbBlogTagsRelationEntity();
            tbBlogTagsRelationEntity.setBlogId(tbBlogEntity.getBlogId());
            tbBlogTagsRelationEntity.setTagsId(Tags.getTagsId());
            tbBlogTagsRelationService.save(tbBlogTagsRelationEntity);
        }
        return baseMapper.updateEssayById(tbBlogEntity.getBlogId(),
                tbBlogEntity.getBlogTitle(),
                tbBlogEntity.getBlogContent(),
                tbBlogEntity.getBlogClassifyId(),
                tbBlogEntity.getBlogStatus(),
                tbBlogEntity.getIsDeleted(),
                tbBlogEntity.getUpdateTime());
    }
    /** 删除文章 */
    @Override
    public int deleteBlogById(Integer id) throws BlogException {
        if (id==null){
            throw new BlogException("参数非法");
        }
        return baseMapper.deleteById(id);
    }
    /** 后台查询文章列表 */
    @Override
    public Page<TbBlogEntity> selectEssayList(int pageNum, int pageSize) {
        //设置分页
        Page<TbBlogEntity> BlogPage=new Page<>(pageNum,pageSize);
        //设置匹配条件
        LambdaQueryWrapper<TbBlogEntity> wrapper=new LambdaQueryWrapper<>();
        //设置以创建时间倒序
        wrapper.orderByDesc(TbBlogEntity::getCreateTime);
        //查询为Page对象
        baseMapper.queryAllEssayList(BlogPage,wrapper);
        //遍历对象设置参数
        BlogPage.setRecords(BlogPage.getRecords().stream().map((item) -> {
            TbBlogDTO tbBlogDTO = new TbBlogDTO();
            BeanUtils.copyProperties(item, tbBlogDTO);
            List<TbTagsEntity> tagsByBlogId = tbBlogTagsRelationService.getTagsByBlogId(item.getBlogId());
            tbBlogDTO.setTbTagsEntityList(tagsByBlogId);
            return tbBlogDTO;
        }).collect(Collectors.toList()));
        return BlogPage;
    }
    /** 后台查询文章 */
    @Override
    public TbBlogDTO queryEssayByBlogId(Integer Id) {
        TbBlogEntity tbBlogEntity = baseMapper.queryEssayByBlogId(Id);
        TbBlogDTO tbBlogDTO=new TbBlogDTO();
        BeanUtils.copyProperties(tbBlogEntity,tbBlogDTO);
        List<TbTagsEntity> tagsByBlogId = tbBlogTagsRelationService.getTagsByBlogId(tbBlogDTO.getBlogId());
        tbBlogDTO.setTbTagsEntityList(tagsByBlogId);
        return tbBlogDTO;
    }


    /** 新增文章 */
    @Override
    public int insertEssay(TbBlogDTO tbBlogEntity) throws BlogException {
        tbBlogEntity.setCreateTime(new Date());
        tbBlogEntity.setUpdateTime(new Date());
        int insertCount = baseMapper.insert(tbBlogEntity);
        Iterator<TbTagsEntity> iterator = tbBlogEntity.getTbTagsEntityList().iterator();
        while (iterator.hasNext()){
            TbTagsEntity Tags = iterator.next();
            LambdaQueryWrapper<TbTagsEntity> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TbTagsEntity::getTagsId,Tags.getTagsId());
            long count = tbTagsService.count(lambdaQueryWrapper);
            if (count<0){
                throw new BlogException("标签ID异常");
            }
            TbBlogTagsRelationEntity tbBlogTagsRelationEntity=new TbBlogTagsRelationEntity();
            tbBlogTagsRelationEntity.setBlogId(tbBlogEntity.getBlogId());
            tbBlogTagsRelationEntity.setTagsId(Tags.getTagsId());
            tbBlogTagsRelationService.save(tbBlogTagsRelationEntity);
        }
        return insertCount;
    }
    /** 批量删除文章 */
    @Override
    public int deleteEssayByIds(List<Integer> ids) {
        return baseMapper.deleteBatchIds(ids);
    }


}
