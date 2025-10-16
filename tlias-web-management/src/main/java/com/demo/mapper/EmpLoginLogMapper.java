package com.demo.mapper;

import com.demo.pojo.EmpLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpLoginLogMapper {


    @Insert("INSERT INTO emp_login_log (username, password, login_time, is_success, jwt, cost_time) " +
            "VALUES (#{username}, #{password}, #{loginTime}, #{isSuccess}, #{jwt}, #{costTime})")
    void insert(EmpLoginLog empLoginLog);
}
