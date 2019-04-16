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
            <div id="chart" class="container-fluid" style="width:800px;height:400px"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bmap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dataTool.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts.simple.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts.common.js"></script>
<script type="text/javascript">
    $(() => {
        $.ajax({
            url: '<%=request.getContextPath()%>/studentLogBean',
            type: 'post',
            asynd: true,
            success: (data) => {
                var value=[];
                data.data.sort((a,b)=>{
                    return a.time-b.time;
                });
                for (var i = 0; i < data.data.length; ++i) {
                   value.push([data.data[i].time,data.data[i].core]);
                }
                var myChart = echarts.init(document.getElementById('chart'));
                var option = {
                    title: {
                        text: '学生成绩分析图',
                        color: '#000fff'
                    },
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
                        data: ['分数']
                    },
                    xAxis: [{
                        type: 'value',
                        name: '学生在线时长（小时）',
                        min: 0,
                        axisPointer: {
                            type: 'shadow'
                        },
                        axisLabel: {
                            formatter: '{value} h'
                        }
                    }],
                    yAxis: [
                        {
                            type: 'value',
                            name: '学生所选课程平均分（分数）',
                            min: 0,
                            max: 100,
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
                            name: '分数',
                            type: 'line',
                            smooth: true,
                            data: value
                        }
                    ]
                };
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            },
            error: () => {
                Swal.fire({
                    text: '网络出错！请刷新重试！',
                    type: 'error'
                });
            }
        });
    });
</script>
</body>
</html>
