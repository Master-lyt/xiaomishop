<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 用于格式化时间 -->
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script type="text/javascript">
	/*进入添加用户的页面*/
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
    //初始加载
    var currentPage = 1;
    renovate(currentPage);
    //定义跨页删除数组
    var ids = new Array();

	$(function(){
		$("#checkAll").click(function(){
			if($(this).prop("checked")){
				$("input[type='checkbox']").not(this).prop("checked",true);
				$("input[type='checkbox']").each(function(){
					if ($(this).val() != "on") {
						check($(this).val());
					}
				})
			}else{
				$("input[type='checkbox']").not(this).prop("checked",false);
			}
		});
	});

    //更新页面
    function renovate(page) {
        var j = 1;
        currentPage = page;
    	var AbsoluteAddress = "${pageContext.request.contextPath}";
    	var strimg = "";
    	var strche = "";
		//获取查询值
		var roleid = $("#typeid").val();
		if (roleid == '' && roleid == null) {
			roleid = -1;
		}
		var uname = $("#name").val();
        //异步加载数据 参数的格式为json格式的参数{currentPage:page} 参数的名称currentPage 参数的值page
        //在springmvc中使用Map集合接受参数，还需要注解@RequestParam
        //数据返回的对象名称为data,名称可以自定义，返回的格式有时json
        $.ajax({
            type:"GET",
            url:"${pageContext.request.contextPath}/getusersbypage_ajax",
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            data:{page:currentPage, roleid:roleid, uname:uname},
            dataType:"json",
            success:function(data) {
                //先清除前一步的数据tbody
                $("#users_list").html("");
                //先清除前一步的分页div
                $("#kkpager").html("");
                //遍历数据 生成动态的数据 附加到tbody里面去 ，data就是我们的分页的实体类PageBean转换后的Map集合,list键就是数据
                if (data.list != null && data.list.length > 0) {
                    $.each(data.list, function (i, user) {
                    	console.log(user.uimage);
                        if (user.uimage == '' || user.uimage == null) {
                            strimg += 'src="' + AbsoluteAddress + '/resources/image_big/defualt.jpg"';
                        }
                        else {
                            strimg += 'src="' + AbsoluteAddress + '/resources/image_big/' + user.uimage + '"'
                        }

                        for (var j = 0; j < ids.length; j++) {
                            if (user.uid == ids[j]) {
                                console.log(ids[j]);
                                strche = "checked";
                            }
                        }

                        $("#users_list").append(
                            '<tr>\n'+
                            '<th style="width: 50px;text-align: center;" scope="row">' +
                            '<input type="checkbox" onclick="check(' + user.uid + ')" value="' + user.uid + '" style="width: 20px;height: 20px;" ' + strche + '>' +
                            '</th>' +
                            '<td>' + user.uname + '</td>' +
                            '<td>' + user.udepartment + '</td>' +
                            '<td>' + user.realname + '</td>' +
                            '<td>' + user.rolename + '</td>' +
                            '<td><img width="55px" height="45px" ' + strimg + '</td>' +
                            '<td>' +
                            '<button type="button" class="btn btn-info myupdate"' + 'onclick="umodify(' + user.uid + ')">修改</button>&nbsp;&nbsp;' +
                            '<button type="button" class="btn btn-warning" id="mydel"' + 'onclick="udel(' + user.uid + ')">删除</button>' +
                            '</td>'
                        );
                        strimg = "";
                        strche = "";
                    })
                }
                else if (currentPage > 1){
                    currentPage --;
                    renovate(currentPage);
                }
				//分页脚标 ：data.pageSize每页显示数， data.pageCount总的页数， data.rowCount总的行数
				createPageInfo(page, data.pagesize, data.pages, data.rowcount, renovate);
			},
            error:function() {
                alert("预期外的错误");
		    }
	    })
    };

    /*删除商品  */
    function udel(id) {
        if (confirm("确定删除吗")) {
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/delusers?id=" + id,
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                dataType:"text",
                success:function() {
                    renovate(currentPage);
                }
            })
        }
    }

    //进行跨页删除的数组增删
	function check(id) {
    	var flag = 0;
    	for (var i = 0 ; i < ids.length; i++) {
    		if (id == ids[i]) {
    			ids.splice(i, 1);
    			flag = 1;
			}
		}
    	if (flag == 0) {
    		ids.push(id);
		}
	}

    //批量删除商品（跨页）
    function udelBatch() {
        if(ids.length == 0){
			alert('请选择至少一条记录删除');
			return;
		}
		else {
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/batchdelusers?ids=" + ids,
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				dataType:"text",
				success:function() {
					renovate(currentPage);
				}
			})
		}
    }

    /*修改商品  */
    function umodify(id) {
        location.href = "${pageContext.request.contextPath}/getusersbyid?id="+id;
    }

    //创建分页
    function createPageInfo(currentPage, pageSize, pageCount, recordCount, callbackFunction){
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
			<form id="myform">
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
						<button type="button" class="btn btn-primary" onclick="renovate(1)">查询</button>
					</div>
				</div>
			</form>
		</div>
		<br>
		<div id="table">
			<div id="top">
				<input type="button" class="btn btn-warning mv" id="batchdelpro" onclick="udelBatch()" value="批量删除">
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
					<tbody id="users_list">
					</tbody>
				</table>
				<!--分页栏-->
				<div id="kkpager">
				</div>
			</div>
		</div>
	</div>
</body>
</html>