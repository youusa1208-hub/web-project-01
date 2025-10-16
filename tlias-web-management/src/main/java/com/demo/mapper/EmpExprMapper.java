package com.demo.mapper;

import com.demo.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 従業員給与経験
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 従業員業務情報の一括保存
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 従業員IDによる従業員業務経験情報の一括削除
     */
    void deleteByEmpIds(List<Integer> empIds);
}
