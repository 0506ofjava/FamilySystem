<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>成员信息展示</title>
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
					<a href="${pageContext.request.contextPath}/member?method=getMemberList&currentPage=1" class="list-group-item text-center active">成员列表</a>
					<a href="${pageContext.request.contextPath}/member-add.jsp" class="list-group-item text-center ">新增成员</a>
				</div>
			</div>
			<!-- 右侧内容 -->
			<div class="col-md-9">
				<!-- 成功提示框 -->
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
					<strong>成功！</strong> 操作成功提示
				</div>
				<!-- 失败提示框 -->
				<div style="display: none" class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<strong>失败！</strong> 操作失败提示
				</div>
				<!-- 自定义内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">家庭成员列表</div>
					<div class="panel-body">
						<table class="table table-striped table-responsive table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>姓名</th>
									<th>年龄</th>
									<th>辈分</th>
									<th>性别</th>
									<th width="120">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${page.list}" var="member">
								<tr>
									<th>${member.id}</th>
									<td>${member.name}</td>
									<td>${member.age}</td>
									<c:if test="${member.type==0}" >
										<td>长辈</td>
									</c:if>
									<c:if test="${member.type==1}" >
										<td>平辈</td>
									</c:if>
									<c:if test="${member.type==2}" >
										<td>晚辈</td>
									</c:if>
									<td>${member.sex}</td>


									<td>
										<%--<a href="">详情</a>--%>
										<%-- pageContext.request.contextPath代表当前页面路径--%>
										<a href="${pageContext.request.contextPath}/member?method=deleteMember&id=${member.id}">删除</a>
										<a href="${pageContext.request.contextPath}/member-update.jsp?id=${member.id}&name=${member.name}&type=${member.type}&sex=${member.sex}">修改</a>
									</td>
								</tr>
							</c:forEach>


							</tbody>
						</table>
					</div>
				</div>

				<nav>
					<ul class="pagination pull-right">
						<li  class="previous"><a href="#">&laquo;</a></li>
						<c:forEach begin="1" end="${page.totalPage}" var="Page">
							<li><a href="${pageContext.request.contextPath}/member?method=getMemberList&currentPage=${Page}">${Page}</a></li>
						</c:forEach>
						<li><a href="#">&raquo;</a></li>
					</ul>

				</nav>

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
</body>
</html>