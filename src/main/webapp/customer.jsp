<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
    <script type="text/javascript">
        /*查询*/
       $(function(){
            $.ajax({
                url:"${pageContext.request.contextPath}/cus/listInfo",
                type:"GET",
                success:function(msg){
                    $(msg).each(function (index,item) {
                        var tr="<tr align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';'onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22'>"
                            +"<td><input name='cid' type='checkbox' id='cid' value='"+item.id+"' class='np'></td>"
                            +"<td>"+(index+1)+"</td>"
                            +"<td>"+item.companyperson+"</td>"
                            +"<td align='center'>"+item.comname+"</td>"
                            +"<td>"+item.comaddress+"</td>"
                            +"<td>"+item.comphone+"</td>"
                            +"<td><a href='${pageContext.request.contextPath}/cus/findById/"+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/cus/findInfoById/"+item.id+"'>查看详情</a></td></tr>";

                        $(tr).insertBefore($("#focus-tr"));
                    })
                    
                },
            })
        })


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
        /*条件查询*/
        function searchLike(){
            var chid=$("select[name='chid']").val();
            var infoKey=$("input[name='infoKey']").val();
            var orderWord=$("select[name='orderWord']").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/cus/selectInfo",
                type:"GET",
                data:{"chid":chid,"infoKey":infoKey,"orderWord":orderWord},
                success:function(msg){
                    $("#list-table").find("tr[name!='avoid-delete']").remove();
                    $(msg).each(function (index,item) {
                        var tr="<tr align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';'onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22'>"
                            +"<td><input name='id' type='checkbox' id='id' value='"+item.id+"' class='np'></td>"
                            +"<td>"+(index+1)+"</td>"
                            +"<td>"+item.companyperson+"</td>"
                            +"<td align='center'>"+item.comname+"</td>"
                            +"<td>"+item.comaddress+"</td>"
                            +"<td>"+item.comphone+"</td>"
                            +"<td><a href='${pageContext.request.contextPath}/cus/findById/"+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/cus/findInfoById/"+item.id+"'>查看详情</a></td></tr>";

                        $(tr).insertBefore($("#focus-tr"));
                    });

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
    当前位置:客户信息管理>>客户信息
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='customer-add.jsp';" value='添加客户信息' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form1' action='javascript:void' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='chid' id="chid" style='...'>
          <option value='0'>选择类型...</option>
          	<option value='1'>客户所在公司名称</option>
          	<option value='2'>联系人姓名</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='infoKey' id="infoKey" value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderWord' id="orderWord" style='width:120px'>
            <option value='0'>排序...</option>
            <option value='1'>按id排序</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" onclick="searchLike()" src="skin/images/frame/search.gif"  width="45" height="20" border="0" class="np" />
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
<tr name="avoid-delete" bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr name="avoid-delete" align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">联系人</td>
	<td width="10%">公司名称</td>
	<td width="8%">公司地址</td>
	<td width="8%">联系电话</td>
	<td width="10%">操作</td>
</tr>
    <tr name="avoid-delete" id="focus-tr"></tr>
<%--<c:if test="${not empty customerList}">
    <c:forEach items="customerList" var="l" varStatus="vs">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="cid" type="checkbox" id="cid" value="${l.id}" class="np"></td>
	<td>vs</td>
	<td>${l.companyperson}</td>
	<td>${l.comname}</td>
	<td>${l.comaddress}</td>
	<td>${l.comphone}</td>
	<td><a href="${pageContext.request.contextPath}/cus//findById/${l.id}">编辑</a> | <a href="${pageContext.request.contextPath}/cus//findInfoById/${l.id}">查看详情</a></td>
</tr>
    </c:forEach>
</c:if>--%>

<tr name="avoid-delete" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
    <a href="javascript:checkAll()" class="coolbg">全选</a>
	<a href="javascript:selectinvert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>