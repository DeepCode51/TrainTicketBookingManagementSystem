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
			<div class="container container-message container-details">
					<div class="contar-wrap">
						<div class="item">
							<div class="item-box  layer-photos-demo1 layer-photos-demo">
								<h3>${g.title }</a></h3>
								<h5>发布于：<span>${g.optime }</span></h5>
								<p>${g.content }</p>
								<div class="count layui-clear">
								</div>
							</div>
						</div>	
						<div class="comt layui-clear">
							<button class="layui-btn layui-btn-normal pull-right" onclick="javascript:history.go(-1)">返回</button>
						</div>
					</div>
			</div>
		</div>
	<%@include file="footer.jsp" %>	
	<script src="<%=Const.ROOT %>res/layui/layui.js"></script>
	<script src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
	<script>
	layui.config({
	  base: '<%=Const.ROOT %>res/static/js/' 
	}).use('blog');
	
	layui.use(['form'], function () {
        var form = layui.form,
        layer = layui.layer;
	});
	</script>
</body>
</html>