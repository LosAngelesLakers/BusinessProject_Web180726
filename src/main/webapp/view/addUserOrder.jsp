<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>添加订单</h1>
<table>
   <tr>
     <td>特定商品id</td>
     <td><input type="text" name="pid"></td>
     <td><input type="submit" value="查询"></td>
    </tr>
    <tr>
       
       <th>商品名称</th>
       <th>商品价格</th>
       <th>商品图片</th>
       <th>商品规格</th>
       <th>商品库存</th>
       <th>商品描述</th>
       <th>用户操作</th>                                            
    </tr> 
   
    <tr>
       
       <td>${product.name}</td>
       <td>${product.price}</td>
       <td>${product.image}</td>
       <td>${product.rule}</td>
       <td>${product.stock}</td>
       <td>${product.desc}</td>                                         
    </tr>
  
</table>




</body>
</html>