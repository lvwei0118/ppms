<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>角色管理</title>
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
                url:"${pageContext.request.contextPath}/anal/delNInfo/"+ids,
                type:"POST",
                contentType:"application/json",
                success:function(msg){
                    if (msg.statusCode==200){
                        window.location.href="${pageContext.request.contextPath}/project-need.jsp";
                    }

                },
            });

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
    当前位置:权限管理>>角色管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/role-add.jsp';" value='添加角色' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form name="form2">

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;角色列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">编号</td>
    <td width="10%">角色名称</td>
	<td width="10%">角色描述</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>
<c:forEach items="${rl.list}" var="r" varStatus="vs">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${r.roleid}" class="np"></td>
	<td>${vs.count}</td>
	<td>${r.rolename}</td>
	<td align="center">${r.roledis}</td>
	<td>${r.status}</td>
	<td><a href="${pageContext.request.contextPath}/role/deleteNode/'+${r.roleid}+'" >删除</a> |<a href="${pageContext.request.contextPath}/role/getOneById/'+${r.roleid}+'">编辑</a> | <a href="${pageContext.request.contextPath}/role/lookOneById/'+${r.roleid}+'">查看详情</a></td>
</tr>
</c:forEach>

	<tr bgcolor="#FAFAF1">
		<td height="28" colspan="12">
			&nbsp;
			<a href="javascript:checkAll()" class="coolbg">全选</a>
			<a href="javascript:selectinvert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="" class="coolbg">&nbsp;禁用&nbsp;</a>
		</td>
	</tr>


</table>

</form>

<jsp:include page="page.jsp"/>
</body>
</html>