<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/4/26
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>主观题作答记录管理</title>
</head>
<body>
<div class="container-fluid">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">序号</th>
            <th class="col text-center">试题名称</th>
            <th class="col text-center">题号</th>
            <th class="col text-center">题型</th>
            <th class="col text-center">作答人</th>
            <th class="col text-center">作答时间</th>
            <th class="col text-center">相似程度</th>
            <th class="col text-center">得分</th>
        </tr>
        </thead>
        <tbody id="tbody_answer">
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
$(()=>{
    $.ajax({
        url:'<%=request.getContextPath()%>/subjectiveAnswerRecordBean',
        type:'POST',
        async:true,
        success:(data)=>{
            for(var i=0;i<data.data.length;++i){
                $('#tbody_answer').append('<tr class="row">\n' +
                    '            <td class="col text-center">'+(i+1)+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].examinationPaperByEId.name+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].id+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].questionByQId.problemByProblem.topicByType.name+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].studentBySId.name+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].time+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].similarity+'</td>\n' +
                    '            <td class="col text-center">'+data.data[i].core+'</td>\n' +
                    '        </tr>');
            }
        },
        error:()=>{
            Swal.fire({
                text:'网络问题!请刷新重试!',
                type:'error'
            })
        }
    });
});
</script>
</html>
