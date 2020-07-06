<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/view.js"></script>
    班级名称：${classes.className }<br>
    教室：${classes.classRoom }<br>
    成立时间：${classes.createTime }<br>
    毕业时间：${classes.graduateTime }<br>
    讲师：
        <c:forEach items="${teacherList}" var="tea">
            <c:if test="${classes.lecturerId==tea.id}">${tea.userName}
            </c:if>
        </c:forEach><br>
    班主任：<c:forEach items="${teacher2List}" var="tea">
        <c:if test="${classes.teacherId==tea.id}">${tea.userName}
        </c:if>
    </c:forEach><br>
    状态：<c:if test="${classes.status == 0}">未毕业
    </c:if>
    <c:if test="${classes.status == 1}">已毕业
    </c:if><br>
<input type="submit" value="返回"><br>
</body>
</html>
