<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>报销管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
	<script type="text/javascript">

	</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='mybaoxiao-add.jsp';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<form action="${pageContext.request.contextPath}/bao/searchBInfo" method="get">
	<table>
		<tr>
			<td>
				<select name="search_bxstatus">
					<option value="">请选择状态</option>
					<option value="0">未审批</option>
					<option value="1">驳回</option>
					<option value="2">已审批</option>
					<option value="3">已付款</option>
				</select>
			</td>
			<td>
				<input type="text" name="search_like_bxremark">
			</td>
			<td>
				<input type="submit" value="查询">
			</td>
		</tr>
	</table>

</form>

<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="5%">编号</td>
	<td width="10%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="45%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

	<c:forEach items="${page.list}" var="bs" varStatus="vs">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>${vs.count}</td>
	<td>${bs.totalmoney}</td>
	<td align="center"> <fmt:formatDate value="${bs.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
	<td>${bs.bxremark}</td>

	<c:if test="${bs.bxstatus == 0}">
		<td>未审批</td>
		<td><a href="${pageContext.request.contextPath}/bao/findBById/${bs.bxid}">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 1}">
		<td>驳回</td>
		<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 2}">
		<td>审批通过</td>
		<td><a href="${pageContext.request.contextPath}/bao/findBById/${bs.bxid}">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 3}">
		<td>审批并付款</td>
		<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
	</c:if>

</tr>
	</c:forEach>



</table>

</form>

<jsp:include page="page.jsp"/>
</body>

</html>