<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bootstrap.css" />
<script src="../js/echarts.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript" src="../js/myfocus-2.0.1.min.js"></script>
		<script type="text/javascript" src="../js/mf-pattern/mF_games_tb.js"></script>
		<link rel="stylesheet" href="../js/mf-pattern/mF_games_tb.css" />
		<script type="text/javascript">
			myFocus.set({
				id: 'picbox'
			})
</script>
<style>		
        *{
            margin: 0px;
            padding: 0px;
            }
		ul{
			list-style: none;
			margin: 0px;
			padding: 0px;
		}
		
		
		.current{
			background-color: #22282E;
		}	
		 .normal{
             background-color: #37424f;
         }
         .lisclick{
             background-color: #37424f;
         }
		.cat{
		      font-size: 18px;
		      height: 45px;
             /* border-bottom:1px whitesmoke solid;*/
              margin-bottom: 1px;
		      display:flex;
		      justify-content:center;
		      align-items: center;
		      color: white;
              box-shadow:0px 5px 5px white;
		}
        a:link {color: white; text-decoration:none !important;}
        a:active{color: white;text-decoration:none !important; }
        a:visited {color:white;text-decoration:none !important;}
        a:hover {color: white; text-decoration:none !important;}

        .lis{
				display:flex;
				height: 45px;
                width: 100%;
				justify-content: center;
                align-items: center;
                color: white;
                background-color: #293038;
		}
		
		
		
</style>		
		
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default" style="background-color:#FAFAFA;">
            <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                           <span class="sr-only">Toggle navigation</span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                        </button>
                           <a class="navbar-brand" href="#" style="margin-left: 20px">
                               管理控制台
                           </a>
                    </div>

    
                   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> 
                       <ul class="nav navbar-nav navbar-right">
                          <li>
		                      <a href="manager.html" target="iframe">
			                     <span class="glyphicon glyphicon-user" aria-hidden="true"style="color:BLACK;"></span>
		                      </a>
		                  </li>
		
		                  <li>
		                     <a href="addguanli.html" target="iframe">
			                    <span class="glyphicon glyphicon-plus" aria-hidden="true"style="color:BLACK;"></span>
		                     </a>
		                  </li>
		
		                  <!-- <li>
		                     <a href="today.html" target="iframe">
			                     <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"style="color:white;"></span> 
		                     </a>
		                  </li> -->
		                   <li class="dropdown" >
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color:BLACK;"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"style="color:black;"></span> <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="addCart.jsp" target="iframe">添加购物车</a></li>
                                    <li><a href="cart?operation=2" target="iframe">查询购物车</a></li>
                                   
                                </ul>
                          </li>
		
		                  <li>
		                     <a href="main.html" target="iframe">
			                     <span class="glyphicon glyphicon-home" aria-hidden="true"style="color:BLACK;"></span>
		                     </a>
		                  </li>
		
		                  <li>
			                  <a href="http://localhost:8080/BusinessProject_Web/login.jsp" id="back">
			                      <span class="glyphicon glyphicon-off" aria-hidden="true" style="color:BLACK;"></span>
			                  </a>
		                  </li>
		
                          <li class="dropdown" >
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color:BLACK;">主题<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">蓝色</a></li>
                                    <li><a href="#">黑色</a></li>
                                    <li><a href="#">白色</a></li>
                                </ul>
                          </li>
                        </ul>
                      </div>
                </div>
          </nav>
     </div><!-- 第一行结束 -->
              
     <div class="row" style="margin-top:-19px">
          <!-- 左侧部分菜单栏 -->
           <div class="col-sm-2 col-md-2 col-lg-2" style="height:600px;background-color:#293038">
              <div id="contro" style="margin:0px;padding:0px;height:45px;font-size: 18px;display: flex;justify-content: center;align-items:center;color: white;background-color:#37424f;border-bottom: 1px whitesmoke solid">
                 管理控制台
              </div>
			  <ul id="menu" style="display: none;">
                  <li >
                      <a href="#" class="cat">
                          用户中心
                      </a>
                      <ul style="display: none;font-size: 15px;">
                          <li><a href="main.jsp"  class="lis" target="iframe">账号管理</a></li>
                          <li><a href="#"  class="lis" target="iframe">消息中心</a></li>
                          <li><a href="#"  class="lis" target="iframe">订单管理</a></li>

                      </ul>
                  </li>
					<li >
						<a href="#" class="cat">
						商品管理
						</a>
						<ul style="display: none;font-size: 15px;">
							<li><a href="AddProduct.jsp"  class="lis" target="iframe">添加商品</a></li>
							<li><a href="product?operation=2&pageNo=1&pageSize=5" class="lis" target="iframe">查询商品</a></li>
						</ul>
					</li>



					<li >
						<a href="#" class="cat">

						类别管理
						</a>
						<ul style="display: none;font-size: 18px;">
							<li><a href="addCategory.jsp"  class="lis" target="iframe">添加类别</a></li>
							<li><a href="category?operation=2" class="lis" target="iframe">查询类别</a></li>
						</ul>
					</li>



					<li >
						<a href="#" class="cat">
						购物车
						</a>
						<ul style="display: none;font-size: 18px;">
							<li><a href="addCart.jsp" class="lis" target="iframe">添加购物车</a></li>
							<li><a href="cart?operation=2" class="lis" target="iframe">查询购物车</a></li>
						</ul>
					</li>
					<li >
						<a href="#" class="cat">
						订单处理</a>
						<ul style="display: none;font-size: 18px;">
							<li><a href="order?operation=2&pageNo=${pageNo}&pageSize=5" class="lis" target="iframe">查询订单</a></li>
						</ul>
					</li>
			</ul>

				
				
		   </div><!-- 结束左侧部分 -->
          <!-- 右侧部分展示栏   --> 
           <div class="col-sm-10 col-md-10 col-lg-10 "style="padding: 0px;border-left: 1px black solid;background-color: #00FFCC">
					<div id="main">
						<iframe name="iframe" frameborder="0" width="100%" height="600" src="main.jsp" scrolling="no"></iframe>
					</div>
			</div>
     </div>         
              
         
              
</div><!-- 关闭容器 -->

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
<script>
		$(function(){
            $("#menu>li>a").addClass("normal");

			$("#contro").click(function(e){
     			$("#menu").slideToggle();
                $("#menu>li>a").next().slideUp();
     	     })
            $("#menu>li>a").click(function(){
                $(this).removeClass("normal").addClass("current")
                    .next().slideToggle()
                    .parent().siblings()
                    .children().removeClass("current").addClass("normal")
                    .next().hide();
            })
		 })

</script>
</html>