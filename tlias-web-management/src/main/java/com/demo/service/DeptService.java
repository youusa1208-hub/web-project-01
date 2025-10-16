package com.demo.service;

import com.demo.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * すべての部門を検索
     * @return
     */
    List<Dept> findAll();

    /**
     * IDによる部門削除
     */
    void deleteById(Integer id);

    /**
     * 部門の追加
     */
    void add(Dept dept);

    /**
     * IDによる部門検索
     */
    Dept getById(Integer id);

    /**
     * 部門の更新
     */
    void update(Dept dept);
}

