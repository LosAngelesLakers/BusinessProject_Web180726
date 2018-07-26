<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>查询商品</h1>
<form action="product" method="get">
<table>
    <tr>
       <th>商品id</th>
       <th>类别名称</th>
       <th>商品名称</th>
       <th>商品价格</th>
       <th>商品图片</th>
       <th>商品规格</th>
       <th>商品库存</th>
       <th>商品描述</th>
       <th>用户操作</th>                                            
    </tr>
    
    
    <c:forEach items="${products}" var="product">
    <tr>
       <td>${product.id}</td>
       <td>${product.category_id}</td>
       <td>${product.name}</td>
       <td>${product.price}</td>
       <td>${product.image}</td>
       <td>${product.rule}</td>
       <td>${product.stock}</td>
       <td>${product.desc}</td>
       <td>
       <a href="product?operation=3&id=${product.id}">修改</a>
       <a href="product?operation=4&id=${product.id}">删除</a> 
      
       <td>                                         
    </tr>
    
    </c:forEach>
    
    
</table>
</form>
</body>
</html>