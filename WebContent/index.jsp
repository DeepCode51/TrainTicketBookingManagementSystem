<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	session.setAttribute("active","index");
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
	.pw{
		position:relative;float:left;text-align:center;margin:10px;
	}
	.pw span{
		position:absolute;font-size:16px;font-weight:700;color:#ddd;left:55px;top:118px;
	}
	.pw div{
		margin-top:10px;
		color:#1E9FFF;
		font-weight:700;
		font-size:1.2em;
	}
	</style>
</head>
<body class="lay-blog">
		<%@include file="top.jsp" %>
		<div class="container-wrap">
			<div class="container" style="width:1190px">
					<div class="contar-wrap" style="width:1190px">
						<div class="renav">
						<h4 class="item-title">
							<c:forEach items="${glist}" var="g">
							<p><i class="layui-icon layui-icon-speaker"></i>公告：<span><a href="<%=Const.ROOT%>gonggao/detail?id=${g.id}">${g.title}</a></span><span style="float:right;margin-right:20px;">${g.optime }</span></p>
							</c:forEach>
						</h4>
						</div>
						<form class="layui-form" action="" lay-filter="example" method="post">
						<div class="layui-form-item layui-bg-blue" style="line-height:65px;text-align:center;">
						  <div class="layui-inline">
						    <div class="layui-input-inline" style="width: 100px;">
						      <input type="text" name="bsite" lay-verify="required" placeholder="始发站" autocomplete="off" class="layui-input" value="${bsite }">
						    </div>
						    <div class="layui-form-mid">-</div>
						    <div class="layui-input-inline" style="width: 100px;">
						      <input type="text" name="esite" lay-verify="required" placeholder="终点站" autocomplete="off" class="layui-input" value="${esite }">
						    </div>
						  </div>
						  
						  <div class="layui-inline">
						   <button lay-submit="" lay-filter="search" class="layui-btn layui-btn-warm ">查询</button>
						  </div>
						</div>
						</form>
						<hr class="layui-bg-orange">
						<div class="item" style="padding-bottom:20px;">
							<table class="layui-table">
								<tr>
									<td>编号</td>
									<td>车次</td>
									<td>起点</td>
									<td>终点</td>
									<td width="25%">站点</td>
									<td width="25%">票价</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${list }" var="v" varStatus="st">
									<tr>
									<td>${st.count }</td>
									<td>${v.name}</td>
									<td>${bsite}</td>
									<td>${esite}</td>
									<td>${v.sites}</td>
									<td id="s${v.id}">${v.seats}</td>
									<td>
									<c:if test="${v.seats!='需要中转，请分开预订!'}">
										<button data="${v.id}" class="layui-btn layui-btn-danger buy" type="button">预订</button></td>
									</c:if>
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
	
	//加载需要的模块
    layui.use(['form','layer','laydate'], function () {
    	var $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;
       
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(search)', function (data) {
        	 location.href="<%=Const.ROOT %>index?bsite="+data.field.bsite+"&esite="+data.field.esite;
        	 /*$.post("<%=Const.ROOT %>index",data.field,function(data){
             	
             });*/
            return false;
        });
	
        $(".buy").click(function(){
        	if('${sessionScope.role}'==''){
        		alert("请先登录!");
        		return;
        	}
        	var id=$(this).attr("data");
        	//console.log(id);
        	var rad=$("#s"+id).find("input[name='sid']:checked");
        	if(rad.length==0){
        		layer.msg("请先选择座位类型",{icon:5});
        	}else{
        		var sid=rad.val();
        		//console.log(sid);
        		var price=$(".p"+sid).text();
        		//console.log(price);
        		
        		 $.getJSON('<%=Const.ROOT%>orders/add',{'cid':id,'sid':sid,'price':price,'bsite':'${bsite}','esite':'${esite}'},function(msg) {
						if(msg.code=="0"){
							layer.msg(msg.msg, { icon: 6, time: 800},function(){
								location.href="<%=Const.ROOT%>orders/index?id=${sessionScope.users.id}";
							});
						}else{
							layer.msg(msg.msg, {icon: 5});
						}	
					}); 
        	}
        	
        });
        
    });
	
	
	
	</script>
</body>
</html>