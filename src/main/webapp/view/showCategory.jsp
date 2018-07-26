<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>查询类别</h1>
<form action="category" method="get">
<tr>
<td>类别ID</td>
<td><input type="text" name="id"></td>
<td><input type="submit" value="查询"></td>
</tr>
<table>
    <tr>
       <th>类别id</th>
       <th>父类id</th>
       <th>类别名称</th>
       <th>类别状态</th>
       <th>排序编号</th>
       <th>创建时间</th>
       <th>更新时间</th> 
       <th>类别操作</th>                                           
    </tr>
    
   <!--  
        items:要迭代的list集合
        var:变量的名字 
    -->
    <c:forEach items="${pageModel.data}" var="category">
    <tr>
       <td>${category.id}</td>
       <td>${category.parent_id}</td>
       <td>${category.name}</td>
       <td>${category.status}</td>
       <td>${category.sort_order}</td>
       <td>${category.creat_time}</td>
       <td>${category.update_time}</td>
       <td>
       <a href="category?operation=3&id=${category.id}">修改</a>
       <a href="category?operation=4&id=${category.id}">删除</a> 
       <td>                                         
    </tr>
    
    </c:forEach>
    
</table>
<c:forEach begin="1" end="${pageModel.totalPage}" step="1" var="pageNo">
         <c:choose>
         <c:when test="${pageModel.currentPage==pageNo}">
         <a style="color: red" href="category?operation=2&pageNo=${pageNo}&pageSize=5">${pageNo}</a>
         </c:when>
         
         <c:when test="${pageModel.currentPage!=pageNo}">
          <a href="category?operation=2&pageNo=${pageNo}&pageSize=5">${pageNo}</a>
         </c:when>
         
         </c:choose>
   </c:forEach>
</form>
</body>
</html>