package com.demo.service;

import com.demo.pojo.Clazz;
import com.demo.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {

    /**
     * ページング検索
     */
    PageResult<Clazz> page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * クラス情報の追加
     */
    void save(Clazz clazz);

    /**
     * クラス情報の削除
     */
    void delete(Integer id);

    /**
     * IDによるクラス情報検索
     */
    Clazz getInfo(Integer id);

    /**
     * クラス情報の更新
     */
    void update(Clazz clazz);

    /**
     * すべてのクラス情報検索
     */
    List<Clazz> findAll();
}

