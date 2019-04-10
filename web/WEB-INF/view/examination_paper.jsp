<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/4/8
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>试题（作业）展示</title>
</head>
<body>
<div id="parent_examination" class="container-fluid">
    <%--<div class="card">--%>
        <%--<div class="card-header">--%>
            <%--<a class="card-link" data-toggle="collapse" href="#" onclick="onclickCollapse(this)">--%>
                <%--第<label>1</label>题：--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="collapse" data-parent="#parent_examination">--%>
            <%--<div class="card-body">--%>
                <%--<div class="radio">--%>
                    <%--<label><input type="radio" name="optradio">Option 1</label>--%>
                <%--</div>--%>
                <%--<div class="radio">--%>
                    <%--<label><input type="radio" name="optradio">Option 2</label>--%>
                <%--</div>--%>
                <%--<div class="radio disabled">--%>
                    <%--<label><input type="radio" name="optradio">Option 3</label>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="card">--%>
        <%--<div class="card-header">--%>
            <%--<a class="card-link" data-toggle="collapse" href="#" onclick="onclickCollapse(this)">--%>
                <%--第<label>2</label>题：--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="collapse" data-parent="#parent_examination">--%>
            <%--<div class="card-body">第二部分</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>
<script type="text/javascript">
    function onclickCollapse(obj) {
        $(obj).parent().next().collapse('toggle');
    }

    $(() => {
        $.ajax({
            url: '<%=request.getContextPath()%>/getExaminationPaperDetailById',
            type: 'post',
            async: true,
            data: {'id': 1},
            success: (data) => {
                if (data.resultCode === 'success') {
                    var problem = data.list.problem;
                    problem.sort((a, b) => {
                        return parseInt(a.problemNumber) - parseInt(b.problemNumber);
                    });
                    //console.log(problem);
                    for (var i = 0; i < problem.length; ++i) {
                        if (problem[i].type === 1 || problem[i].type === 2 || problem[i].type === 3) {
                            var question=problem[i].question;
                            question.sort((a,b)=>{
                                return parseInt(a.questionNumber)-parseInt(b.questionNumber);
                            });
                            for(var j=0;j<question.length;++j){
                                var option=question.option;
                                option.sort((a,b)=>{
                                    return a.mark-b.mark;
                                });
                                console.log(option);
                                for(var k=0;k<option.length;++k){

                                }
                            }
                            $('#parent_examination').append('<div class="card">\n' +
                                '        <div class="card-header">\n' +
                                '            <a class="card-link" data-toggle="collapse" href="#" onclick="onclickCollapse(this)">\n' +
                                '                第<label>'+(i+1)+'</label>题：\n' +
                                '            </a>\n' +
                                '        </div>\n' +
                                '        <div class="collapse" data-parent="#parent_examination">\n' +
                                '            <div class="card-body">' +
                                '</div>\n' +
                                '        </div>\n' +
                                '    </div>');
                        }
                    }
                } else {
                    Swal.fire({
                        text: '加载失败！,请刷新重试！',
                        type: 'error'
                    });
                }
            },
            error: () => {
                Swal.fire({
                    text: '网络出错！,请刷新重试！',
                    type: 'error'
                });
            }
        });
    });
</script>
</body>
</html>
