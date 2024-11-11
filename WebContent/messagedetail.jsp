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
								<h3>${v.title }</a></h3>
								<h5>发布于：<span>${v.optime }</span></h5>
								<p>作者：${v.user.name }</p>
								<p>${v.content }</p>
								<div class="count layui-clear">
								</div>
							</div>
						</div>	
						
						<div class="comt layui-clear">
							<a href="#comment" class="pull-right">回复</a>
						</div>
						<div id="LAY-msg-box">
							<c:forEach items="${list }" var="p" varStatus="st">
							<div class="info-item">
								<img class="info-img" src="<%=Const.ROOT %>res/static/images/info-img.png" alt="">
								<div class="info-text">
									<p class="title count">
										<span class="name">[#${st.count}]${p.user.name }</span>
										<span class="info-img like">${p.optime }</span>
									</p>
									<p class="info-intr">${p.content }</p>
								</div>
							</div>	
							</c:forEach>
							
							<a name="comment"></a>
							<c:if test="${sessionScope.role==1 }">
							<form class="layui-form" action="">
								<input type="hidden" name="mid" value="${v.id}"/>
								<div class="layui-form-item layui-form-text">
									<textarea name="content" class="layui-textarea" required="required" style="resize:none" placeholder="写点什么啊"></textarea>
								</div>
								<div class="btnbox">
								<button class="layui-btn" lay-submit="" lay-filter="add">回复</button>
							</div>
						</form>	
						</c:if>		
						</div>
					</div>
			</div>
		</div>
	<%@include file="footer.jsp" %>	
	<script src="<%=Const.ROOT %>res/layui/layui.js"></script>
	<script src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
	<script>
	layui.use(['form'], function () {
        var form = layui.form,
        layer = layui.layer;
      
        
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
        	var url="<%=Const.ROOT%>reply/add";
            $.post(url,data.field,function(data){
            	//parent.location.reload();//用layer弹出的iframe则这样刷新父页面
            	if(data.code==1){//5错误，6正常
            		layer.msg(data.msg,{"icon":5,time:2000});
            	}else{
            		layer.msg(data.msg,{"icon":6,time:2000},function(){
            			location.href="<%=Const.ROOT%>message/detail?id=${v.id}";
            		});
            	}	
            });
            return false;//return false是阻止提交
        });
	});
		layui.config({
		  base: '<%=Const.ROOT %>res/static/js/' 
		}).use('blog');
	</script>
</body>
</html>