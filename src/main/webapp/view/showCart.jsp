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
<h1>购物车</h1>
<form action="cart" method="get">
<table>
    <tr>
       <th>购物车id</th>
       <th>商品id</th>
       <th>商品数目</th> 
       <th>商品单价</th>
       <th>共支付</th>
       <th>购物车管理</th>                                          
    </tr>
    
    
    <c:forEach items="${pageModel.data}" var="cart">
    <tr>
       <td>${cart.id}</td>
       <td>${cart.productid}</td>
       <td>${cart.productNum}</td>
       <td>${cart.unit_price}</td>
       <td>${cart.totalprice}</td>
       <td>
       <a href="cart?operation=3&id=${cart.id}">修改</a>
       <a href="cart?operation=4&id=${cart.id}">删除</a> 
       <a href="order?operation=1&totalprice=${cart.totalprice}&id=${cart.productid}">下单</a> 
       <td>                                         
    </tr>
    
    </c:forEach>
    
    
</table>
<c:forEach begin="1" end="${pageModel.totalPage}" step="1" var="pageNo">
         <c:choose>
         <c:when test="${pageModel.currentPage==pageNo}">
         <a style="color: red" href="cart?operation=2&pageNo=${pageNo}&pageSize=5">${pageNo}</a>
         </c:when>
         
         <c:when test="${pageModel.currentPage!=pageNo}">
          <a href="cart?operation=2&pageNo=${pageNo}&pageSize=5">${pageNo}</a>
         </c:when>
         
         </c:choose>
   </c:forEach>
</form>
</body>
</html>