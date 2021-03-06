<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>功能管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
    <script type="text/javascript">
        /*查询*/
        $(function(){
            $.ajax({
                url:"${pageContext.request.contextPath}/func/listFInfo",
                type:"GET",
                success:function(msg){
                    $(msg).each(function (index,item) {
                        var tr="<tr align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';'onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22'>"
                            +"<td><input name='id' type='checkbox' id='id' value='"+item.id+"' class='np'></td>"
                            +"<td>"+(index+1)+"</td>"
                            +"<td>"+item.functionname+"</td>"
                            +"<td>"+item.modname+"</td>"
                            +"<td>"+item.analysisname+"</td>"
                            +"<td>"+item.proname+"</td>"
                            +"<td>"+item.level+"</td>"
                            +"<td>"+item.simpledis+"</td>"
                            +"<td>"+item.detaileddis+"</td>"
                            +"<td><a href='${pageContext.request.contextPath}/func/findFById/"+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/func/findFInfoById/"+item.id+"'>查看详情</a></td></tr>";
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
                url:"${pageContext.request.contextPath}/func/delFInfo/"+ids,
                type:"POST",
                contentType:"application/json",
                success:function(msg){
                    if (msg.statusCode==200){
                        window.location.href="${pageContext.request.contextPath}/project-function.jsp";
                    }

                },
            });

        }

        /*条件查询*/
        function searchLike(){
            var phid=$("select[name='phid']").val();
            var infoKey=$("input[name='infoKey']").val();
            var orderWord=$("select[name='orderWord']").val();
            alert(chid+":"+infoKey+":"+orderWord);
            $.ajax({
                url:"${pageContext.request.contextPath}/func/selectFInfo",
                type:"GET",
                data:{"chid":chid,"infoKey":infoKey,"orderWord":orderWord},
                success:function(msg){
                    $("#list-table").find("tr[name!='avoid-delete']").remove();
                    $(msg).each(function (index,item) {
                        var tr="<tr align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';'onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22'>"
                            +"<td><input name='id' type='checkbox' id='id' value='"+item.id+"' class='np'></td>"
                            +"<td>"+(index+1)+"</td>"
                            +"<td>"+item.functionname+"</td>"
                            +"<td>"+item.modname+"</td>"
                            +"<td>"+item.analysisname+"</td>"
                            +"<td>"+item.proname+"</td>"
                            +"<td>"+item.level+"</td>"
                            +"<td>"+item.simpledis+"</td>"
                            +"<td>"+item.detaileddis+"</td>"
                            +"<td><a href='${pageContext.request.contextPath}/func/findFById/"+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/func/findFInfoById/"+item.id+"'>查看详情</a></td></tr>";

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
    当前位置:项目管理>>功能管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='project-function-add.jsp';" value='添加新功能' />
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
          	<option value='1'>项目名称</option>
          	<option value='2'>需求名称</option>
          	<option value='3'>模块名称</option>
          	<option value='4'>功能名称</option>
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

<table name="avoid-delete" id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;功能列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">功能名称</td>
	<td width="10%">模块名称</td>
	<td width="10%">需求名称</td>
	<td width="10%">项目名称</td>
	<td width="10%">优先级</td>
	<td width="8%">简单信息</td>
	<td width="8%">详细信息</td>
	<td width="10%">操作</td>
</tr>
    <tr name="avoid-delete" id="focus-tr"></tr>
<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td align="center"><a href=''><u>添加帐户功能</u></a></td>
	<td align="center"><a href=''><u>帐户管理模块</u></a></td>
	<td align="center"><a href=''><u>帐户管理需求分析</u></a></td>
	<td align="center"><a href=''><u>农业银行自助管理系统</u></a></td>
	<td>高</td>
	<td>2015-02-03</td>
	<td>2015-06-03</td>
	<td><a href="project-function-edit.jsp">编辑</a> | <a href="project-function-look.jsp">查看详情</a></td>
</tr>--%>


    <tr name="avoid-delete" bgcolor="#FAFAF1">
        <td height="28" colspan="12">
            &nbsp;
            <a href="javascript:checkAll()" class="coolbg">全选</a>
            <a href="javascript:selectinvert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
            <a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
        </td>
    </tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>