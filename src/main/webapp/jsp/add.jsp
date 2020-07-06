<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yjp
  Date: 2019/5/27
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath }/Class/add">
        班级名称：<input type="text" name="className" class="text" value=""><br>
        教室：<input type="text" name="classRoom" value=""><br>
        成立时间：<input type="text" name="createTime" value=""><br>
        毕业时间：<input type="text" name="graduateTime" value=""><br>
        讲师：<select name="lecturerId" id="lecturerId">
            <option value="0">==请选择==</option>
            <c:forEach items="${teacherList}" var="tea">
                <option value="${tea.id}"> ${tea.userName}</option>
            </c:forEach>
        </select>
        班主任：<select name="teacherId" id="teacherId">
            <option value="0">==请选择==</option>
            <c:forEach items="${teacher2List}" var="tea">
                <option value="${tea.id}"> ${tea.userName}</option>
            </c:forEach>
        </select><br>
        状态：<input type="radio" name="status" value="0">未毕业
        <input type="radio" name="status" value="1">已毕业<br>
        <input type="submit" value="保存">
    </form>
</body>
</html>
