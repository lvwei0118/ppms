<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>main</title>
<base target="_self">
<link rel="stylesheet" type="text/css" href="skin/css/base.css" />
<link rel="stylesheet" type="text/css" href="skin/css/main.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
	<script type="text/javascript">
        // 将最新的3条数据展示到页面
        $(function(){
            $.ajax({
                type:'GET',
                url:'${pageContext.request.contextPath}/notice/listNInfo',
                dataType:'json',
                success:function(resultData){
                    $(resultData).each(function(index,item){
                        var dateInfo= new Date(item.ndate);
                        var di= dateInfo.getFullYear()+"-"+(dateInfo.getMonth()+1)+"-"+dateInfo.getDate();
						 var li ="<li class='ue-clear'><span>"+di+"</span>&nbsp;&nbsp;&nbsp;<a onclick='showWindow("+item.nid+")' class='notice-title'>"+item.ntitle+"</a></li><p>";
                        $("#notices").append(li);
                    });
                },
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/for/listFInfo",
                type:"GET",
                success:function(msg){
                    $(msg).each(function (index,item) {
                        var li="<li class='ue-clear'>"
                            +"<span><img src='${pageContext.request.contextPath}/images/tx.png' height='50px' width='30px'/></span>发布时间："+item.postDate
                            +"<a href='${pageContext.request.contextPath}/for/selectFInfo/"+item.tid+"' class='notice-title'>"+item.title+"</a></li>"
                        $("#forum").append(li);
                    })
                }
            })
        });



        // 弹窗
        function showWindow(obj) {
            $('#showdiv').show();  //显示弹窗
            $('#cover').css('display','block'); //显示遮罩层
            $('#cover').css('height',document.body.clientHeight+'px'); //设置遮罩层的高度为当前页面高度

			$.ajax({
                type:'GET',
                url:'${pageContext.request.contextPath}/notice/showOneInfo',
				data:{"nid":obj},
                dataType:'json',
                success:function(resultData) {
                     console.log(resultData)
                     $("#ntitle").text(resultData.ntitle);
                     $("#content").text(resultData.remark);
                }
			});


        }
        // 关闭弹窗
        function closeWindow() {
            $('#showdiv').hide();  //隐藏弹窗
            $('#cover').css('display','none');   //显示遮罩层
        }

	</script>


</head>
<body leftmargin="8" topmargin='8'>
<!-- 遮罩层 -->
<div id="cover" style="background: #000; position: absolute; left: 0px; top: 0px; width: 100%; filter: alpha(opacity=30); opacity: 0.3; display: none; z-index: 2 ">

</div>
<!-- 弹窗 -->
<div id="showdiv" style="width: 60%; margin: 0 auto; height:500px; border: 1px solid #999; display: none; position: absolute; top: 20%; left: 20%; z-index: 3; background: #fff">
	<!-- 标题 -->
	<div id="ntitle" style="background: #F8F7F7; width: 100%; height: 5rem; font-size: 3rem; line-height: 5rem; border: 1px solid #999; text-align: center;" >

	</div>
	<!-- 内容 -->
	<div id="content" style="text-indent: 50px; height: 4rem; font-size: 2rem; padding: 2rem; line-height: 2rem; ">

	</div>
	<!-- 按钮 -->
	<div style="background: green; width: 10%; margin: 0 auto; height: 1.5rem; line-height: 1.5rem; text-align: center;color: #fff;margin-top: 28rem; -moz-border-radius: .128rem; -webkit-border-radius: .128rem; border-radius: .128rem;font-size: .59733rem;" onclick="closeWindow()">
		关闭
	</div>
</div>




	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><div style='float: left'>
					<img height="14" src="skin/images/frame/book1.gif" width="20" />&nbsp;欢迎使用项目平台管理系统
				</div>
				<div style='float: right; padding-right: 8px;'>
					<!--  //保留接口  -->
				</div></td>
		</tr>
		<tr>
			<td height="1" background="skin/images/frame/sp_bg.gif"
				style='padding: 0px'></td>
		</tr>
	</table>
<br>
    <table width="98%" align="center" border="0" cellpadding="4"
		cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom: 8px">
		<tr>
			<td colspan="2" background="skin/images/frame/wbg.gif"
				bgcolor="#EEF4EA" class='title'>
				<div style='float: left'>
					<span>快捷操作</span>
				</div>
				<div style='float: right; padding-right: 10px;'></div>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td height="30" colspan="2" align="center" valign="bottom"><table
					width="100%" border="0" cellspacing="1" cellpadding="1">
					<tr>
						<td width="15%" height="31" align="center"><img
							src="skin/images/frame/qc.gif" width="90" height="30" /></td>
						<td width="85%" valign="bottom"><div class='icoitem'>
								<div class='ico'>
									<img src='skin/images/frame/addnews.gif' width='16' height='16' />
								</div>
								<div class='txt'>
									<a href='project-base.html'><u>查看项目信息</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='skin/images/frame/menuarrow.gif' width='16'
										height='16' />
								</div>
								<div class='txt'>
									<a href='task-my.html'><u>查看任务</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='skin/images/frame/manage1.gif' width='16' height='16' />
								</div>
								<div class='txt'>
									<a href='task-add.html'><u>发布任务</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='skin/images/frame/file_dir.gif' width='16'
										height='16' />
								</div>
								<div class='txt'>
									<a href='message-give.html'><u>收件箱</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='skin/images/frame/part-index.gif' width='16'
										height='16' />
								</div>
								<div class='txt'>
									<a href='info.html'><u>个人信息</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='skin/images/frame/manage1.gif' width='16' height='16' />
								</div>
								<div class='txt'>
									<a href='modpassword.html'><u>修改密码</u></a>
								</div>
							</div></td>
					</tr>
				</table></td>
		</tr>
	</table>

<br>

	<table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
				class='title'><span>待完成任务</span></td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td>您有<a href=""><font color="red">2</font></a>个任务未完成……&nbsp;
			</td>
		</tr>
	</table>

<br>

	<table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
				class='title'><span>未读消息</span></td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td>您有<a href=""><font color="red">10</font></a>条未读消息……&nbsp;
			</td>
		</tr>
	</table>


<br>


	<table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
				class='title'><span>通知公告</span><a href='#'>更多...</a></td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td>
				<ul class="notice-list" id="notices">

				</ul>
			</td>
		</tr>
	</table>

<br>


<table width="98%" align="center" border="0" cellpadding="3"
	   cellspacing="1" bgcolor="#CBD8AC"
	   style="margin-bottom: 8px; margin-top: 8px;">
	<tr>
		<td colspan="3" background="skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
			class='title'>
			<span>员工论坛</span><a href='forum.jsp'>更多...</a>
		</td>
	</tr>

	<tr bgcolor="#FFFFFF">
		<td>
			<ul class="notice-list" id="forum">
			</ul>
		</td>
	</tr>
</table>

</body>
</html>