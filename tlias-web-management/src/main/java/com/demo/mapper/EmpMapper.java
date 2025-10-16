package com.demo.mapper;


import com.demo.pojo.Emp;
import com.demo.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 従業員情報
 */
@Mapper
public interface EmpMapper {

    // -------------------原始分页查询实现-----------------------------
    /**
     * 総レコード数を取得
     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id;")
//    public Long count();

    /**
     * ページング検索
     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);


//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件検索による従業員情報検索
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 従業員情報の追加
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")//生成された主キーを取得 -- 主キー返却
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date,dept_id,create_time,update_time)" +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 従業員IDによる従業員基本情報の一括削除
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 従業員IDによる従業員情報および従業員業務情報検索
     */
    Emp getById(Integer id);

    /**
     * 従業員IDによる従業員情報更新
     */
    void updateById(Emp emp);

    /**
     * 従業員職位別人数統計
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 従業員性別別人数統計
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * すべての従業員情報検索
     */
    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> findAll();

    /**
     * 部門IDによる従業員人数検索
     */
    @Select("select count(*) from emp where dept_id = #{deptId}")
    Integer countByDeptId(Integer deptId);

    /**
     * ユーザー名とパスワードによる従業員情報検索
     */
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}

