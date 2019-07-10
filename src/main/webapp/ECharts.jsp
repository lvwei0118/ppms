<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--数据图--%>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/images/jquery-1.8.1.js"></script>
    <!-- 引入 ECharts 文件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts.common.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts.simple.min.js"></script>
    <script type="text/javascript">
        window.onload=function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            /*var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };*/

            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                angleAxis: {
                    type: 'category',
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                    z: 10
                },
                radiusAxis: {
                },
                polar: {
                },
                series: [{
                    type: 'bar',
                    data: [1, 2, 3, 4, 3, 5, 1],
                    coordinateSystem: 'polar',
                    name: 'A',
                    stack: 'a'
                }, {
                    type: 'bar',
                    data: [2, 4, 6, 1, 3, 2, 1],
                    coordinateSystem: 'polar',
                    name: 'B',
                    stack: 'a'
                }, {
                    type: 'bar',
                    data: [1, 2, 3, 4, 1, 2, 5],
                    coordinateSystem: 'polar',
                    name: 'C',
                    stack: 'a'
                }],
                legend: {
                    show: true,
                    data: ['A', 'B', 'C']
                }
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option,true);
            window.addEventListener('resize', function() {
                myChart.resize();
            })
        }
    </script>

</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
