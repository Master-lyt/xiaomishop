<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录小米账号</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" ref="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<style type="text/css">
input:focus {
	border: 2px solid #FF7E00;
}

hr {
	border: 1px solid #EDEDED;
}
/* 顶部菜单导航栏 */
#topbar {
	background-color: #3B3B3B;
	width: 100%;
	height: 40px;
}

.sep {
	color: #3B3B3B
}

#topbar a {
	text-decoration: none;
	color: #BDBDBD;
	font-family: 黑体;
	font-size: 14px;
}

#topbar a:hover {
	color: white;
}

#topbar_left {
	width: 70%;
	margin-left: 100px;
	padding-top: 10px;
	float: left;
}

#topbar_right {
	width: 30%;
	margin-left: 950px;
	padding-top: 10px;
}

#menu {
	background-color: white;
	height: 95px;
}

#logo {
	margin-left: 350px;
	padding-top: 20px;
	float: left;
}

#menu_nav {
	margin-left: 100px;
	padding-top: 40px;
	width: 800px;
	float: left;
}

#menu_nav a {
	text-decoration: none;
	color: black;
	font-family: 黑体;
	font-size: 16px;
}

#menu_nav a:hover {
	color: #FF7E00;
}

#search {
	margin-left: 1300px;
	width: 300px;
	padding-top: 30px;
}

#searchinput {
	width: 200px;
	height: 40px;
	font-size: 16px;
	border: 1px solid #EDEDED;
}

#searchbtn {
	background-color: white;
	width: 60px;
	height: 40px;
	border: 1px solid #EDEDED;
	cursor: pointer;
	font-size: 16px;
	font-family: 黑体;
}
/* 登陆部分 */
.logininput {
	width: 250px;
	height: 40px;
	font-size: 16px;
}

#loginbtn {
	background-color: #FF7E00;
	width: 250px;
	height: 50px;
	line-height: 50px;
	border: 1px solid #EDEDED;
	cursor: pointer;
	font-size: 16px;
	font-family: 黑体;
	font-color:white;
}

#login {
	display: flex;
	flex-direction: column;
	position: absolute;
	text-align: center;
	background-color: white;
	width: 400px;
	height: 520px;
	border-radius: 15px;
	top: 80px;
	right: 100px;
	opacity: 0.90;
}

#loginlogo {
	width: 55px;
	margin: 0px auto;
}

#login_title {
	width: 200px;
	margin: 0px auto;
	font-family: 黑体;
}

#login_form {
	margin: 0 auto;
	width: 300px;
	height: 300px;
	margin-top: 20px;
}

#xy {
	width: 450px;
	margin: 0px auto;
	color: gray;
}

#video {
	position: absolute;
	left: 160px;
	top: 230px;
}
	label{
		margin-right: 10px;
	}
body{
	background-image: url(resources/image/login.jpg);
	background-repeat: no-repeat;
	background-size: 100% 110%;
	background-color: #EDEDED;
	font-family: 黑体;
	position: relative;
}
.oneGroup{
	margin-top: 30px;
	display: flex;
	flex-direction: row;
	align-items: center;
	position: relative;
}
#yzmImg{
	position: absolute;
	right: 5px;
}
#yzm{
	width: 150px;
}
#loginbtn{
	margin-top: 50px;
	border-radius: 5px;
	width: 100%;
}
	#xy{
		margin-top: 20px;
	}
</style>
</head>

<body>
	<!-- 顶部菜单导航栏 -->
	<div id="header">
		<div id="topbar">
			<div id="topbar_left">
				<a href="">小米商城</a><span class="sep">|</span> <a href=""
					target="_blank">MIUI</a><span class="sep">|</span> <a href=""
					target="_blank">米聊</a><span class="sep">|</span> <a href=""
					target="_blank">游戏</a><span class="sep">|</span> <a href=""
					target="_blank">多看阅读</a><span class="sep">|</span> <a href=""
					target="_blank">云服务</a><span class="sep">|</span> <a href=""
					target="_blank">金融</a><span class="sep">|</span> <a href=""
					target="_blank">米币</a><span class="sep">|</span> <a href=""
					target="_blank">手机版</a><span class="sep">|</span> <a href=""
					target="_blank">问题反馈</a>
			</div>
			<div id="topbar_right">
				<c:if test="${empty customer }">
					<a href="${pageContext.request.contextPath}/tocustomerloginpage">登录</a>
					<span class="sep">|</span>
				</c:if>
				<c:if test="${not empty customer }">
					<a href="">${customer.cname }</a>
					<span class="sep">|</span>
					<a href="${pageContext.request.contextPath}/customerlogout">注销</a>
					<span class="sep">|</span>
					<span class="sep">|</span>
					<a href="${pageContext.request.contextPath}/showcarshopbycustomerid?customerid=${customer.cid}" target="_blank">购物车</a>
					<span class="sep">|</span>
				</c:if>
				<a href="${pageContext.request.contextPath}/toregisterpage">注册</a><span class="sep">|</span>
			</div>
		</div>
	</div>

	<!-- 登录部分 -->
	<div id="login">
		<div id="loginlogo">
			<img alt="" src="${pageContext.request.contextPath}/image/milogo.jpg">
		</div>
		<br> <br>
		<div id="login_title">
			<h1>登录小米账号</h1>
		</div>
		<div id="login_form">
			<form action="${pageContext.request.contextPath}/customerlogin" method="post">
				<div class="oneGroup input-group">
					<label for="cname"><img src="${pageContext.request.contextPath}/resources/image/userlogin.png" style="width: 35px; height: 25px"/></label>
					<input type="text" id="cname" name="cname" value="" class="logininput form-control"/>
				</div>

				<div class="oneGroup input-group">
					<label for="cpass"><img src="${pageContext.request.contextPath}/resources/image/userpassword.png" style="width: 35px; height: 25px"/></label>
					<input type="password" id="cpass" name="cpass" class="logininput form-control" value="" />
				</div>
				<div class="oneGroup input-group">
					<img alt="" src="${pageContext.request.contextPath}/randomcode" id="yzmImg" onclick="changeYzm()">
					<label for="yzm">验证码:</label>
					<input type="text" id="yzm" name="yzm" class="logininput form-control" value="" />
				</div>

				<button type="submit" id="loginbtn" class="btn btn-primary">立即登录</button>

				<p id="errormsg" style="color: red;">${error}</p>
			</form>
		</div>
	</div>


	<script type="text/javascript">

		//更换验证码
		function changeYzm() {
			document.getElementById("yzmImg").src = "randomcode?num=" + Math.random();
		}

	</script>
</body>
</html>
