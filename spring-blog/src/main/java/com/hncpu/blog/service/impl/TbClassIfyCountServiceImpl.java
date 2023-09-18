package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.dto.TbBlogCountByYearDTO;
import com.hncpu.blog.dto.TbBlogCountYearParam;
import com.hncpu.blog.entity.TbClassIfyCountEntity;
import com.hncpu.blog.entity.TbClassifyEntity;
import com.hncpu.blog.mapper.TbClassIfyCountMapper;
import com.hncpu.blog.service.TbClassIfyCountService;
import com.hncpu.blog.service.TbClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbClassIfyCountServiceImpl extends ServiceImpl<TbClassIfyCountMapper, TbClassIfyCountEntity> implements TbClassIfyCountService {
    @Autowired
    private TbClassifyService tbClassifyService;
    @Override
    public TbBlogCountByYearDTO getBlogCountByYear() {
        //创建返回对象
        TbBlogCountByYearDTO tbBlogCountByYearDTO=new TbBlogCountByYearDTO();
        //返回对象初始化
        tbBlogCountByYearDTO.setSeries(new TbBlogCountYearParam());
        tbBlogCountByYearDTO.setYAxisData(new ArrayList<>());
        tbBlogCountByYearDTO.getSeries().setData(new ArrayList<>());
        //获取年份
        LocalDate today=LocalDate.now();
        //当前年份
        String year = String.valueOf(today.getYear());
        //设置年份
        tbBlogCountByYearDTO.getSeries().setName(year);
        //设置类型
        tbBlogCountByYearDTO.getSeries().setType("bar");
        //获取所有归档
        List<TbClassifyEntity> classIfyList = tbClassifyService.list();;
        for (TbClassifyEntity classIfy:classIfyList){
            //设置返回对象获取归档名称
            tbBlogCountByYearDTO.getYAxisData().add(classIfy.getClassifyName());
            //获取归档ID进行查询
            Integer classifyId = classIfy.getClassifyId();
            LambdaQueryWrapper<TbClassIfyCountEntity> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(TbClassIfyCountEntity::getClassifyId,classifyId);
            //获取 归档Id 下所有统计数据
            List<TbClassIfyCountEntity> tbClassIfyCountList = baseMapper.selectList(wrapper);
            //根据标签 处理每年访问数量
            Integer classIfyCount=0;
            for (TbClassIfyCountEntity ClassIfyCount:tbClassIfyCountList){
                SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
                String currentYear = getYearFormat.format(ClassIfyCount.getCreateTime());
                if (year.equals(currentYear)){
                    ++classIfyCount;
                }
            }
            //设置当前归档下每年访问数量
            tbBlogCountByYearDTO.getSeries().getData().add(classIfyCount);
        }
        return tbBlogCountByYearDTO;
    }
}
