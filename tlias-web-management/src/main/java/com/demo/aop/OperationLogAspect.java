package com.demo.aop;

import com.demo.mapper.OperateLogMapper;
import com.demo.pojo.OperateLog;
import com.demo.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.demo.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ターゲットメソッドを実行
        Object result = joinPoint.proceed();
        // 処理時間の計算
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // ログエンティティの構築
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId()); // ここでは実際の状況に応じて現在のユーザーIDを取得する必要があります
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);

        // ログの保存
        log.info("操作ログを記録: {}", olog);
        operateLogMapper.insert(olog);

        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
