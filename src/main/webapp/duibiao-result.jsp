<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
    <!-- 引入 ECharts 文件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts.common.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts.simple.min.js"></script>
        <script type="text/javascript">
            $(function () {
                var mychart=echarts.init(document.getElementById('main'));
                //1、显示数据加载提示动画
                mychart.showLoading();
                var option={
                    title:{
                        text:'营业额分析图'
                    },
                    legend:{
                        data:['营业额']
                    },
                    xAxis:{
                        data:[]
                    },
                    yAxis:{
                        type:'value',
                        axisLabel:{
                            formatter:'{value}(亿)',
                        },
                    },
                    series:[{
                        name:'营业额',
                        type:'bar',
                        data:[]
                    }],
                };
                mychart.setOption(option);
                //准备存储x轴数据，公司名数组
                var names=[];
                //准备营业额数组
                var scores=[];
                //2、启动ajax，请求数据
                $.ajax({
                    type:'GET',
                    url:'${pageContext.request.contextPath}/com/compare',
                    dataType:'json',
                    success:function(result){
                        //判断返回值是否存在
                        if(result!=null){
                            //json，遍历
                            $(result).each(function (index,item) {
                                names.push(item.company);
                                scores.push(item.targetmoney);
                            })
                            //隐藏加载动画
                            mychart.hideLoading();
                            //设置图表属性，把相关数据设置进去
                            mychart.setOption({
                                title:{
                                    text:'2018年公司营业额对比'
                                },
                                xAxis:{
                                    data:names
                                },
                                series:[{
                                    name:'营业额',
                                    type:'bar',
                                    data:scores
                                }]
                            });
                        }
                    }
                });
            });

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
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<div id="main" style="width:800px;height:600px;"></div>

</body>
</html>