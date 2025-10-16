package com.demo.service;

import com.demo.pojo.ClazzCountOption;
import com.demo.pojo.PageResult;
import com.demo.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * ページング検索
     */
    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    /**
     * 学生の追加
     */
    void save(Student student);

    /**
     * 学生の削除
     */
    void delete(List<Integer> ids);

    /**
     * 学生情報の取得
     */
    Student getInfo(Integer id);

    /**
     * 学生情報の更新
     */
    void update(Student student);

    /**
     * 違紀処理
     */
    void violationHandle(Integer id, Integer score);

    /**
     * 学歴別人数統計
     */
    List<Map> getStudentDegreeData();

    /**
     * クラス別人数統計
     */
    ClazzCountOption getStudentCountData();
}




