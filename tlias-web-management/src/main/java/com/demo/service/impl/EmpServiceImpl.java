package com.demo.service.impl;

import com.demo.mapper.EmpExprMapper;
import com.demo.mapper.EmpMapper;
import com.demo.pojo.*;
import com.demo.service.EmpLogService;
import com.demo.service.EmpService;
import com.demo.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /**
     * 原始ページング検索
     * @param page ページ番号
     * @param pageSize 1ページあたりのレコード数
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1. Mapperインターフェースを呼び出し、総レコード数を検索
//        Long total = empMapper.count();
//
//        //2. Mapperインターフェースを呼び出し、結果リストを検索
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(page, pageSize);
//
//        //3. 結果をカプセル化、PageResult
//        return new PageResult<Emp>(total,rows);


    /**
     * PageHelperページング検索
     * @param page ページ番号
     * @param pageSize 1ページあたりのレコード数
     * 注意事項：
     *         1.定義されたSQL文の末尾にはセミコロンを付けない ;
     *         2.PageHelperは直後の最初のクエリ文のみページング処理を行う
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1. ページングパラメータを設定
//        PageHelper.startPage(page,pageSize);
//
//        //2. クエリを実行
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
//
//        //3. クエリ結果を解析し、カプセル化
//        Page<Emp> p = (Page<Emp>) empList;
//
//        return new PageResult<Emp>(p.getTotal(),p.getResult());


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. ページングパラメータを設定
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        //2. クエリを実行
        List<Emp> empList = empMapper.list(empQueryParam);

        //3. クエリ結果を解析し、カプセル化
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) //トランザクション管理 - デフォルトでは実行時例外RuntimeExceptionのみロールバック
    @Override
    public void save(Emp emp) throws Exception {
        try {
//        1. 従業員の基本情報を保存
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

//        2. 従業員の業務情報を保存
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                // コレクションを走査し、empIdに値を設定
                exprList.forEach(empExpr ->{
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //操作ログを記録
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"従業員追加:" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1. 従業員の基本情報を一括削除
        empMapper.deleteByIds(ids);


        //2. 従業員の業務情報を一括削除
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
//        1. IDにより従業員の基本情報を更新
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

//        2. IDにより従業員の業務経験情報を更新
//        2.1 従業員IDにより既存の業務経験を削除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

//        2.2 従業員IDにより新しい業務経験を追加
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // コレクションを走査し、empIdに値を設定
            exprList.forEach(empExpr ->{
                empExpr.setEmpId(emp.getId());
            });

            empExprMapper.insertBatch(exprList);
        }

    }


    @Override
    public List<Emp> list() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1. Mapperインターフェースを呼び出し、ユーザー名とパスワードにより従業員情報を検索
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2. 判定：この従業員が存在するかどうかを判定し、存在する場合はログイン成功情報を組み立てる
        if (e != null){
            log.info("ログイン成功、従業員情報：{}",e);
            //JWTトークンを生成
            Map<String,Object> claims =new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }


        //3. 存在しない場合、ログイン失敗情報を返却 nullを返す
        return null;
    }
}