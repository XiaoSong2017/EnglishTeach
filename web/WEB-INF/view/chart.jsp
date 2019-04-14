<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/4/1
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>图表展示</title>
</head>
<body>
<div class="container-fluid">
    <div class="row align-items-center justify-content-center">
        <div class="m-auto">
            <div id="chart" style="width:800px;height:400px"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bmap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dataTool.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts.simple.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts.common.js"></script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('chart'));
    var app = {};
    option = null;
    app.title = '学习进度';
    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#8d3899'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['分数', '学习时间']
        },
        xAxis: [{
            type: 'value',
            name: '分钟',
            min: 0,
            axisPointer: {
                type: 'shadow'
            },
            axisLabel: {
                formatter: '{value} min'
            }
        }
        ],
        yAxis: [
            {
                type: 'value',
                min: 0,
                max: 100,
                interval: 10,
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    formatter: '{value} 分'
                }
            }
        ],
        series: [
            {
                name: '学习时间',
                type: 'line',
                color: '#ffee07',
                smooth: true,
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8]
            },
            {
                name: '分数',
                type: 'line',
                data: [20, 49, 70, 23.2, 56, 76.7, 10, 100, 32.6, 20.0]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>
