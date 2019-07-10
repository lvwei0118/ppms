<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看个人档案</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
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
    当前位置:档案>>个人档案信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="forms" action="${pageContext.request.contextPath}/arch/saveAInfo" method="POST">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;个人档案信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22" >员工姓名：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="ename" id="pname" readonly="true" value="${aList.employee.ename}" />
	</td>

	<td align="right" bgcolor="#FAFAF1" height="22">性别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="psex" readonly="true" value="${aList.employee.esex}"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">毕业院校：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text"  id="school" name="school" value="${aList.school}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">专业：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text"  id="zhuanye" name="zhuanye" value="${aList.zhuanye}"/>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22" >入职时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="hiredate" readonly="true" value="<fmt:formatDate value="${aList.employee.hiredate}" pattern="yyyy年MM月dd日"/>"/></td>

	<td align="right" bgcolor="#FAFAF1" height="22">民族：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="minzu" value="${aList.minzu}"/></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">毕业时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="biyedate" value="<fmt:formatDate value="${aList.biyedate}" pattern="yyyy年MM月dd日"/>"/></td>

	<td align="right" bgcolor="#FAFAF1" height="22">紧急联系人：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="sosperson" id="sosperson" value="${aList.sosperson}"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="level" id="level" readonly="true" value="${aList.level.jname}"/></td>
	</td>

	<td align="right" bgcolor="#FAFAF1" height="22">职位：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="position" id="position" readonly="true" value="${aList.position.name}"/></td>
</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">部门：</td>
		<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<input type="text" name="dname" id="dname" readonly="true" value="${aList.dept.dname}"/></td>
		</td>

		<td align="right" bgcolor="#FAFAF1" height="22">邮箱：</td>
		<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<input type="text" name="email"  value="${aList.email}" /></td>
	</tr>



<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea type="text" rows=15 cols=130 name="employee.remark">${aList.employee.remark}</textarea><span id="number"></span>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<A class="coolbg" onclick="commit()" >修改</A>
	<a href="${pageContext.request.contextPath}/arch/listAInfo" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>