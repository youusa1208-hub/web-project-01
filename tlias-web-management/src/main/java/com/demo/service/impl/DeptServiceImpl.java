package com.demo.service.impl;

import com.demo.mapper.DeptMapper;
import com.demo.pojo.Dept;
import com.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll() ;
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
//        1.基本属性の補完 - createTime、updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

//        2.Mapperインターフェースメソッドを呼び出してデータを挿入
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
//        1.基本属性の補完 - updateTime
        dept.setUpdateTime(LocalDateTime.now());

//        2.Mapperインターフェースメソッドを呼び出して部門を更新
        deptMapper.update(dept);
    }
}

