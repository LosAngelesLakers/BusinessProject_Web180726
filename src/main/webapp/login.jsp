<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="" type="text/css"/>
</head>
<body>
   <div class="container-fluid" style="height:500px;">
      <div class="row"">
         <div class="col-xs-12 col-md-12  col-lg-12 ">
                   <div id="picbox">
                      <img src="imgs/华为logo.png">
                   </div>
         </div>
      </div>

      <div class="row" style="margin-top: 100px">
          <div class="col-xs-12 col-md-6  col-lg-6 ">
             <form action="login.do" method="post">
                 <table align="center" style="font-size: 20px">
                    <tr>
                      <td>用户名</td>
                      <td><input type="text" name="username"/></td>
                    </tr>
                    <tr></tr> <tr></tr> <tr></tr> <tr></tr>
                    <tr>
                      <td>密码</td>
                      <td><input type="password" name="password"/></td>
                    </tr>
                    <tr></tr> <tr></tr> <tr></tr> <tr></tr>
                     <tr>
                        <td></td>
                        <td><input type="submit" value="   登录   "></td>
                        <td> <a href="view/addAccount.jsp">注册</a> </td>  
                     </tr>
                 </table>
             </form>
      </div>          
    </div>
</div>
</body>
</html>