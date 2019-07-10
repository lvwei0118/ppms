<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>main</title>
    <base target="_self">
    <link rel="stylesheet" type="text/css" href="skin/css/base.css" />
    <link rel="stylesheet" type="text/css" href="skin/css/main.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/for/listFInfo",
                type:"GET",
                success:function(msg){
                    $(msg).each(function (index,item) {
                     var li="<li class='ue-clear'>"
                           +"<span><img src='${pageContext.request.contextPath}/images/tx.png' height='50px' width='30px'/></span>发布时间："+item.postDate
                           +"<a href='${pageContext.request.contextPath}/for/selectFInfo/"+item.tid+"' class='notice-title'>"+item.title+"</a></li>"
                        $("#forumpost").append(li);
                    })
                }
            })
        })
    </script>

</head>
<body leftmargin="8" topmargin='8'>

<table width="98%" align="center" border="0" cellpadding="3"
       cellspacing="1" bgcolor="#CBD8AC"
       style="margin-bottom: 8px; margin-top: 8px;">
    <tr>
        <td colspan="3" background="skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
            class='title'>
            <span>员工论坛</span> |<span>生活广场</span> |<span>租房信息</span> |<a href='forum-add.jsp'><font size="5">发帖</font></a>
        </td>
    </tr>
    <tr bgcolor="#FFFFFF">
        <td>
            <ul class="notice-list" id="forumpost">

            </ul>
        </td>
    </tr>
</table>

</body>
</html>