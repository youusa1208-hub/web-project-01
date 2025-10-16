package com.demo.service;

import com.demo.pojo.ClazzCountOption;
import com.demo.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 従業員職位別人数を取得
     */
    JobOption getEmpJobData();

    /**
     * 従業員性別別人数を取得
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 学生学歴別人数を取得
     */
    List<Map> getStudentDegreeData();

    /**
     * クラス別人数を取得
     */
    ClazzCountOption getStudentCountData();
}

