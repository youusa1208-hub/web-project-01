package com.demo.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Emp {
    private Integer id; // ID、主キー
    private String username; // ユーザー名
    private String password; // パスワード
    private String name; // 氏名
    private Integer gender; // 性別, 1:男, 2:女
    private String phone; // 携帯電話番号
    private Integer job; // 職位, 1:担任教師,2:講師,3:学生工作主管,4:教学研究主管,5:カウンセラー
    private Integer salary; // 給与
    private String image; // アバター
    private LocalDate entryDate; // 入社日
    private Integer deptId; // 関連する部門ID
    private LocalDateTime createTime; // 作成日時
    private LocalDateTime updateTime; // 更新日時

    //    部門名をカプセル化
    private String deptName;// 部門名
    //    勤務経験情報をカプセル化
    private List<EmpExpr> exprList;
}

