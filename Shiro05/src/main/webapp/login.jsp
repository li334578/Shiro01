<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2019/10/2
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>login</h1>
<form action="/user/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    <div style="color: red;font-size:12px">${msg}</div>
    <input type="submit" value="登录">
</form>
</body>
</html>
