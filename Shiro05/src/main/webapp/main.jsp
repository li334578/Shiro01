<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2019/10/2
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>main</h1>
<shiro:authenticated>
    登录成功，当前用户名是：<shiro:principal/>
</shiro:authenticated>
<a href="/user/logout">退出登录</a>
<shiro:hasRole name="stu">
    <a href="/student.jsp">学生页面</a>
</shiro:hasRole>
<shiro:hasRole name="tea">
    <a href="/teacher.jsp">老师页面</a>
</shiro:hasRole>
<shiro:hasAnyRoles name="tea,stu">
    <a href="/list.jsp">list页面</a>
</shiro:hasAnyRoles>
</body>
</html>
