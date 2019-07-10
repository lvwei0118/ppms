<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>创建任务</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
	<script type="text/javascript">
        /*项目名称*/
        $(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/anal/listNInfo",
                type:"GET",
                success:function(msg){
                    $(msg).each(function (index, item) {
                        $("#proname").append("<option value='"+item.title+","+item.id+"'>"+item.proname+"</option>")
                    });
                }
            })
        })
		/*对应需求*/
        function changeCeo(obj){
            var info=$(obj).val();
            var arr=info.split(',');
            $("#analysis").append("<option value='"+arr[1]+"' selected='selected'>"+arr[0]+"</option>");
            /*对应模块*/
            $.ajax({
                url:"${pageContext.request.contextPath}/mod/listMInfoByaf/"+arr[1],
                type:"GET",
                success:function(msg){
                    $("#module option").remove();
                    $("#module").append("<option>请选择模块</option>");
                    $(msg).each(function (index, item) {
                        $("#module").append("<option value='"+item.id+"'>"+item.modname+"</option>")
                    });
                }
            })
        }
        /*对应功能*/
        function choiseFunction(obj){
            var info=$(obj).val();
            $.ajax({
                url:"${pageContext.request.contextPath}/func/selectFInfoNoTask/"+info,
                type:"GET",
                success:function(msg){
                    $("#function option").remove();
                    $(msg).each(function (index, item) {
                        $("#function").append("<option value='"+item.id+"''>"+item.functionname+"</option>")
                    });
                }
            })
        }
        /*员工选择*/
        $(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/emp/listEInfo",
                type:"GET",
                success:function(msg){
                    $(msg).each(function (index, item) {
                        $("#ename").append("<option value='"+item.eid+"'>"+item.ename+"---"+item.position+"</option>")
                    });
                }
            })
        })

        /*提交表单*/
        function commit(){
            $("#form2").submit();
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
    当前位置:任务管理>>创建任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2"id="form2" action="${pageContext.request.contextPath}/task/saveTInfo" method="POST">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;创建任务&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="proname"  onchange="changeCeo(this)">
			<option>请选择项目</option>
		</select>-
		<select id="analysis">
			<option>请选择需求</option>
		</select>-
		<select id="module" onchange="choiseFunction(this)">
			<option>--------</option>
		</select>-
		<select id="function" name="funFk">
			<option>请选择功能</option>
		</select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="define"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime"/></td>
</tr>
<tr >
	<input type="hidden" name="empFk2" value="${activeUser.eid}">
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="ename" name="empFk">
			<option >请选择员工</option>
		</select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option>高</option>
			<option>中</option>
			<option>低</option>
			<option>暂缓</option>
		</select></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130>暂无</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a class="coolbg" onclick="commit()" >保存</a>
	<a href="task.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>