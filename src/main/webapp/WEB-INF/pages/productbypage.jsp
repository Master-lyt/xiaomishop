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
		function addpro(){
			window.location.href="${pageContext.request.contextPath}/addproductpage";//get
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
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/kkpage.css" />
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/kkpage.js"></script>
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
		
		$("#batchdelpro").click(function(){
			 var ids = new Array(); //定义一个数组存储id
			 $("input[name='id']:checked").each(function() {
			      ids.push($(this).val()); // 把值push进入数组里面
			 });
			 if(ids.length == 0){
			      alert('请选择至少一条记录删除');
			      return;
			 }
			 location.href="${pageContext.request.contextPath}/batchdelproduct?ids="+ids;//get
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
		<c:redirect url="login"/>
	</c:if>
	<div id="brall">
		<div id="nav">
			<p>商品管理>商品列表</p>
		</div>
		<div id="condition" style="text-align: center">
			<form id="myform">
				<div class="searchTop">
					<div>
						<label for="name">商品名称：</label>
						<input name="typeName" id="name" value="${typeName }"placeholder="请输入商品名称"  class="form-control indvidiv">
					</div>
					<div class="onetop">
						<label for="typeid">商品类型：</label>
						<select name="typeId" id="typeId" class="form-control indvidiv">
							<option value="-1">请选择</option>
							<c:forEach items="${ptlist}" var="type">
								<option value="${type.typeId}"
										<c:if test="${type.typeId==typeid}">
											selected="selected"
										</c:if>>
										${type.typeName}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="onetop">
						<button type="button" class="btn btn-primary" id="search">查询</button>
					</div>
				</div>
			</form>
		</div>
		<br>
		<div id="table">
			<div id="top">
			    <input type="button" class="btn btn-warning" id="batchdelpro" value="批量删除">
				<input type="button" class="btn btn-warning" id="btn1" value="新增商品" onclick="addpro()">
			</div>
			<!--显示分页的商品信息-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th style="width: 50px;text-align: center;" scope="col">
								<input type="checkbox" style="width: 20px;height: 20px;" id="checkAll">
							</th>
							<th scope="col">商品名称</th>
							<th scope="col">商品介绍</th>
							<th scope="col">定价（元）</th>
							<th scope="col">商品图片</th>
							<th scope="col">商品数量</th>
							<th scope="col">日期</th>
							<th scope="col">商品类型</th>
							<th scope="col">操作</th>
						</tr>
					</thead>
					<!-- 动态生成数据，做数据的显示的局部刷新 -->
					<tbody id="productlist"></tbody>
				</table>
				<!--分页栏 动态生成-->
				<div id="kkpager"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	//分页的js代码
	//第一次进入页面默认显示第一页的数据
	var currentPage = 1;

	//载入 (默认加载全部) 默认第一次为currentPage为 1
	loadData(currentPage);

	//查询
	function loadData(page) {

		var currentPage = page;

		//获取查询的参数
		//商品类型的ID值
		var tid = $("#typeId").val();
		if(tid == ''){
			tid = -1;
		}
		//商品类型的名称值
		var name = $("#name").val();

		//异步加载数据 参数的格式为json格式的参数{currentPage:page} 参数的名称currentPage 参数的值page
		//在springmvc中使用Map集合接受参数，还需要注解@RequestParam
		//数据返回的对象名称为data,名称可以自定义，返回的格式有时json
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/product_list_ajax",
			data:{pn:currentPage,typeId:tid,Name:name},
			dataType:"json",
			success:function (data) {
				//先清除前一步的数据tbody
				$("#productlist").html("");
				//先清除前一步的分页div
				$("#kkpager").html("");
				//遍历数据 生成动态的数据 附加到tbody里面去 ，data就是我们的分页的实体类PageBean转换后的Map集合,list键就是数据
				if (data.list != null && data.list.length > 0) {
					for (var i = 0; i < data.list.length; i++) {
						$("#productlist").append('<tr> ' +
								'<th style="width: 50px;text-align: center;" scope="row">' +
								'<input type="checkbox" name="id" value="'+ data.list[i].id +'" style="width: 20px;height: 20px;"> </th> ' +
								'<td>'+ data.list[i].name +'</td>' +
								'<td>'+ data.list[i].content +'</td>' +
								'<td>'+ data.list[i].price +'</td>' +
								'<td><img width="55px" height="45px"' +
								'src="${pageContext.request.contextPath}/resources/image_big/'+ data.list[i].image +'"></td> ' +
								'<td>'+ data.list[i].number +'</td>' +
								'<td>'+ data.list[i].sdate +'</td>' +
								'<td>'+ data.list[i].typename +'</td>' +
								'<td> ' +
								'<button type="button" class="btn btn-info myupdate" ' +
								'onclick="pmodify('+ data.list[i].id +')">修改</button> ' +
								'<button type="button" class="btn btn-warning" id="mydel"' +
								'onclick="pdel('+ data.list[i].id +')">删除</button> </td> </tr>'
						)
					}
				}
				//分页脚标 ：data.pageSize每页显示数， data.pageCount总的页数， data.rowCount总的行数
				createPageInfo(page,data.pageSize,data.pageCount,data.rowCount,goToPage);
			}
		});
	}

	function goToPage(n){
		loadData(n);
	}

	//init
	function createPageInfo(currentPage,pageSize,pageCount,recordCount,callbackFunction){

		var totalPage = pageCount;
		var totalRecords = recordCount;
		var pageNo = currentPage;
		if(!pageNo){
			pageNo = 1;
		}

		$("#kkpager").html("");


		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.inited = false;
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			mode : 'click',
			//总数据条数
			totalRecords : totalRecords,
			click : function(n){
				//这里可以做自已的处理
				//处理完后可以手动条用selectPage进行页码选中切换
				callbackFunction(n);
				kkpager.selectPage(n);
			}
		});
	}

	//查询按钮的提交
	$("#search").bind("click",function(){
		loadData(currentPage);
	});

    /*删除商品  */
    function pdel(id) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/delproduct?id="+id;//get
        }
    }
    /*修改商品  */
    function pmodify(id) {
        location.href = "${pageContext.request.contextPath}/getproductbyid?id="+id;
    }
    function addpro() {
		location.href = "${pageContext.request.contextPath}/addproductpage"
	}
</script>
</html>