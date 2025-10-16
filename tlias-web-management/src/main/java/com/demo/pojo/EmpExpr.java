package com.demo.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 勤務経験
 */
@Data
public class EmpExpr {
    private Integer id; // ID
    private Integer empId; // 従業員ID
    private LocalDate begin; // 開始日時
    private LocalDate end; // 終了日時
    private String company; // 会社名
    private String job; // 職位
}

