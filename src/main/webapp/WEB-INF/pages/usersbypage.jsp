<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 用于格式化时间 -->
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script type="text/javascript">
	/*进入添加商品的页面*/
    function addusers(){
    	window.location.href="${pageContext.request.contextPath}/adduserspage";//get
    }
</script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bright.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/addBook.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<title></title>
<style type="text/css">
	#batchdelpro {
		background-color: orangered;
		width: 150px;
		height: 48px;
		margin-top: 15px;
		margin-left: 25px;
		font-size: 18px;
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#checkAll").click(function(){
			if($(this).prop("checked")){
                $("input[type='checkbox']").not(this).prop("checked",true);
            }else{
                $("input[type='checkbox']").not(this).prop("checked",false);
            }
		});
		
		$("#batchdelusers").click(function(){
			 var ids = new Array(); //定义一个数组存储id
			 $("input[name='id']:checked").each(function() {
			      ids.push($(this).val()); // 把值push进入数组里面
			 });
			 if(ids.length == 0){
			      alert('请选择至少一条记录删除');
			      return;
			 }
			 location.href="${pageContext.request.contextPath}/batchdelusers?ids="+ids;//get
		});
	});
</script>
</head>

<body>

    <!-- 判断否登录  登录成功会将users保存到session ,如果没有登录 session中的users为空 -->
    <!--UsersController.java中的登录中的登录成功保存用户信息到session的代码 
    session.setAttribute("users", users); -->
	<c:if test="${sessionScope.users == null }">
		<!--  没有登录，重新去登录页面登录  对应UsersController.java中的
		@RequestMapping(value="/login",method=RequestMethod.GET)-->
		<c:redirect url="login"></c:redirect>
	</c:if>
	<div id="brall">
		<div id="nav">
			<p>用户管理>用户列表</p>
		</div>
		<div id="condition" style="text-align: center">
			<form action="${pageContext.request.contextPath}/getusersbypage" id="myform">
				<div class="searchTop">
					<div>
						<label for="name">用户名称：</label>
						<input name="uname" id="name" value="${name }"placeholder="请输入用户名称"  class="form-control indvidiv">
					</div>
					<div class="onetop">
						<label for="typeid">角色类型：</label>
						<select name="roleid" id="typeid" class="form-control indvidiv">
							<option value="-1">请选择</option>
							<c:forEach items="${rolelist}" var="role">
								<option value="${role.id}"
										<c:if test="${role.id==roleid}">
											selected="selected"
										</c:if>>
										${role.rolename}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="onetop">
						<button type="submit" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
		</div>
		<br>
		<div id="table">
			<div id="top">
				<input type="button" class="btn btn-warning mv" id="batchdelpro" value="批量删除">
				<input type="button" class="btn btn-warning" id="btn1" value="新增用户" onclick="addusers()">
			</div>
			<!--显示没有分页的用户信息-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<thead>
					<tr>
					    <th style="width: 50px;text-align: center;" scope="col">
					    	<input type="checkbox" style="width: 20px;height: 20px;" id="checkAll">
					    </th>
						<th scope="col">账号</th>
						<th scope="col">部门</th>
						<th scope="col">真实姓名</th>
						<th scope="col">角色</th>
						<th scope="col">图片</th>
						<th scope="col">操作</th>
					</tr>
					</thead>
					<tbody>
					<!-- pagebean分页实体 list属性为当前页的数据 -->
					<c:forEach items="${pagebean.list}" var="u">
						<tr>
						    <th style="width: 50px;text-align: center;" scope="row">
						    	<input type="checkbox" name="id" value="${u.uid}" style="width: 20px;height: 20px;">
						    </th>
							<td>${u.uname}</td>
							<td>${u.udepartment}</td>
							<td>${u.realname}</td>
							<td>${u.rolename}</td>
							<td><img width="55px" height="45px"
								<c:if test="${u.uimage == '' || u.uimage == null}">
									 src="${pageContext.request.contextPath}/resources/image_big/defualt.jpg"
								</c:if>
								<c:if test="${u.uimage != ''}">
									 src="${pageContext.request.contextPath}/resources/image_big/${u.uimage}"
								</c:if>>
								</td>

							<td>
								<button type="button" class="btn btn-info myupdate"
									onclick="umodify(${u.uid})">修改</button>
								<button type="button" class="btn btn-warning" id="mydel"
									onclick="udel(${u.uid})">删除</button>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<!--分页栏-->
				<div class="footNum">
					<ul>
						<c:choose>
							<c:when test="${pagebean.page eq 1 }">
								<li class="pre"><a href="javascript:void(0)">上一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="pre"><a
									href="${pageContext.request.contextPath}/getusersbypage?page=${pagebean.page-1}&uname=${uname}&roleid=${roleid}">
										上一页</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${pagebean.pages}" step="1" var="index">
							<c:choose>
								<c:when test="${pagebean.page eq index}">
									<li class="num current"><a href="javascript:void(0)">${index}</a></li>
								</c:when>
								<c:otherwise>
									<li class="num"><a
										href="${pageContext.request.contextPath}/getusersbypage?page=${index}&uname=${uname}&roleid=${roleid}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pagebean.page eq pagebean.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getusersbypage?page=${pagebean.page+1}&uname=${uname}&roleid=${roleid}">
										下一页</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    /*删除商品  */
    function udel(id) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/delusers?id="+id;//get
        }
    }
    /*修改商品  */
    function umodify(id) {
        location.href = "${pageContext.request.contextPath}/getusersbyid?id="+id;
    }
</script>
</html>