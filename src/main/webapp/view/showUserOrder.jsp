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
<h1>订单表</h1>
<form action="order" method="get">
<table>
    <tr>
       <th>订单id</th>
       <th>订单号</th>
       <th>用户id</th>
       <th>地址id</th>
       <th>实际付款</th>
       <th>支付类型</th>
       <th>运费</th>
       <th>订单状态</th>
      <!--  <th>支付时间</th>
       <th>发货时间</th>
       <th>交易完成时间</th>
       <th>交易关闭时间</th> -->
       <th>创建时间</th>    
       <!-- <th>更新时间</th>    -->                                        
    </tr>
    
    
    <c:forEach items="${pageModel.data}" var="userOrder">
    <tr>
       <td>${userOrder.id}</td>
       <td>${userOrder.order_no}</td>
       <td>${acc.accountId}</td>
       <td>${acc.accountId}</td>
       <td>${userOrder.payment}</td>
       <td>${userOrder.payment_type}</td>
       <td>${userOrder.postage}</td>
       <td>${userOrder.status}</td>
   <%--     <td>${userOrder.payment_time}</td>
       <td>${userOrder.send_time}</td>
       <td>${userOrder.end_time}</td>
       <td>${userOrder.close_time}</td> --%>
       <td>${userOrder.create_time}</td>
      <%--  <td>${userOrder.update_time}</td> --%>
                                             
    </tr>
    </c:forEach>
</table>


 <c:forEach begin="1" end="${pageModel.totalPage}" step="1" var="pageNo">
         <c:choose>
         <c:when test="${pageModel.currentPage==pageNo}">
         <a style="color: red" href="order?operation=2&pageNo=${pageNo}&pageSize=5">${pageNo}</a>
         </c:when>
         
         <c:when test="${pageModel.currentPage!=pageNo}">
          <a href="order?operation=2&pageNo=${pageNo}&pageSize=5">${pageNo}</a>
         </c:when>
         
         </c:choose>
         </c:forEach>

</form>
</body>
</html>