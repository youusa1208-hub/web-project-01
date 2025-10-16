
package com.demo.aop;

import com.demo.mapper.EmpLoginLogMapper;
import com.demo.pojo.Emp;
import com.demo.pojo.EmpLoginLog;
import com.demo.pojo.Result;
import com.demo.pojo.LoginInfo;
import com.demo.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Slf4j
@Component
public class LoginLogAspect {

    @Autowired
    private EmpLoginLogMapper empLoginLogMapper;

    @Around("execution(* com.demo.controller.LoginController.login(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //開始時間を記録
        Long begin = System.currentTimeMillis();

        //結果を強制的にResult型にキャスト（Loginメソッドは最終的にResult型のデータを返す）
        Result result = (Result) pjp.proceed();

        Long  end = System.currentTimeMillis();
        Long cost = end - begin;

        //ログインメソッドに渡された引数empを取得
        Object[] args = pjp.getArgs();
        Emp emp = (Emp) args[0];
        //ログインメソッドの戻り値のData部分を強制的にLoginInfo型にキャスト
        LoginInfo loginInfo = (LoginInfo) result.getData();

        EmpLoginLog empLoginLog = new EmpLoginLog();
        empLoginLog.setLoginTime(LocalDateTime.now());//ログイン時間

        //ログイン成功かどうか
        if (loginInfo == null){
            empLoginLog.setUsername(emp.getUsername());
            empLoginLog.setPassword(emp.getPassword());
            empLoginLog.setIsSuccess((short) 0);//ログイン失敗
            empLoginLog.setCostTime(cost);//ログイン所要時間
            empLoginLogMapper.insert(empLoginLog);//データベースに挿入
            return result;
        }
        // ログイン成功、トークンを取得
        String token = loginInfo.getToken();
        empLoginLog.setUsername(emp.getUsername());
        empLoginLog.setPassword(emp.getPassword());
        empLoginLog.setIsSuccess((short) 1);//ログイン成功
        empLoginLog.setJwt(token);//ログイン成功後のトークン
        empLoginLog.setCostTime(cost);//ログイン所要時間

        //データベースに挿入
        empLoginLogMapper.insert(empLoginLog);//データベースに挿入
        return result;

    }
}