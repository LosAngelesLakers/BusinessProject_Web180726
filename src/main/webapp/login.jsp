<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CSS3方块动画背景登录界面模板</title>

    <link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>


<div class="wrapper">

    <div class="container">
        <h1>Welcome</h1>
        <form class="form" action="login.do" method="post">
            <input type="text" placeholder="用户名" name="username">
            <input type="password" placeholder="密码" name="password">
            <button type="submit" id="login-button">登录</button>
        </form>
    </div>

    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>

</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    $('#login-button').click(function(event){

        $('form').fadeOut(500);
        $('.wrapper').addClass('form-success');
    });
</script>

</body>
</html>