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
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/resources/js/ajaxfileupload.js"></script>
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
	#imgDiv{
		display: block;
		width: 100px;
		height: 100px;
	}
	#upimage{
		margin-top: 5px;
	}
	.myCheckBox{
		width: 20px;
		height: 20px;
	}
</style>
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
				<input type="button" class="btn btn-warning" id="btn1" value="新增商品" data-toggle="modal" data-target="#modifyModal">
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
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">商品操作</h4>
					</div>
					<div class="modal-body">
						<form id="modForm">
							<input type="hidden" name="id" id="modId">
							<div class="form-group">
								<label for="modName">商品名称：</label>
								<input type="text" class="form-control" placeholder="请输入商品名称"
									   name="name" autocomplete="off" id="modName">
							</div>
							<div class="form-group">
								<label for="modContent">商品介绍：</label>
								<input type="text" class="form-control" placeholder="请输入商品介绍"
									   name="content" autocomplete="off" id="modContent">
							</div>
							<div class="form-group">
								<label for="modPrice">定价：</label>
								<input class="form-control" placeholder="请输入商品定价"
									   type="number" name="price" autocomplete="off" id="modPrice">
							</div>
							<div class="form-group">
								<label for="upimage">图片：</label>
								<div id="imgDiv"></div>
								<input type="file" id="upimage" class="form-control-file" name="upimage"  onchange="fileChange()">
								<input type="hidden" id="pimage" name="image" value="">
							</div>
							<div class="form-group">
								<label  for="modTotal">总量：</label>
								<input class="form-control" placeholder="请输入商品总量"
									   type="number" name="number" autocomplete="off" id="modTotal">
							</div>
							<div class="form-group">
								<label for="modSelect">类别：</label>
								<select name="typeid" id="modSelect" class="form-control">
									<c:forEach items="${ptlist}" var="type">
										<option value="${type.typeId}">${type.typeName}</option>
									</c:forEach>
								</select>
							</div>
						</form> 
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="aSubmit">确定</button>
					</div>
				</div>
		</div>
	</div>
</body>
<script type="text/javascript">

    var myClickFlag = -1;

	function fileChange(){//注意：此处不能使用jQuery中的change事件，因此仅触发一次，因此使用标签的：onchange属性
		$.ajaxFileUpload({
			url: '${pageContext.request.contextPath}/produpload',//用于文件上传的服务器端请求地址
			secureuri: false,//一般设置为false
			fileElementId: 'upimage',//文件上传控件的id属性  <input type="file" id="upimage" name="upimage" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function(obj,status){//服务器成功响应处理函数
				var str = "${pageContext.request.contextPath}";
				str += obj.imgurl;
				$("#imgDiv").empty();  //清空原有数据
				//创建img 标签对象
				var imgObj = $("<img>");
				//给img标签对象追加属性
				//alert(obj.imgurl);
				imgObj.attr("src",str);
				imgObj.attr("width","100px");
				imgObj.attr("height","100px");
				//将图片img标签追加到imgDiv末尾
				$("#imgDiv").append(imgObj);
				//将图片的名称（从服务端返回的JSON中取得）赋值给文件本框
				//alert(obj.imgName);
				$("#pimage").val(obj.imgName);
			},
			error: function (obj,status,e)//服务器响应失败处理函数
			{
				alert(e.message);
			}
		});
	}

    var ids = []; //定义一个数组存储id
	//分页的js代码
	//第一次进入页面默认显示第一页的数据
	var currentPage = 1;
	var myCurrentPage = 1;

	//载入 (默认加载全部) 默认第一次为currentPage为 1
	loadData(currentPage);

	//查询
	function loadData(page) {

        putIntoArray();

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
            async:false,
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
								'<input type="checkbox" name="id" value="'+ data.list[i].id +'" class="myCheckBox"/> </th> ' +
								'<td>'+ data.list[i].name +'</td>' +
								'<td>'+ data.list[i].content +'</td>' +
								'<td>'+ data.list[i].price +'</td>' +
								'<td><img width="55px" height="45px"' +
								'src="${pageContext.request.contextPath}/resources/image_big/'+ data.list[i].image +'"></td> ' +
								'<td>'+ data.list[i].number +'</td>' +
								'<td>'+ data.list[i].sdate +'</td>' +
								'<td>'+ data.list[i].typename +'</td>' +
								'<td> ' +
								'<button type="button" class="btn btn-info myupdate" data-toggle="modal" data-target="#modifyModal"' +
								'onclick="pmodify('+ data.list[i].id +')">修改</button> ' +
								'<button type="button" class="btn btn-warning" id="mydel"' +
								'onclick="pdel('+ data.list[i].id +')">删除</button> </td> </tr>'
						)
					}
				}
                checkBoxStyle();
				//分页脚标 ：data.pageSize每页显示数， data.pageCount总的页数， data.rowCount总的行数
				createPageInfo(page,data.pageSize,data.pageCount,data.rowCount,goToPage);
			}
		});
	}

	function goToPage(n){
		myCurrentPage = n;
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

	$(".modal").on("hide.bs.modal",function(){
		$("#modId").val("");
		$("#modName").val("");
		$("#modContent").val("");
		$("#modPrice").val("");
		$("#modTotal").val("");
		$("#pimage").val("");
		$("#upimage").val("");
		$("#imgDiv").html("");
	});

	//添加按钮的提交
	$("#aSubmit").bind("click",function(){
		let id = $("#modId").val();
		if (id == "") {
			console.log("addProduct!!");
			$.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/addproduct",
				data:{
					name:$("#modName").val(),
					content:$("#modContent").val(),
					price:$("#modPrice").val(),
					number:$("#modTotal").val(),
					image:$("#pimage").val(),
					typeid:$("#modSelect").val()
				},
				dataType:"json",
				success:function(data){
					$("#modifyModal").modal("hide");
					loadData(1);
				}
			});
		} else {
			console.log("updateProduct!!");
			$.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/product_update_ajax",
				data:$("#modForm").serialize(),
				dataType:"json",
				success:function (data) {
					console.log("updateProduct---success")
					$("#modifyModal").modal("hide");
					loadData(1);
				}
			});
		}

	});

    /*删除商品  */
    function pdel(id) {
        if (confirm("确定删除吗")) {
        	$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/delproduct?id=" + id,
				dataType:"json",
				success:function (data) {
					loadData(1);
				}
			});
        }
    }
    /*修改商品  */
    function pmodify(id) {
    	$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/getproductbyid_ajax?id="+id,
			dataType:"json",
			success:function (data) {
				$("#modImg").attr("src", "${pageContext.request.contextPath}/resources/"+data.product.image);
				$("#modId").val(data.product.id);
				$("#modName").val(data.product.name);
				$("#modContent").val(data.product.content);
				$("#modPrice").val(data.product.price);
				$("#modTotal").val(data.product.number);
				$("#pimage").val(data.product.image);
				let src = "";
				src += "<img src='${pageContext.request.contextPath}/resources/image_big/"+
						data.product.image + "' width='100px' height='100px' id='modImg'/>";
				$("#imgDiv").html(src);

				var str = "";
				for (let i = 0; i < data.plist.length; i++) {
					if (data.product.typeid === data.plist[i].typeId) {
						str += "<option selected = 'selected' value = '"+ data.plist[i].typeId +"'>"+ data.plist[i].typeName +" </option>";
					}else {
						str += "<option value='"+ data.plist[i].typeId +"'>"+ data.plist[i].typeName +"</option>";
					}
				}
				$("#modSelect").html(str);
			}
		});
    }
    //换页时先将选中check存入数组
    function putIntoArray() {
        $("input[name='id']:checked").each(function() {
            if (ids.indexOf($(this).val()) === -1 ){
                ids.push($(this).val());// 把值push进入数组里面
            }
        });
    }

    //将checkbox改为checked状态
    function checkBoxStyle(){
        $(".myCheckBox").each(function () {
            if (ids.indexOf($(this).val()) !== -1 ){
                $(this).prop("checked", true);
            }
        });
    }

    $(function(){
        $("#checkAll").click(function(){
            if($(this).prop("checked")){
                $("input[type='checkbox']").not(this).prop("checked",true);
            }else{
                $("input[type='checkbox']").not(this).prop("checked",false);
            }
        });

        $("#batchdelpro").click(function(){

            putIntoArray();

            if(ids.length === 0){
                alert('请选择至少一条记录删除');
                return;
            }
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/batchdelproduct_ajax?ids="+ids,
                dataType:"json",
                success:function (data) {
                    console.log("batchdelproduct_ajax---success---pro--ids:" + ids);
                    ids.splice(0, ids.length);
                    console.log("batchdelproduct_ajax---success----ids:" + ids);
                   loadData(1);
                }
            })
        });
    });
</script>
</html>