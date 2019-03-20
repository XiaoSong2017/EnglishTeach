<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/3
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>作业管理</title>
</head>
<body>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">序号</th>
            <th class="col text-center">试题号</th>
            <th class="col text-center">试题名称</th>
            <th class="col text-center">开考时间</th>
            <th class="col text-center">结束时间</th>
            <th class="col text-center">出题人</th>
            <th class="col text-center">操作
                <div class="btn-group" role="group">
                    <a role="button" shape="circle" class="btn btn-outline-info" data-toggle="modal">
                        <img role="img" class="img-fluid" src="<%=request.getContextPath()%>/images/add.svg"
                             type="svg" alt="img">
                    </a>
                </div>
            </th>
        </tr>
        </thead>
        <tbody class="popover-body" id="tbody_homework">
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/examinationPaperBean',
            datatype: 'json',
            async: true,
            type: 'post',
            data: {teacherId: '<%=request.getSession().getAttribute("ID")%>', type: true},
            success: function (data) {
                for(var i=0;i<data.data.length;++i) {
                    $('#tbody_homework').append('<tr class="row">\n' +
                        '            <td class="col text-center">'+(parseInt(i)+1)+'</td>\n' +
                        '            <td class="col text-center">'+data.data[i].id+'</td>\n' +
                        '            <td class="col text-center">'+data.data[i].name+'</td>\n' +
                        '            <td class="col text-center">'+data.data[i].startTime+'</td>\n' +
                        '            <td class="col text-center">'+data.data[i].endTime+'</td>\n' +
                        '            <td class="col text-center">'+'<%=request.getSession().getAttribute("user")%>'+'</td>\n' +
                        '            <td class="col text-center">\n' +
                        '                <div class="btn-group" role="group">\n' +
                        '                    <a class="btn btn-outline-info" data-toggle="modal">修改</a>\n' +
                        '                    <a class="btn btn-outline-danger" onclick="deleteExaminationPaperById(\''+data.data[i].id+'\',this)">删除</a>\n' +
                        '                </div>\n' +
                        '            </td>\n' +
                        '        </tr>');
                }
            },
            error: function () {
                alert("加载失败！请刷新页面重试！");
            }
        });
    });
</script>
</html>
