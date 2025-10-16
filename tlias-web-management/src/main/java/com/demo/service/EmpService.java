package com.demo.service;

import com.demo.pojo.Emp;
import com.demo.pojo.EmpQueryParam;
import com.demo.pojo.LoginInfo;
import com.demo.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * ページング検索
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /**
     * 従業員情報の追加
     */
    void save(Emp emp) throws Exception;


    /**
     * 従業員情報の一括削除
     */
    void delete(List<Integer> ids);


    /**
     * 従業員IDによる従業員情報検索
     */
    Emp getInfo(Integer id);


    /**
     * 従業員情報の更新
     */
    void update(Emp emp);

    /**
     * すべての従業員情報検索
     */
    List<Emp> list();


    /**
     * ログインメソッド
     */
    LoginInfo login(Emp emp);


//    /**
//     * 分页查询
//     */
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);
}


