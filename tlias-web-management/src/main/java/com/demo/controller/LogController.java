package com.demo.controller;

import com.demo.pojo.EmpLoginLog;
import com.demo.pojo.PageResult;
import com.demo.pojo.Result;
import com.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * ログインログのページング検索
     */
    @GetMapping("/page")
    public Result page(
            @RequestParam(defaultValue = "1") Integer page ,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
