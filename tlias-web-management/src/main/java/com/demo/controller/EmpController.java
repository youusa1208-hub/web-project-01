package com.demo.controller;

import com.demo.pojo.Emp;
import com.demo.pojo.EmpQueryParam;
import com.demo.pojo.PageResult;
import com.demo.pojo.Result;
import com.demo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 従業員管理Controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * ページング検索
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("ページング検索：{}，{}，{}，{}，{}，{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("ページング検索：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 従業員の追加
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("従業員を追加：{}",emp);
        empService.save(emp);
        return Result.success();

    }


    /**
     * 従業員の削除 - 配列
     */
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("従業員を削除：{}", Arrays.toString(ids));
//        return Result.success();


    /**
     * 従業員の削除 - List
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("従業員を削除：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * IDによる従業員情報検索
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("従業員IDを検索：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("従業員を更新：{}",emp);
        empService.update(emp);
        return Result.success();

    }

    /**
     * すべての従業員を検索
     */
    @GetMapping("/list")
    public Result list(){
        log.info("すべての従業員データを検索");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
}
