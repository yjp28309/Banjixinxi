package edu.buba.service;

import edu.buba.pojo.Classes;
import edu.buba.pojo.Teacher;

import java.util.List;

public interface ClassService {
    //查看全部信息
    public List<Classes> getClassList(String className,String status, int currentPageNo, int pageSize);
    //查询班级列表记录数
    public int getClassesCount(Classes classes);

    //添加班级
    public boolean addClass(Classes classes);
    //讲师下拉框
    public List<Teacher> getTeacherList() throws Exception;
    //班主任下拉框
    public List<Teacher> getTeacher2List() throws Exception;
    //单查
    public Classes getClassId(int id) throws Exception;
    //修改
    public boolean updateClass(Classes classes);
    //删除
    public boolean delClass(int id);
}
