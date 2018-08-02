<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1 align="center">修改商品信息</h1>

<form action="cart" method="get">
<input type="hidden" name="operation" value="5">
<input type="hidden" name="id" value="${cart.id}">
   <input type="hidden" name="unit_price" value="${cart.unit_price}">
   <table align="center">
   <tr>
   <%-- <td>购物车id</td>
   <td><input type="text" name="id" value="${cart.id }"/></td>
   </tr>
   
   <tr>
   <td>商品id</td>
   <td><input type="text" name="productid" value="${cart.productid }"/></td>
   </tr> --%>
   
   <tr>
   <td>商品数目</td>
   <td><input type="text" name="productNum" value="${cart.productNum }"/></td>
   </tr>
   
   
   
   <tr>
   <td><input type="submit" value="修改"/></td>
   </tr>
   </table>
</form>


</body>
</html>