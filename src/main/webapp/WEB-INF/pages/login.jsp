<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<title></title>

</head>

<body>
<div id="bottom">
	<form id="loginForm" class="login-form" method="POST" action="${pageContext.request.contextPath}/login">
		<div id="top">
			<img src="${pageContext.request.contextPath}/resources/images/cloud.jpg" />
			<span>Login</span>
		</div>
		<div class="info">${info}</div>

		<div class="txtb">
			<input type="text" name="uname" autocomplete="off">
			<span data-placeholder="用户名"></span>
		</div>

		<div class="txtb">
			<input type="password" name="upass" autocomplete="off">
			<span data-placeholder="密码"></span>
		</div>

		<button type="submit" class="btn btn-primary loginbtn">登录</button>
	</form>
	<script type="text/javascript">
		$(".txtb input").on("focus", function(){
			$(this).addClass("focus");
		});

		$(".txtb input").on("blur", function(){
			if($(this).val() == "")
				$(this).removeClass("focus");
		});

	</script>
</div>
</body>

</html>