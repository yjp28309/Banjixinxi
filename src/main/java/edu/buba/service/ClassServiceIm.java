package edu.buba.service;

import edu.buba.BaseDao;
import edu.buba.dao.ClassDao;
import edu.buba.dao.ClassDaoIm;
import edu.buba.pojo.Classes;
import edu.buba.pojo.Teacher;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
@Service("classService")
public class ClassServiceIm implements ClassService {
    //查看全部信息
    @Override
    public List<Classes> getClassList(String className, String status, int currentPageNo, int pageSize) {
        List<Classes> list = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        ClassDao dao = new ClassDaoIm();
        list = dao.getClassList(connection, className, status, currentPageNo, pageSize);
        BaseDao.closeResource(connection, null, null);
        return list;
    }
    //查询班级列表记录数
    public int getClassesCount(Classes classes){
        ClassDao classDao = new ClassDaoIm();
        Connection connection = null;

        int totalCount = 0;
        try {
            connection = BaseDao.getConnection();
            // 查询订单列表
            totalCount = classDao.getClassesCount(connection, classes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return totalCount;
    }
    //添加班级
    @Override
    public boolean addClass(Classes classes) {
        ClassDao dao = new ClassDaoIm();
        // TODO Auto-generated method stub
        Connection connection = null;
        int num = 0;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            // 添加订单
            num = dao.add(connection, classes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    //讲师下拉框
    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> list = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        ClassDao dao = new ClassDaoIm();
        list = dao.getTeacherList(connection);
        BaseDao.closeResource(connection, null, null);
        return list;
    }

    //班主任下拉框
    public List<Teacher> getTeacher2List() throws Exception {
        List<Teacher> list = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        ClassDao dao = new ClassDaoIm();
        list = dao.getTeacher2List(connection);
        BaseDao.closeResource(connection, null, null);
        return list;
    }

    //单查
    @Override
    public Classes getClassId(int id) throws Exception {
        ClassDao dao = new ClassDaoIm();
        Connection connection = null;
        Classes classes = new Classes();
        connection = BaseDao.getConnection();
        // 根据id查询信息
        classes = dao.getClassId(connection, id);
        BaseDao.closeResource(connection, null, null);
        return classes;
    }
    //修改
    @Override
    public boolean updateClass(Classes classes) {
        ClassDao dao = new ClassDaoIm();
        // TODO Auto-generated method stub
        Connection connection = null;
        int num = 0;
        boolean b = false;
        try {
            connection = BaseDao.getConnection();
            num = dao.update(connection, classes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        if (num > 0) {
            b = true;
        }
        return b;
    }
    //删除
    @Override
    public boolean delClass(int id) {
        ClassDao dao = new ClassDaoIm();
        // TODO Auto-generated method stub
        Connection connection = null;
        int num = 0;
        boolean b = false;
        try {
            connection = BaseDao.getConnection();
            num = dao.del(connection, id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        if (num > 0) {
            b = true;
        }
        return b;
    }
}
