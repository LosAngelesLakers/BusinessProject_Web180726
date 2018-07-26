<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>注册用户</h1>

<form action="account" method="get">
<input type="hidden" name="operation" value="1">
   <table align="center">
   <tr>
   <td>用户名</td>
   <td>
         <input type="text" name="username">
   </td>
   </tr>
   
    <tr>
    <td>密码</td>
    <td>
<input type="password" name="password">
    </td>
   </tr>
    <tr>
    <td>ip</td>
   <td><input type="text" name="ip"/></td>
   </tr>
    <tr>
   <td>性别</td>
   <td>
   <select name="sex">
<option value="男">男</option>
<option value="女">女</option>
</select>

   </td>
   </tr>
   
   
   <tr>
   <td><input type="submit" value="注册"/></td>
   </tr>
   </table>
</form>
</body>
</html>