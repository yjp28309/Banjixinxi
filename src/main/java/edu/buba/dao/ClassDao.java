package edu.buba.dao;


import edu.buba.pojo.Classes;
import edu.buba.pojo.Teacher;

import java.sql.Connection;
import java.util.List;

public interface ClassDao {
    //查看全部信息
    public List<Classes> getClassList(Connection connection,String className,String status, int currentPageNo, int pageSize);
    //查询班级列表记录数
    public int getClassesCount(Connection connection, Classes classes) throws Exception;

    //添加班级
    public int add(Connection connection, Classes classes) throws Exception;
    //讲师下拉框
    public List<Teacher> getTeacherList(Connection connection) throws Exception;
    //班主任下拉框
    public List<Teacher> getTeacher2List(Connection connection) throws Exception;
    //单查
    public Classes getClassId(Connection connection, int id) throws Exception;
    //修改
    public int update(Connection connection, Classes classes) throws Exception;
    //删除
    public int del(Connection connection,int id) throws Exception;
}
