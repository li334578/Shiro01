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
<h1>login</h1>
<shiro:authenticated>
    登录成功，当前用户名是：<shiro:principal/>
    <a href="/student.jsp">学生页面</a>
    <a href="/teacher.jsp">老师页面</a>
    <a href="/main.jsp">主页面</a>
</shiro:authenticated>
<shiro:notAuthenticated>
<form action="/user/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    <div style="color: red;font-size:12px">${msg}</div>
    <input type="submit" value="登录">
</form>
</shiro:notAuthenticated>
</body>
</html>
