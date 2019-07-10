<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
	<script type="application/javascript">
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
                        window.location.href="${pageContext.request.contextPath}/notice.jsp";
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
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form action="${pageContext.request.contextPath}/notice/searchNInfo" method="get">
	<table>
		<tr>
			<td>
				<input type="text" name="search_like_ntitle">
			</td>
			<td>
				<input type="submit" value="查询">
			</td>
		</tr>
	</table>

</form>

<!--  内容列表   -->
<form name="form2">

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="4%">序号</td>
	<td width="20%">标题</td>
	<td width="30%">内容</td>
	<td width="10%">发送时间</td>
	<td width="5%">操作</td>
</tr>
	<c:forEach items="${page.list}" var="ns" varStatus="vs">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${ns.nid}" class="np"></td>
	<td>${vs.count}</td>
	<td>${ns.ntitle}</td>
	<td align="center"><span>${ns.remark}</span></td>
	<td><fmt:formatDate value="${ns.ndate}" pattern="yyyy年MM月dd日"/></td>
	<td><a href="" >删除</a></td>
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