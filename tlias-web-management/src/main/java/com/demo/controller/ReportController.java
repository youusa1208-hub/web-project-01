package com.demo.controller;


import com.demo.pojo.ClazzCountOption;
import com.demo.pojo.JobOption;
import com.demo.pojo.Result;
import com.demo.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 従業員の職位別人数統計
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("従業員の職位別人数統計");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);

    }

    /**
     * 従業員の性別別人数統計
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("従業員の性別別人数統計");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }


    /**
     * 生徒の学歴情報統計
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("生徒の学歴情報統計");
        List<Map> dataList = reportService.getStudentDegreeData();
        return Result.success(dataList);
    }


    /**
     * クラス別人数統計
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("クラス別人数統計");
        ClazzCountOption clazzCountOption = reportService.getStudentCountData();
        return Result.success(clazzCountOption);
    }

}

