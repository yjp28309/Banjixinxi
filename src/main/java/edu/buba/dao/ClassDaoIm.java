package edu.buba.dao;

import com.mysql.jdbc.StringUtils;
import edu.buba.BaseDao;
import edu.buba.pojo.Classes;
import edu.buba.pojo.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoIm implements ClassDao {
    //查看全部信息
    @Override
    public List<Classes> getClassList(Connection connection, String className, String status, int currentPageNo, int pageSize) {
        List<Classes> list = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (connection != null) {
            StringBuffer sql = new StringBuffer("select * from classes");
            sql.append(" where flag=0 ");
            // 班级名称如果不为null，并且不为空，拼接模糊查询sql
            if (className != null && !"".equals(className)) {
                sql.append(" and className like '%" + className + "%'");
            }
            if (status != null && !"".equals(status)) {
                sql.append(" and status=" + status);
            }
            //分页
            // 声明条件参数列表
            List<Object> paramList = new ArrayList();
            sql.append("limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            paramList.add(currentPageNo);
            paramList.add(pageSize);
            Object[] params = paramList.toArray();
            //分页
            try {
                rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
                while (rs.next()) {
                    Classes classes = new Classes();
                    classes.setId(rs.getInt("id"));
                    classes.setClassName(rs.getString("className"));
                    classes.setClassRoom(rs.getString("classRoom"));
                    classes.setCreateTime(rs.getDate("createTime"));
                    classes.setGraduateTime(rs.getDate("graduateTime"));
                    classes.setStatus(rs.getString("status"));
                    list.add(classes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }
    //查询班级列表记录数
    public int getClassesCount(Connection connection, Classes classes) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int totalCount = 0;
        if (null != connection) {

            StringBuffer sql = new StringBuffer("select count(*) totalCount from classes where flag=0");
            // 班级名称如果不为null，并且不为空，拼接模糊查询sql
            if (classes.getClassName() != null && !"".equals(classes.getClassName())) {
                sql.append(" and classes.getClassName() like '%" + classes.getClassName() + "%'");
            }
            if (classes.getStatus() != null && !"".equals(classes.getStatus())) {
                sql.append(" and classes.getStatus()=" + classes.getStatus());
            }

            rs = BaseDao.execute2(connection, pstm, rs, sql.toString());
            if (rs.next()) {
                totalCount = rs.getInt("totalCount");
            }
            BaseDao.closeResource(null, pstm, rs);
        }

        return totalCount;
    }

    //添加班级
    @Override
    public int add(Connection connection, Classes classes) throws Exception {
        int num = 0;
        PreparedStatement pstm = null;
        String sql = "insert into classes(className,classRoom,createTime,graduateTime,lecturerId,teacherId,status,flag) values(?,?,?,?,?,?,?,0)";
        Object[] params = {
                classes.getClassName(),
                classes.getClassRoom(),
                classes.getCreateTime(),
                classes.getGraduateTime(),
                classes.getLecturerId(),
                classes.getTeacherId(),
                classes.getStatus(),
        };
        num = BaseDao.execute(connection, pstm, sql, params);
        BaseDao.closeResource(null, pstm, null);
        return num;
    }

    //讲师下拉框
    @Override
    public List<Teacher> getTeacherList(Connection connection) throws Exception {
        List<Teacher> list = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (connection != null) {
            String sql = "select * from teacher t,user u where position=1 and t.userId=u.id";
            rs = BaseDao.execute2(connection, pstm, rs, sql);
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setUserId(rs.getInt("userId"));
                teacher.setPosition(rs.getString("position"));
                teacher.setUserName(rs.getString("userName"));
                list.add(teacher);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }
    //班主任下拉框
    @Override
    public List<Teacher> getTeacher2List(Connection connection) throws Exception {
        List<Teacher> list = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (connection != null) {
            String sql = "select * from teacher t,user u where position=2 and t.userId=u.id";
            rs = BaseDao.execute2(connection, pstm, rs, sql);
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setUserId(rs.getInt("userId"));
                teacher.setPosition(rs.getString("position"));
                teacher.setUserName(rs.getString("userName"));
                list.add(teacher);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }
    //单查
    @Override
    public Classes getClassId(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Classes classes = null;
        if (null != connection) {
//            StringBuffer sql = new StringBuffer("select c.*,b.username,a.username from classes c,");
//            sql.append("(select teacher.id,`user`.username from teacher,`user` where teacher.userId = `user`.id) b,");
//            sql.append("(select teacher.id,`user`.username from teacher,`user` where teacher.userId = `user`.id) a ");
//            sql.append("where c.teacherId = b.id and c.lecturerId = a.id and c.id=?");
            String sql="select * from classes where id=?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                classes =new Classes();
                classes.setId(rs.getInt("id"));
                classes.setClassName(rs.getString("className"));
                classes.setClassRoom(rs.getString("classRoom"));
                classes.setCreateTime(rs.getDate("createTime"));
                classes.setGraduateTime(rs.getDate("graduateTime"));
                classes.setLecturerId(rs.getInt("lecturerId"));
                classes.setTeacherId(rs.getInt("teacherId"));
//                classes.setLecturerName(rs.getString("a.username"));
//                classes.setTeacherName(rs.getString("b.username"));
//                classes.setUserName(rs.getString("userName"));
                classes.setStatus(rs.getString("status"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return classes;
    }
    //修改
    @Override
    public int update(Connection connection, Classes classes) throws Exception {
        int num = 0;
        PreparedStatement pstm = null;
        String sql = "update classes set className=?,classRoom=?,createTime=?,graduateTime=?,lecturerId=?,teacherId=?,status=? where id=?";
        Object[] params = {
                classes.getClassName(),
                classes.getClassRoom(),
                classes.getCreateTime(),
                classes.getGraduateTime(),
                classes.getLecturerId(),
                classes.getTeacherId(),
                classes.getStatus(),
                classes.getId()
        };
        num = BaseDao.execute(connection, pstm, sql, params);
        BaseDao.closeResource(null, pstm, null);
        return num;
    }
    //删除
    @Override
    public int del(Connection connection,int id) throws Exception {
        int num = 0;
        PreparedStatement pstm = null;
        String sql = "update classes set flag=1 where id=?";
        Object[] params = {id};
        num = BaseDao.execute(connection, pstm, sql, params);
        BaseDao.closeResource(null, pstm, null);
        return num;
    }
}