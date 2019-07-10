<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>任务信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
    <script type="text/javascript">
    /*$(function () {
        $("#status").each(function (index,item) {
            var endtime=$(this).parent().find("td:eq(7)").text();
            var starttime=$(this).parent().find("td:eq(6)").text();
            endtime=new Date(endtime);
            var end=endtime.getTime();
            starttime=new Date(starttime);
            var start=starttime.getTime();
            var timetep=Date.parse(new Date());
            if (start-timetep>0){
                $(this).text("未开始");
            }
            if (end-timetep>0&&start-timetep<0){
                $(this).text("进行中...");
            }
            if (end-timetep<0){
                $(this).text("已完成");
            }
        })

    })*/

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
                        +"<td>"+item.define+"</td>"
                        +"<td align='center'>"+item.emp.ename+"</td>"
                        +"<td>"+进行中+"</td>"
                        +"<td>"+item.starttime+"</td>"
                        +"<td>"+item.endtime+"</td>"
                        +"<td><a href='${pageContext.request.contextPath}/cus/findById/"+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/cus/findInfoById/"+item.id+"'>查看详情</a></td></tr>";

                    $(tr).insertBefore($("#focus-tr"));
                });

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
    当前位置:任务管理>>任务信息
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
        <tr> <td width='90' align='center'>状态：</td>
          <td width='160'>
          <select name='cid'>
          <option value='0'>请选择</option>
          	<option value='1'>未开始</option>
          	<option value='1'>进行中</option>
          	<option value='1'>已完成</option>
          </select>
        </td>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid'>
          <option value='0'>选择类型...</option>
          	<option value='1'>任务标题</option>
          	<option value='1'>执行者</option>
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
            <option value='pubdate'>开始时间</option>
            <option value='pubdate'>结束时间</option>
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

<table id="#list-table"  name="avoid-delete" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;任务信息&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">任务标题</td>
	<td width="8%">执行者</td>
	<td width="8%">状态</td>
	<td width="5%">优先级</td>
	<td width="12%">开始时间</td>
	<td width="12%">结束时间</td>
	<td width="10%">操作</td>
</tr>
    <tr name="avoid-delete" id="focus-tr"></tr>
    <c:forEach items="${tasks}" var="t" varStatus="vs">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${t.id}" class="np"></td>
            <td>${vs.count}</td>
            <td>${t.define}</td>
            <td align="center">${t.emp.ename}</td>
            <td align="center">${t.status}</td>
            <td align="center">${t.level}</td>
            <td>
                <fmt:formatDate value="${t.starttime}" pattern="yyyy年MM月dd日"/>
            </td>
            <td>
                <fmt:formatDate value="${t.endtime}" pattern="yyyy年MM月dd日"/>
            </td>
            <td><a href="${pageContext.request.contextPath}/task/findById/${t.id}">编辑</a> | <a href="${pageContext.request.contextPath}/task/findById/${t.id}">查看详情</a></td>
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
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>