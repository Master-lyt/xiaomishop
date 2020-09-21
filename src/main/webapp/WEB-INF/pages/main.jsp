<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<title></title>
<style type="text/css">
	.two{
		background-color: #363F4A;
	}
	.one{
		background-color: #465260;
	}
</style>

</head>

<body>
	<!--整体部分-->
	<div id="all">
		<!--上部分-->
		<div id="top">
			<div id="top1">
				<span>商品管理系统</span>
			</div>
			<div id="top2"></div>
			<div id="top3">
				<span>欢迎您:${users.realname}&nbsp;角色:${users.rolename}</span>
			</div>
		</div>
		<!--下部分-->
		<div id="bottom">
			<!--下部分左边-->
			<div id="bleft">
				<div id="ltop">
					<div id="lts">
						<img
							src="${pageContext.request.contextPath}/resources/image_user/pooh.jpg" /><br/>
						<p style="text-align: center;">${user.realname}</p>
					</div>
				</div>
				<div id="lbottom">
					<ul>
						<!-- 获取商品@RequestMapping("/getprobypage") -->
						<a href="${pageContext.request.contextPath}/getproductbypage" target="myright">
							<li class="one">
								<span class="glyphicon glyphicon-book"style="color: white;"></span>
								<div class="Manage">商品管理</div>
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
							</li>
						</a>
						<a href="${pageContext.request.contextPath}/admin/err.jsp" target="myright">
							<li class="one">
								<span class="glyphicon glyphicon-sort" style="color: white;"></span>
								<div class="Manage">订单管理</div>
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
							</li>
						</a>
						<!-- 获取用户@RequestMapping("/getusers") -->
						<a href="${pageContext.request.contextPath}/getusersbypage" target="myright">
							<li class="one">
								<span class="glyphicon glyphicon-user"style="color: white;"></span>
								<div class="Manage">员工管理</div>
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
							</li>
						</a>
<%-- 						<a href="${pageContext.request.contextPath}/getproducttypebypage"
							target="myright">
							<li class="one"><span class="glyphicon glyphicon-bullhorn"
								style="color: white"></span>&nbsp;&nbsp;&nbsp;&nbsp;商品类型管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
								class="glyphicon glyphicon-play" style="color: white;"></span></li>
						</a> --%>
						<!--ajax分页实现 -->
						<a href="${pageContext.request.contextPath}/toproducttypepage"
							target="myright">
							<li class="one">
								<span class="glyphicon glyphicon-bullhorn" style="color: white"></span>
								<div class="Manage">商品类别管理</div>
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
							</li>
						</a>
						<a href="${pageContext.request.contextPath}/logout" target="_top">
							<li class="one">
								<span class="glyphicon glyphicon-bullhorn" style="color: white"></span>
								<div class="Manage">退出系统</div>
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
							</li>
						</a>
					</ul>
				</div>
			</div>
			<!--下部分右边-->
			<div id="bright">
				<iframe frameborder="0" scrolling="no" name="myright" width="100%"
					height="700px"></iframe>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function () {
			$("li").on("click", function () {
				$("li").each(function () {
					if ($(this).hasClass("two")){
						console.log("have!");
						$(this).removeClass("two");
						$(this).addClass("one");
					}
				});
				$(this).removeClass("one");
				$(this).addClass("two");
			})
		})
	</script>
</body>

</html>