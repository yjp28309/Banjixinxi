<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Insert title here</title>
    <script src="js/jquery-1.8.3.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath }/Class/update">
    <input type="hidden" name="id" value="${classes.id}"/>
    班级名称:<input type="text" name="className" value="${classes.className}"><br>
    教室:<input type="text" name="classRoom" value="${classes.classRoom}"><br>
    成立时间:<input type="text" name="createTime" value="${classes.createTime}"><br>
    毕业时间:<input type="text" name="graduateTime" value="${classes.graduateTime}"><br>

    讲师:<select name="lecturerId">
            <c:forEach var="list" items="${teacherList}">
                <option <c:if test="${classes.lecturerId == list.id}"> selected="selected" </c:if>
                        value="${list.id}">${list.userName}</option>
            </c:forEach>
        </select><br>

    班主任:<select name="teacherId">
            <c:forEach var="list" items="${teacher2List}">
                <option <c:if test="${classes.teacherId == list.id}"> selected="selected" </c:if>
                        value="${list.id}">${list.userName}</option>
            </c:forEach>
        </select><br>

    <c:if test="${classes.status==0}">
        状态:<input type="radio" name="status" value="0" checked>未毕业
            <input type="radio" name="status" value="1">已毕业<br>
    </c:if>
    <c:if test="${classes.status==1}">
        状态:<input type="radio" name="status" value="0">未毕业
            <input type="radio" name="status" value="1" checked>已毕业<br>
    </c:if>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>
