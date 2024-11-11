<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>火车票订票系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=Const.ROOT %>res/layui/css/layui.css">
	<link rel="stylesheet" href="<%=Const.ROOT %>res/static/css/mian.css">
</head>
<body class="lay-blog">
		<%@include file="top.jsp" %>
		<div class="container-wrap">
			<div class="container">
					<div class="contar-wrap">
						<c:forEach items="${list}" var="v" varStatus="st">
						<div class="item">
							<div class="item-box  layer-photos-demo1 layer-photos-demo">
								<h3>[${st.count }]<a href="<%=Const.ROOT%>message/detail?id=${v.id}">${v.title }</a></h3>
								<h5>${v.user.name }发表于：<span>${v.optime }</span></h5>
							</div>
						</div>
						</c:forEach>
					</div>
					<div class="item-btn">
						<button class="layui-btn layui-btn-normal" onclick="goPage(${pageBean.prevPage})">上一页</button>
						<button class="layui-btn layui-btn-normal" onclick="goPage(${pageBean.nextPage})">下一页</button>
					</div>
					<div>&nbsp;</div>
					<c:if test="${sessionScope.role==1 }">
					<form class="layui-form" method="post" action="<%=Const.ROOT%>message/add">
						<div class="layui-form-item layui-form-text">
							<input class="layui-input" name="title" style="resize:none" placeholder="留言标题" required/>
						</div>
						<div class="layui-form-item layui-form-text">
							<textarea class="layui-textarea" name="content" style="resize:none" placeholder="留言内容" required></textarea>
						</div>
						<div class="btnbox">
							<button class="layui-btn">发表</button>
						</div>
					</form>
					</c:if>
			</div>
		</div>
	<%@include file="footer.jsp" %>	
	<script src="<%=Const.ROOT %>res/layui/layui.js"></script>
	<script src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
	<script src="<%=Const.ROOT %>js/scroll.js"></script>

	<script>
	layui.config({
		  base: '<%=Const.ROOT %>res/static/js/' 
	}).use('blog');	
	
	
	function goPage(page){
		location.href="<%=Const.ROOT %>message/index?pageNo="+page;
	}
	
	</script>
</body>
</html>