package com.demo.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OperateLog {
    private Integer id; // ID
    private Integer operateEmpId; // 操作者ID
    private LocalDateTime operateTime; // 操作時間
    private String className; // 操作クラス名
    private String methodName; // 操作メソッド名
    private String methodParams; // 操作メソッド引数
    private String returnValue; // 操作メソッド戻り値
    private Long costTime; // 操作所要時間
}

