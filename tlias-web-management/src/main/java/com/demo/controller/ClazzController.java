package com.demo.controller;


import com.demo.pojo.Clazz;
import com.demo.pojo.PageResult;
import com.demo.pojo.Result;
import com.demo.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * ページング検索
     */
    @GetMapping
    public Result page(String name ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("ページング検索：{}，{}，{}，{}，{}",name,begin,end,page,pageSize);
        PageResult<Clazz> pageResult = clazzService.page(name,begin,end,page,pageSize);
        return Result.success(pageResult);

    }

    /**
     * クラスの追加
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("クラス追加：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * クラス情報の削除
     */
    @DeleteMapping("/{id}")
    public Result delete(Integer id) {
        log.info("クラス情報削除：{}", id);
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * IDによるクラス情報の検索
     */

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("クラス情報ID：{}",id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * クラス情報の更新
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("クラス情報更新：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * すべてのクラス情報の検索
     */

    @GetMapping("/list")
    public Result list(){
        log.info("すべてのクラス情報");
        List<Clazz> clazzsList = clazzService.findAll();
        return Result.success(clazzsList);
    }

}
