package com.demo.mapper;

import com.demo.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 学生情報の動的検索
     */
    List<Student> list(String name, Integer degree, Integer clazzId);

    /**
     * 学生の追加
     */
    @Insert("insert into student(name, no, gender, phone,id_card, is_college, address, degree, graduation_date,clazz_id, create_time, update_time) VALUES " +
            "(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 学生情報の削除
     */
    void delete(List<Integer> ids);

    /**
     * IDによる学生情報検索
     */
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 学生情報の更新
     */
    void updateById(Student student);

    /**
     * 違紀処理
     */
    @Update("update student set violation_count = violation_count + 1 , violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    void violationHandle(Integer id, Integer score);

    /**
     * クラス別人数統計
     */
    @Select("select c.name cname , count(s.id) scount from clazz c  left join student s on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    List<Map<String,Object>> getStudentCount();

    /**
     * 学生学歴データの統計
     */
    @MapKey("name")
    List<Map> countStudentDegreeData();

    /**
     * 学生情報の統計
     */
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);

    /**
     * 一括挿入
     */
    void insertBatch(List<Student> studentList);

}

