<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>修改成员信息</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">    
	<style type="text/css">
		body{ font-family: 'Microsoft YaHei';}
		/*.panel-body{ padding: 0; }*/
	</style>
</head>
<body>
<div class="jumbotron">
	<div class="container">

  		<h3>——家庭信息管理系统</h3>
  		
	</div>
</div>
<div class="container">
	<div class="main">
		<div class="row">
			<!-- 左侧内容 -->
			<div class="col-md-3">
				<div class="list-group">
					<a href="${pageContext.request.contextPath}/member?method=getMemberList&currentPage=1" class="list-group-item text-center ">成员列表</a>
					<a href="${pageContext.request.contextPath}/member-add.jsp" class="list-group-item text-center ">新增成员</a>
					<a href="${pageContext.request.contextPath}/member-update.jsp" class="list-group-item text-center active">修改成员</a>
				</div>
			</div>
			<!-- 右侧内容 -->
			<div class="col-md-9">
				<!-- 错误提示 -->

				<div id="sucess-info" style="display: none" class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
					<strong>成功！</strong> 操作成功提示
				</div>
				<!-- 失败提示框 -->
				<div id="fail-info" style="display: none" class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<strong>失败！</strong> 操作失败提示
				</div>
				<!-- 自定义内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">修改家庭成员信息</div>
					<div class="panel-body">
						<form action="member" method="post" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-2 control-label">名称</label>
								<input hidden name="method" value="updateMember">
								<div class="col-sm-5">
									<input type="text" name="name" value="${param.name}" class="form-control" placeholder="成员名称">
								</div>
								<div class="col-sm-5">
									<p class="form-control-static text-danger">名称不能为空</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">性别</label>
								<div class="col-sm-5">
									<input type="text" name="sex"  value="${param.sex}" class="form-control" placeholder="性别">
								</div>
								<input type="hidden" name="id" value="${param.id}">
								<div class="col-sm-5">
									<p class="form-control-static text-danger">性别不能为空</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">年龄</label>
								<div class="col-sm-5">
									<input type="text" name="age" value="${param.age}" class="form-control" placeholder="年龄">
								</div>
								<div class="col-sm-5">
									<p class="form-control-static text-danger">年龄不能为空</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">辈分</label>
								<div class="col-sm-5">
									<label class="radio-inline">
										<input <c:if test="${param.type==0}">checked="checked"</c:if> type="radio" name="type" value="0">长辈
									</label>
									<label class="radio-inline">
										<input <c:if test="${param.type==1}">checked="checked"</c:if> type="radio" name="type" value="1">平辈
									</label>
									<label class="radio-inline">
										<input <c:if test="${param.type==2}">checked="checked"</c:if> type="radio" name="type" value="2">晚辈
									</label>
								</div>
								<div class="col-sm-5">
									<p class="form-control-static text-danger">请选择辈分</p>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary">修改</button>
								</div>
							</div>
						</form>

					</div>
				</div>
				
				
			</div>
		</div>
  	</div>
</div>
<!-- 尾部 -->
<div class="jumbotron" style=" margin-bottom:0;margin-top:105px;">
	<div class="container">
	<span>&copy; 2019 Yiqiang</span>
	</div>
</div>

	<script src="static/js/jquery-3.1.0.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
<%
	System.out.println(response.getStatus()+"--------------status");
	if (response.getStatus()==200) {

	}else if(response.getStatus()==201){
		out.write("<script type=\"text/javascript\">\n" +
				"    window.onload=function(){\n" +
				"        showdiv();\n" +
				"       }</script>");
	}
	else {
		out.write("<script type=\"text/javascript\">\n" +
				"    window.onload=function(){\n" +
				"        offdiv();\n" +
				"       }</script>");
	}

%>


<script>


    function showdiv(){

    if(showdiv_display = document.getElementById('sucess-info').style.display=='none'){//如果show是隐藏的

        document.getElementById('sucess-info').style.display='block';//show的display属性设置为block（显示）
        document.getElementById('fail-info').style.display='none';//show的display属性设置为block（显示）
    }else{//如果show是显示的
        document.getElementById('fail-info').style.display='block';
        document.getElementById('show').style.display='none';//show的display属性设置为none（隐藏）

    }

}
function offdiv(){

   //如果show是显示的
        document.getElementById('fail-info').style.display='block';
        document.getElementById('sucess-info').style.display='none';//show的display属性设置为none（隐藏）



}
</script>

</body>
</html>