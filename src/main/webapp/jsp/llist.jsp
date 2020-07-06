<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div>
<form action="${pageContext.request.contextPath }/Class/getList" method="get">
    班级名称：<input type="text" name="className" value="${className }">
    班级状态：<select name="status">
                <option value="">全部显示</option>
                <option value=0>未毕业</option>
                <option value=1>已毕业</option>
            </select>
    <%--分页--%>
    <input type="hidden" name="pageIndex" value="1" />
    <input value="查 询" type="submit">
    <a href="${pageContext.request.contextPath }/Class/teacherlist">新增班级</a>
</form>
</div>
<table cellpadding="0" cellspacing="0" border="1" align="center">
    <tr>
        <td width="5%">序号</td>
        <td width="10%">班级名称</td>
        <td width="10%">教室</td>
        <td width=10%">成立时间</td>
        "        <td width="10%">毕业时间</td>
        <td width="10%">状态</td>
        <td width="10%">操作</td>
    </tr>
    <c:forEach var="list" items="${list }" varStatus="status">
        <tr align="center">
            <td><span>${status.count }</span></td>
            <td>
                <a href="${pageContext.request.contextPath }/Class/view?id=${list.id}"><span>${list.className }</span></a>
            </td>

            <td><span>${list.classRoom}</span></td>
            <td><span><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd" /></span></td><br>
            <td><span><fmt:formatDate value="${list.graduateTime}" pattern="yyyy-MM-dd" /></span></td>
            <td>
                <c:if test="${list.status==0 }">未毕业</c:if>
                <c:if test="${list.status==1 }">已毕业</c:if>
            <td><span>
                <a href="${pageContext.request.contextPath}/Class/getClassesById?id=${list.id}">编辑</a>
                <a href="${pageContext.request.contextPath}/Class/del?id=${list.id}">删除</a>
            </span></td>
        </tr>
    </c:forEach>
</table>
<%--分页--%>
<input type="hidden" id="totalPageCount" value="${totalPageCount}" />
<c:import url="rollpage.jsp">
    <c:param name="totalCount" value="${totalCount}" />
    <c:param name="currentPageNo" value="${currentPageNo}" />
    <c:param name="totalPageCount" value="${totalPageCount}" />
</c:import>
<%--分页--%>
</body>
</html>