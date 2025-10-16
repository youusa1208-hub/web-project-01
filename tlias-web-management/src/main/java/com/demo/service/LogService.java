package com.demo.service;

import com.demo.pojo.EmpLoginLog;
import com.demo.pojo.PageResult;

import java.time.LocalDate;

public interface LogService {
    PageResult<EmpLoginLog> page(Integer page, Integer pageSize);
}
