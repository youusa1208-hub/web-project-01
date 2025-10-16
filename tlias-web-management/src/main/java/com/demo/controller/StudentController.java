package com.demo.controller;

import com.demo.pojo.PageResult;
import com.demo.pojo.Result;
import com.demo.pojo.Student;
import com.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * ページング検索
     */
    @GetMapping
    public Result page(String name,Integer degree,Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("ページング検索：{}，{}，{}，{}，{}",name,degree,clazzId,page,pageSize);
        PageResult<Student> pageResult = studentService.page(name,degree,clazzId,page,pageSize);
        return Result.success(pageResult);
    }

    /**
     * 生徒情報の追加
     */
    @PostMapping
    public Result sava(@RequestBody Student student){
        log.info("生徒情報追加：{}",student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 生徒情報の削除
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("生徒削除：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * IDによる学生情報検索
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("生徒ID検索：{}",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 生徒情報の更新
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("生徒更新：{}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 違反処理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id , @PathVariable Integer score){
        studentService.violationHandle(id, score);
        return Result.success();
    }
}

