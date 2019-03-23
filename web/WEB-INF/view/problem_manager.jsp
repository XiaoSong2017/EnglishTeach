<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/23
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>题目管理</title>
</head>
<body>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">序号</th>
            <th class="col text-center">题目编号</th>
            <th class="col text-center">试题类型</th>
            <th class="col text-center">出题时间</th>
            <th class="col text-center">对应课程</th>
            <th class="col text-center">出题人</th>
            <th class="col text-center">操作
                <div class="btn-group" role="group">
                    <a role="button" shape="circle" class="btn btn-outline-info" data-toggle="modal"
                       href="#">
                        <img role="img" class="img-fluid" src="<%=request.getContextPath()%>/images/add.svg"
                             type="svg" alt="img">
                    </a>
                </div>
            </th>
        </tr>
        </thead>
        <tbody class="popover-body" id="tbody_problem"></tbody>
    </table>
</div>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/problemBean',
            datatype: 'json',
            async: true,
            type: 'post',
            success: function (data) {
                for (var i = 0; i < data.data.length; ++i) {
                    $('#tbody_problem').append('<tr class="row">\n' +
                        '            <td class="col text-center">'+(i+1)+
                        '            </td>\n' +
                        '            <td class="col text-center"><a\n' +
                        '                    href="#?id='+data.data[i].id+'">'+data.data[i].id+
                        '            </a></td>\n' +
                        '            <td class="col text-center">'+data.data[i].topicByType.name +
                        '            </td>\n' +
                        '            <td class="col text-center">'+data.data[i].time +
                        '            </td>\n' +
                        '            <td class="col text-center">'+data.data[i].courseByCId.name +
                        '            </td>\n' +
                        '            <td class="col text-center">'+data.data[i].teacherByTId.name +
                        '            </td>\n' +
                        '            <td class="col text-center">\n' +
                        '                <div class="btn-group" role="group">\n' +
                        '                    <a href="#id='+data.data[i].id+'" class="btn '+(data.data[i].teacherByTId.name==='<%=request.getSession().getAttribute("user")%>'?'btn-outline-info':'btn-outline-light text-dark disabled')+'" role="button"\n' +
                        '                       aria-pressed="true">编辑</a>\n' +
                        '                    <a onclick="deleteExaminationPaperById(\''+data.data[i].id+'\',this)" class="btn '+(data.data[i].teacherByTId.name==='<%=request.getSession().getAttribute("user")%>'?'btn-outline-danger':'btn-outline-light text-dark disabled')+'" role="button"\n' +
                        '                       aria-pressed="true">删除</a>\n' +
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
    function deleteExaminationPaperById(id, obj) {
        if (confirm("确定删除？")) {
            $.ajax({
                url: '<%=request.getContextPath()%>/problemAction',
                type: 'post',
                async: true,
                data: {"problemId": id,"requestCode":"delete"},
                success: function (data) {
                    //console.log(data);
                    if (data.resultCode === 'success') {
                        $(obj).parent().parent().parent().remove();
                        var rows = $('#tbody_exam').children();
                        for (var i = 0; i < rows.length; ++i) {
                            rows.eq(i).children().eq(0).text(parseInt(i) + 1);
                        }
                        alert("已删除！");
                    } else {
                        alert("删除失败!请重试！");
                    }
                },
                error: function () {
                    alert("删除失败！请重试！");
                }
            });
        }
    }
</script>
</body>
</html>
