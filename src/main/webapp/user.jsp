<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户管理</title>
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
        /*删除*/
        function del(){
            if (!confirm("是否确认删除？")) {
                return;
            }
            var ids="";
            $("#list-table").find("input:checked").each(function (index,item){
                ids=ids+$(this).val()+",";
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/cus/delInfo/"+ids,
                type:"POST",
                contentType:"application/json",
                success:function(msg){
                    if (msg.statusCode==200){
                        window.location.href="${pageContext.request.contextPath}/customer.jsp";
                    }

                },
            });

        }
        function exportExcel() {
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/cus/export",
                success:function (result) {
                    if (result.statusCode==200){
                        alert(result.msg);
                    }
                }
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
    当前位置:权限管理>>用户管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/user-add.jsp';" value='添加用户' />
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
          	<option value='1'>姓名</option>
          	<option value='1'>联系电话</option>
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
            <option value='pubdate'>添加时间</option>
            <option value='pubdate'>修改时间</option>
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

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;用户列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">姓名</td>
	<td width="10%">职位</td>
	<td width="5%">性别</td>
	<td width="5%">年龄</td>
	<td width="10%">联系电话</td>
	<td width="10%">入职时间</td>
	<td width="15%">操作</td>
</tr>
<c:forEach items="${page.list}" var="u" varStatus="vs">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${u.eid}" class="np"></td>
	<td>${vs.count}</td>
	<td>${u.ename}</td>
	<td align="center">${u.position}</td>
	<td>${u.esex}</td>
	<td>${u.eage}</td>
	<td>${u.telephone}</td>
	<td><fmt:formatDate value="${u.hiredate}" pattern="yyyy年MM月dd日"/></td>
	<td><a >删除</a> | <a href="user-edit.jsp">编辑</a> | <a href="user-look.jsp">查看详情</a></td>
</tr>
</c:forEach>

    <tr name="avoid-delete" bgcolor="#FAFAF1">
        <td height="28" colspan="12">
            &nbsp;
            <a href="javascript:checkAll()" class="coolbg">全选</a>
            <a href="javascript:selectinvert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
            <a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
        </td>
    </tr>

</table>

</form>

<jsp:include page="page.jsp"/>
</body>
</html>