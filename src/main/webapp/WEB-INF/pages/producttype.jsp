<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<script type="text/javascript">
		function addproducttypepage(){
			window.location.href="${pageContext.request.contextPath}/addproducttypepage";//get
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
</head>

<body>
<div id="brall">
	<div id="nav">
		<p>商品类型管理>商品类型列表</p>
	</div>
	<div id="condition" style="text-align: center">
		<form id="myform">
			<div class="searchTop">
				<div>
					<label for="tid">商品类型ID：</label>
					<input name="typeName" id="tid" value="${tid }"placeholder="请输入商品类型ID"  class="form-control indvidiv">
				</div>
				<div class="onetop">
					<label for="tname">商品类型名称：</label>
					<input name="typeName" id="tname" value="${tname }"placeholder="请输入商品类型名称"  class="form-control indvidiv">
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
			<button type="button" class="btn btn-warning" id="btn1" data-toggle="modal" data-target="#addModal">新增商品类型</button>
		</div>
		<!--显示没有分页的商品信息-->
		<div id="middle">
			<table class="table table-bordered table-striped">
				<thead>
				<tr>
					<th>商品类型ID</th>
					<th>商品类型名称</th>
					<th>操作</th>
				</tr>
				</thead>
				<!-- 动态生成数据，做数据的显示的局部刷新 -->
				<tbody id="producttypelist"></tbody>
			</table>
			<!--分页栏 动态生成-->
			<div id="kkpager"></div>
		</div>
	</div>
</div>
<!-- 添加/修改 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<form id="addForm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">商品类型管理>商品类型</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="typeId" id="typeIdA" value="">
					<div class="row cl">
						<label class="form-label col-xs-3">类型名称：</label>
						<div class="formControls col-xs-6">
							<input type="text" class="input-text" placeholder="请输入想要添加的商品名称"
								   name="typeName" id="typeNameA" autocomplete="off">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="aSubmit">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
	//分页的js代码
	//第一次进入页面默认显示第一页的数据
	var currentPage = 1;
	var sPage = 1;

	//载入 (默认加载全部) 默认第一次为currentPage为 1
	loadData(currentPage);

	//查询  
	function loadData(page) {

		var currentPage = page;

		//获取查询的参数
		//商品类型的ID值
		var tid = $("#tid").val();
		if(tid == ''){
			tid = -1;
		}
		//商品类型的名称值
		var tname = $("#tname").val();

		//异步加载数据 参数的格式为json格式的参数{currentPage:page} 参数的名称currentPage 参数的值page
		//在springmvc中使用Map集合接受参数，还需要注解@RequestParam
		//数据返回的对象名称为data,名称可以自定义，返回的格式有时json
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/producttype_list_ajax",
			data:{pn:currentPage,typeId:tid,typeName:tname},
			dataType:"json",
			success:function (data) {
				//先清除前一步的数据tbody
				$("#producttypelist").html("");
				//先清除前一步的分页div
				$("#kkpager").html("");
				//遍历数据 生成动态的数据 附加到tbody里面去 ，data就是我们的分页的实体类PageBean转换后的Map集合,list键就是数据
				if (data.list != null && data.list.length > 0) {
					for (var i = 0; i < data.list.length; i++) {
						//将数据动态的附加到<tbody id="producttypelist"></tbody>
						$("#producttypelist").append("<tr>"+
								"<td>"+data.list[i].typeId+"</td>"+
								"<td>"+data.list[i].typeName+"</td>"+
								"<td>" +
                                "<a href='#' type='button' class='upd btn btn-info' name="+data.list[i].typeId+">修改</a>&nbsp;&nbsp;"+
								"<a href='#' type='button' class='del btn btn-warning' name="+data.list[i].typeId+">删除</a>"+
								"</td></tr>");
					}
				}
				//分页脚标 ：data.pageSize每页显示数， data.pageCount总的页数， data.rowCount总的行数
				createPageInfo(page,data.pageSize,data.pageCount,data.rowCount,goToPage);
			}
		});
	}

	function goToPage(n){
		sPage = n;
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
				kkpager.selectPage(n)
			}
		});
	}

	//置空类别名称
	$("#btn1").bind("click", function () {
		$("#typeNameA").val("");
	});

	//查询按钮的提交
	$("#search").bind("click",function(){
		loadData(currentPage);
	});

	$(document).ready(function(){
        //删除函数
		$(document).on("click",".del",function(){
			var id = $(this).attr("name");
			if(confirm("确定要删除吗？")){
				$.ajax({
					type:"GET",
					url:"${pageContext.request.contextPath}/delproducttype",
					data:{id:id},
					dataType:"json",
					success:function (data) {
						alert(data.message);
						if(sPage > data.lastPage){
							sPage = data.lastPage;
						}
						loadData(sPage);
					}
				});
			}
		});

		//添加/修改
		$(document).on("click","#aSubmit",function(){
			var url = "${pageContext.request.contextPath}/updateprotype";
			var typeId = $("#typeIdA").val();
			if(typeId == ''){
				typeId = -1;
				url = "${pageContext.request.contextPath}/addprotype";
			}
			var typeName = $("#typeNameA").val();
			$.ajax({
				type:"POST",
				url:url,
				data:{typeId:typeId, typeName:typeName},
				dataType:"json",
				success:function (data) {
					$("#addModal").modal("hide");
					if(typeId == -1){
						sPage = data.lastPage;
					}
					$("#typeIdA").val("");
					$("#typeNameA").val("");
					setTimeout(function () {
						alert(data.message);
						loadData(sPage);
					}, 500);
				}
			});
		});

		//取修改信息
        $(document).on("click",".upd",function(){
			var id = $(this).attr("name");
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/producttypemodify",
				data:{id:id},
				dataType:"json",
				success:function (data) {
					$("#addModal").modal("show");
					$("#typeIdA").val(data.productType.typeId);
					$("#typeNameA").val(data.productType.typeName);
				}
			});
        });
	});

</script>
</html>