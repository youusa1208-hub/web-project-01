package com.demo.controller;

import com.demo.anno.Log;
import com.demo.pojo.Dept;
import com.demo.pojo.Result;
import com.demo.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger  log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts", method = RequestMethod.GET) //method ：指定请求方式
    @GetMapping
    public Result list() {
//        System.out.println("查询全部的部门数据");
        log.info("すべての部門データを検索");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 部門の削除 - 方法1：HttpServletRequestを使用してリクエストパラメータを取得
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("删除部门：" + id);
//        return Result.success();
//    }

    /**
     * 部門の削除 - 方法2：@RequestParam
     * 注意事項 ： @RequestParamアノテーションを宣言すると、リクエスト時にそのパラメータは必須となり、渡さないとエラーになります。(デフォルトでrequiredがtrue)
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer deptID ){
//        System.out.println("删除部门：" + deptID);
//        return Result.success();
//    }
    /**
     * 部門の削除 - 方法3：@RequestParamを省略 (フロントエンドから渡されるリクエストパラメータ名とサーバーメソッドの仮引数名が一致する場合)【推奨】
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id ){
//        System.out.println("删除部门：" + id);
        log.info("部門削除：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 部門の追加
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("新增部门：" + dept);
        log.info("部門追加：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * IDによる部門検索
     */
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("查询部门：" + deptId);
//        return Result.success();
//    }

    /**
     * IDによる部門検索
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("查询部门：" + id);
        log.info("部門検索：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 部門の更新
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门：" + dept);
        log.info("部門更新：{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
