<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>档案信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
	<script type="text/javascript">
        /*全选*/
        function checkAll(){
            $("#list-table").find("input").prop("checked",true);
        }
        /*反选*/
        function selectinvert(){
            $("#list-table").find("input").each(function () {
                var temp=$(this).prop("checked");
                $(this).prop("checked",!temp);
            })

        }


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
    当前位置:项目管理>>档案信息管理
 </td>

 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;员工档案信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">姓名</td>
	<td width="10%">年龄</td>
	<td width="10%">毕业院校</td>
	<td width="8%">入职时间</td>
	<td width="5%">联系方式</td>
	<td width="8%">学历</td>
	<td width="6%">邮箱</td>
	<td width="8%">政治面貌</td>
	<td width="8%">民族</td>	
	<td width="10%">操作</td>
</tr>
	<c:forEach items="${page.list}" var="as" varStatus="vs">
		<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			<td><input name="id" type="checkbox" id="${as.dnum}" value="101" class="np"></td>
			<td>${vs.count}</td>
			<td>${as.employee.ename}</td>
			<td>${as.employee.eage}</td>
			<td>${as.school}</td>
			<td align="center"><fmt:formatDate value="${as.employee.hiredate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td>${as.landline}</td>
			<td>${as.xueli}</td>
			<td>${as.email}</td>
			<td>${as.zzmm}</td>
			<td>${as.minzu}</td>
			<td><a href="">编辑</a> | <a href="">查看详情</a></td>

		</tr>
	</c:forEach>


	<tr name="avoid-delete" bgcolor="#FAFAF1">
		<td height="28" colspan="12">
			&nbsp;
			<a href="javascript:checkAll()" class="coolbg">全选</a>
			<a href="javascript:selectinvert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
			<a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
			<a href="${pageContext.request.contextPath}/archives-add.jsp" class="coolbg">&nbsp;添加档案信息&nbsp;</a>
		</td>
	</tr>



</table>

</form>

<jsp:include page="page.jsp"/>
</body>
</html>