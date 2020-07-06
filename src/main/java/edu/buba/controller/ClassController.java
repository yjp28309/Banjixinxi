package edu.buba.controller;

import edu.buba.pojo.Classes;
import edu.buba.pojo.Teacher;
import edu.buba.service.ClassService;
import edu.buba.service.ClassServiceIm;
import edu.buba.tools.Constants;
import edu.buba.tools.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value="/Class")
public class ClassController {
    @Autowired
    @Qualifier(value = "classService")
    private ClassService classService;

    //查看
    @RequestMapping(value="/getList",produces = "text/html;charset=UTF-8")
    public String getClassList(HttpServletRequest request, HttpServletResponse response) {
//        ClassService classService=new ClassServiceIm();
        Classes classes=new Classes();
        String classname=request.getParameter("className");
        String status=request.getParameter("status");

        /** ------分页------*/
        // 取得页数
        String pageIndex = request.getParameter("pageIndex");
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;

        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
//                response.sendRedirect("error.jsp");
                return "../error";
            }
        }
        //总数量（表）
        int totalCount	= classService.getClassesCount(classes);
        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        /** ------分页------*/
        ClassService service=new ClassServiceIm();
        List<Classes> claList = service.getClassList(classname, status,currentPageNo, pageSize);
        request.setAttribute("status",status);
        request.setAttribute("className", classname);
        request.setAttribute("list", claList);
        /**-----分页----*/
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        /**-----分页----*/
        return "llist";
    }
    //添加班级
    @RequestMapping(value="/add")
    public String addClass(Classes classes,HttpServletRequest request,HttpServletResponse response) throws Exception {
//        ClassService classService=new ClassServiceIm();
        classService.addClass(classes);
        return "redirect:/Class/getList";
    }

    //讲师 教师下拉框
    @RequestMapping(value="/teacherlist")
    public String getTeacherList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ClassService classService=new ClassServiceIm();
        List<Teacher> teacherList = classService.getTeacherList();
        List<Teacher> teacher2List = classService.getTeacher2List();
        request.setAttribute("teacherList",teacherList);
        request.setAttribute("teacher2List", teacher2List);
        return "add";
    }
    //通过id查看
    @RequestMapping("/view")
    public String getClassesview(HttpServletRequest request,int id) throws Exception {
//        ClassService classService = new ClassServiceIm();
        // 根据id查询信息
        Classes classes = classService.getClassId(id);
        request.setAttribute("classes", classes);
        List<Teacher> teacherList = classService.getTeacherList();
        List<Teacher> teacher2List = classService.getTeacher2List();
        request.setAttribute("teacherList",teacherList);
        request.setAttribute("teacher2List", teacher2List);
        return "view";
    }
    //修改用的通过id查看
    @RequestMapping("/getClassesById")
    public String getClassesById(HttpServletRequest request,int id) throws Exception {
//        ClassService classService = new ClassServiceIm();
        // 根据id查询信息
        Classes classes = classService.getClassId(id);
        request.setAttribute("classes", classes);
        List<Teacher> teacherList = classService.getTeacherList();
        List<Teacher> teacher2List = classService.getTeacher2List();
        request.setAttribute("teacherList",teacherList);
        request.setAttribute("teacher2List", teacher2List);
        return "undate";
    }
    //修改
    @RequestMapping("/update")
    public String updateClasses(Classes classes,HttpServletRequest request) throws Exception {
//        ClassService classService = new ClassServiceIm();
        classService.updateClass(classes);
        List<Teacher> teacherList = classService.getTeacherList();
        List<Teacher> teacher2List = classService.getTeacher2List();
        request.setAttribute("teacherList",teacherList);
        request.setAttribute("teacher2List", teacher2List);
        return "redirect:/Class/getList";
    }
    //删除
    @RequestMapping("/del")
    public String delClasses(int id,HttpServletRequest request) throws Exception {
//        ClassService classService = new ClassServiceIm();
        classService.delClass(id);
        return "redirect:/Class/getList";
    }
}
