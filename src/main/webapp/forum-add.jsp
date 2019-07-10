<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发帖</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/utf8-jsp/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/utf8-jsp/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
	<script type="application/javascript">
		$(function () {
			//实例化编辑器
            //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
            var ue = UE.getEditor('editor');
        })

        function commit(){
             $("#form3").submit();
		}
	</script>
	<style type="text/css">
		div{
			width:100%;
		}
	</style>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" c1ellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:论坛>>发帖
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<div>
<form name="form2"  action="${pageContext.request.contextPath}/for/sendFor" id="form3" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;发帖&nbsp;</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="title"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">内容：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<script id="editor"  type="text/plain" style="width:1024px;height:500px;" name="content"></script>
	</td>
</tr>
	<tr bgcolor="#FAFAF1">
	<td height="28" colspan=4 align=center>
		&nbsp;
		<a href="javascript:commit()" class="coolbg">发布</a>
		<a href="javascript:history.go(-1)" class="coolbg">返回</a>
	</td>
	</tr>
</table>

</form>
</div>

</body>
</html>