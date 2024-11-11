<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	session.setAttribute("active","orders");
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>火车票订票系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=Const.ROOT %>res/layui/css/layui.css">
	<link rel="stylesheet" href="<%=Const.ROOT %>res/static/css/mian.css">
	<style>
	.renav{
	    height:75px;
	    line-height:75px;
	    overflow:hidden;
	    background:#FFFFFF;
	    margin-bottom:10px;
	}
	.renav li{
	    height:75px;
	}
	.layui-table .title{
		font-weight:700;
	}
	
	</style>
</head>
<body class="lay-blog">
		<%@include file="top.jsp" %>
		<div class="container-wrap">
			<div class="container" style="width:1190px">
					<div class="contar-wrap" style="width:1190px;">
						<div class="renav">
						<h4 class="item-title">
							<c:forEach items="${glist}" var="g">
							<p><i class="layui-icon layui-icon-speaker"></i>公告：<span><a href="<%=Const.ROOT%>gonggao/detail?id=${g.id}">${g.title}</a></span><span style="float:right;margin-right:20px;">${g.optime }</span></p>
							</c:forEach>
						</h4>
						</div>
						<h2 class="layui-bg-red" style="line-height:45px;text-align:center;">我的订单</h2>
						<hr class="layui-bg-orange">
						
						<div class="item" style="padding-bottom:20px;">
							<table class="layui-table">
								<tr>
									<td>编号</td>
									<td>用户</td>
									<td>下单时间</td>
									<td>车次</td>
									<td>发站</td>
									<td>到站</td>
									<td>座位</td>
									<td>价格</td>
									<td>发车时间</td>
									<td>到达时间</td>
									<td>状态</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${list }" var="v" varStatus="st">
									<tr>
									<td>${st.count }</td>
									<td>${v.user.name}</td>
									<td>${v.optime}</td>
									<td>${v.checi.name }</td>
									<td>${v.bsite }</td>
									<td>${v.esite }</td>
									<td>${v.seat.name }</td>
									<td>${v.price }</td>
									<td>${v.btime }</td>
									<td>${v.etime }</td>
									<td>${v.status }</td>
									<td>
										<c:if test="${v.status=='未取票' }">
											<button class="layui-btn layui-btn-sm layui-btn-normal" type="button" onclick="qu(${v.id})">取票</button>
										</c:if>
										<c:if test="${v.status=='已取票' }">
											<button class="layui-btn layui-btn-sm layui-btn-warm" type="button" onclick="tui(${v.id},'${v.optime }')">退票</button>
										</c:if>
										<button class="layui-btn layui-btn-sm layui-btn-danger" type="button" onclick="del(${v.id})">删除</button>
									</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<hr class="layui-bg-gray">
						
					</div>
					
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
	
	function qu(id){
		layer.confirm('是否取票', function (index) {
            $.getJSON('<%=Const.ROOT %>orders/update',{'id':id,'status':'已取票'},function(msg) {
					if(msg.code=="0"){
						layer.msg(msg.msg, { icon: 6, time: 800},function(){
							location.reload();
						});
					}else{
						layer.msg(msg.msg, {icon: 5});
					}	
				}); 
        });
	}
	
	function tui(id,optime){
		
		layer.confirm('是否退票', function (index) {
            $.getJSON('<%=Const.ROOT %>orders/update',{'id':id,'status':'已退票'},function(msg) {
					if(msg.code=="0"){
						layer.msg(msg.msg, { icon: 6, time: 800},function(){
							location.reload();
						});
					}else{
						layer.msg(msg.msg, {icon: 5});
					}	
				}); 
        });
	}
	function del(id){
		layer.confirm('是否删除', function (index) {
            $.getJSON('<%=Const.ROOT %>orders/delete',{'id':id},function(msg) {
					if(msg.code=="0"){
						layer.msg(msg.msg, { icon: 6, time: 800},function(){
							location.reload();
						});
					}else{
						layer.msg(msg.msg, {icon: 5});
					}	
				}); 
        });
	}
	
	function getTime(){     	//获取时间
    	var date=new Date();
    	var year=date.getFullYear();
    	var month=date.getMonth()+1;
    	var day=date.getDate();
    	 var hour=date.getHours();
         var minute=date.getMinutes();
         var second=date.getSeconds();
        //这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
        if (month<10) {
        	month='0'+month;
        }
        if (day<10) {
        	day='0'+day;
        }
        if (hour<10) {
        	hour='0'+hour;
        }
        if (minute<10) {
        	minute='0'+minute;
        }
        if (second<10) {
        	second='0'+second;
        }
        return year+'-'+month+'-'+day+" "+hour+":"+minute+":"+second;
    }
	</script>
</body>
</html>