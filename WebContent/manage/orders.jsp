<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>火车票订票系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="<%=Const.ROOT%>lib/layui-v2.5.4/css/layui.css" media="all">
<link rel="stylesheet" href="<%=Const.ROOT%>css/public.css" media="all">
</head>
<body>
	<div class="layuimini-container">
		<div class="layuimini-main">
			<fieldset class="layui-elem-field layuimini-search">
				<legend>搜索信息</legend>
				<div style="margin: 10px 10px 10px 10px">
					<form class="layui-form layui-form-pane" action="">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">站点</label>
								<div class="layui-input-inline">
									<input type="text" name="name" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<a class="layui-btn" lay-submit="" lay-filter="data-search-btn">搜索</a>
							</div>
						</div>
					</form>
				</div>
			</fieldset>
			<table class="layui-hide" id="currentTableId"
				lay-filter="currentTableFilter"></table>
			<script type="text/html" id="currentTableBar">
            	<a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        	</script>
		</div>
	</div>
	

	<script src="<%=Const.ROOT%>lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
	<script>
	
	 layui.use(['form', 'table','laydate','upload'], function () {
	        var $ = layui.jquery,
	            form = layui.form,
	            table = layui.table,
	        	laydate = layui.laydate,
	       		upload = layui.upload;
	       		
	       		
        table.render({
            elem: '#currentTableId',
            url: '<%=Const.ROOT%>orders/list',
            cols: [[
				{field:'id',hide:true,width: 0},
				{width: 80, templet:'#no',title: 'NO', sort: true},
                {templet: '#uid', width: 100, title: '用户'},
                {field: 'optime', width: 180, title: '下单时间'},
                {templet: '#cid', width: 90, title: '车次'},
                {templet: '#sid', width: 100, title: '座位类型'},
                {field: 'bsite', width: 100, title: '发站'},
                {field: 'esite', width: 100, title: '到站'},
                {field: 'btime', width: 130, title: '发车时间'},
                {field: 'etime', width: 130, title: '到达时间'},
                {field: 'price', width: 100, title: '票价'},
                {field: 'status', width: 100, title: '状态'},
                {title: '操作', minWidth: 60, templet: '#currentTableBar', fixed: "right", align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            //var result = JSON.stringify(data.field);
            //console.log(data.field);
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where:data.field
            }, 'data');

            return false;
        });

        // 监听添加操作
        $(".data-add-btn").on("click", function () {
        	var addIndex=layer.open({
        		title:"新增",
        		area: ['700px', '460px'],
        		type: 1, 
        		content: $("#popAdd")
        	});
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'view') {
               location.href="<%=Const.ROOT%>manage/site.jsp?cid="+data.id;
            }else 
            	if (obj.event === 'edit') {
                //layer.alert('编辑行：<br>' + JSON.stringify(data))
                layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 1,
                        title: "修改",
                        area: ['700px', '460px'],
                        content: $("#popUpdate")//引用的弹出层的页面层的方式加载修改界面表单
               });
              //表单初始赋值
               form.val('uexample', data);
               //动态向表传递赋值当然也是异步请求的要数据的修改数据的获取
               setFormValue(obj,data);    
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    //console.log(obj.data);
                    $.getJSON('<%=Const.ROOT%>checi/delete',{'id':obj.data.id},function(msg) {
  						//console.log(msg);
  						if(msg.code=="0"){
  							layer.msg(msg.msg, { icon: 6, time: 800},function(){
  								layer.close(index);
  								obj.del();
  								location.reload();
  							});
  						}else{
  							layer.msg(msg.msg, {icon: 5});
  						}	
  					}); 
                });
            }
        });
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
        	var url="<%=Const.ROOT%>checi/add";
            $.post(url,data.field,function(data){
            	//parent.location.reload();//用layer弹出的iframe则这样刷新父页面
            	if(data.code==1){//5错误，6正常
            		layer.msg(data.msg,{"icon":5,time:2000});
            	}else{
            		layer.msg(data.msg,{"icon":6,time:2000},function(){
            			location.href="<%=Const.ROOT%>manage/checi.jsp";
            		});
            	}	
            });
            return false;//return false是阻止提交
        });
        //监听弹出框表单提交，massage是修改界面的表单数据
          function setFormValue(obj,data){
              form.on('submit(update)', function(message) {
            	  $.ajax({
                  url:'<%=Const.ROOT%>checi/update',
					type : 'POST',
					data : message.field,
					dataType:"JSON",
					success : function(msg) {
						layer.closeAll();
						if(msg.code=="0"){
							layer.msg(msg.msg, { icon: 6, time: 800},function(){
									location.href="<%=Const.ROOT%>manage/checi.jsp";
									//	table.reload('currentTableId');//数据表格重
									layer.closeAll();//关闭弹出层
								});
							} else {
								layer.msg(msg.msg, {
									icon : 5
								});
							}
						}
					});
					return false;
				})
			}
        
          //详情
          form.on('submit(view)', function(message) {
				layer.closeAll();//关闭弹出层
		   });

		});
	 
	 function getTime(){     	//获取时间
	    	var date=new Date();
	    	var year=date.getFullYear();
	    	var month=date.getMonth()+1;
	    	var day=date.getDate();
	        //这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
	        if (month<10) {
	        	month='0'+month;
	        }
	        if (day<10) {
	        	day='0'+day;
	        }
	        return year+'-'+month+'-'+day;
	    }
	</script>
	<script type="text/html" id="no">
    {{d.LAY_TABLE_INDEX+1}}
	</script>
	<script type="text/html" id="uid">
    {{d.user.name}}
	</script>
	<script type="text/html" id="sid">
    {{d.seat.name}}
	</script>
	<script type="text/html" id="cid">
    {{d.checi.name}}
	</script>
</body>
</html>