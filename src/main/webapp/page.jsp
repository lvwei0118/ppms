<%--
  用于分页显示页码的工具
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    $(function () {
        /*失去焦点事件*/
        $("#inputPage").blur(function () {
            var pageNo=$("inputPage").val();
            var reg=/^[1-9]\d*$/;
            if (!reg.test(pageNo)){
                alert("请输入正确的页码");
                return;
            } else {
                window.location.href="${url}?pageNum="+pageNo${queryString};
            }
        })
    })

</script>
<div align="center">
    <a href="${url}?pageNum=1${queryString}">首页</a>&nbsp;
    <a href="${url}?pageNum=${page.prePage}${queryString}">上一页</a>&nbsp;&nbsp;
    <c:forEach items="${page.navigatepageNums}" var="pageNo">
        <c:if test="${pageNo==page.pageNum}">
            【<span style="color: #1f6eff">${pageNo}</span>】
        </c:if>

        <c:if test="${pageNo!=page.pageNum}">
            <a href="${url}?pageNum=${pageNo}${queryString}">${pageNo}</a>&nbsp;
        </c:if>
    </c:forEach>

    <a href="${url}?pageNum=${page.nextPage}${queryString}">下一页</a>&nbsp;
    <a href="${url}?pageNum=${page.pages}${queryString}">末页</a>&nbsp;&nbsp;
    跳转到<input id="inputPage" type="text" size="1px">页
</div>