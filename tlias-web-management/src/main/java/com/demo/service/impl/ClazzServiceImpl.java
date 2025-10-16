package com.demo.service.impl;

import com.demo.mapper.ClazzMapper;
import com.demo.pojo.*;
import com.demo.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //1. ページングパラメータを設定
        PageHelper.startPage(page,pageSize);

        //2. クエリを実行
        List<Clazz> dataList = clazzMapper.list(name, begin, end);

        //3. クエリ結果を解析し、カプセル化
        Page<Clazz> p = (Page<Clazz>) dataList;

        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Clazz clazz) {
        //1. クラスの基本情報を保存
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public void delete(Integer id) {
        //1. クラスの基本情報を一括削除
        clazzMapper.deleteByIds(id);
    }

    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}










