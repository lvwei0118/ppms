<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
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
    当前位置:邮件箱>>发件箱
 </td>
      <td>
          <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/email-seed.jsp';" value='发送新邮件' />
      </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid'>
          <option value='0'>选择类型...</option>
          	<option value='1'>标题</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='id'>排序...</option>
            <option value='pubdate'>发送时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table id="#list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="10%">收件人</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

    <c:forEach items="${email}" var="e" varStatus="vs">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${e.id}" class="np"></td>
            <td>${vs.count}</td>
            <td>${e.title}</td>
            <td align="center"><span>${e.content}</span></td>
            <td>${e.ename}</td>
            <td><fmt:formatDate value="${e.sendtime}" pattern="yyyy年MM月dd日"/></td>
            <td><a >删除</a></td>
        </tr>
    </c:forEach>


    <tr name="avoid-delete" bgcolor="#FAFAF1">
        <td height="28" colspan="12">
            &nbsp;
            <a href="javascript:checkAll()" class="coolbg">全选</a>
            <a href="javascript:selectinvert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
        </td>
    </tr>

</table>

</form>
  

</body>
</html>