package com.demo.service.impl;

import com.demo.mapper.EmpLoginLogMapper;
import com.demo.mapper.OperateLogMapper;
import com.demo.pojo.EmpLoginLog;
import com.demo.pojo.OperateLog;
import com.demo.pojo.PageResult;
import com.demo.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private EmpLoginLogMapper empLoginLogMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> dataList = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) dataList;
        return new PageResult(p.getTotal(), p.getResult());
    }
}
