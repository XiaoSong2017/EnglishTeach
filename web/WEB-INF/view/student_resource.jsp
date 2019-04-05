<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="service.FileService" %>
<%@ page import="java.util.List" %>
<%@ page import="po.TeachResourcePo" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/21
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>教学资源学生端</title>
</head>
<body>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">条目</th>
            <th class="col text-center">文件名</th>
            <th class="col text-center">文件类型</th>
            <th class="col text-center">上传时间</th>
            <th class="col text-center">上传人</th>
            <th class="col text-center">下载量</th>
            <th class="col text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
            FileService fileService = (FileService) applicationContext.getAutowireCapableBeanFactory().getBean("fileService");
            List<TeachResourcePo> teachResourcePos = fileService.getFilesByCourseId(String.valueOf(request.getSession().getAttribute("courseById")));
            int i = 1;
            for (TeachResourcePo teachResourcePo : teachResourcePos) {
        %>
        <tr class="row">
            <td class="col text-center"><%=i++%>
            </td>
            <td class="col text-center"><a href="download.action?id=<%=teachResourcePo.getId()%>"><%=teachResourcePo.getFileName()%>
            </a></td>
            <td class="col text-center"><%=teachResourcePo.getFileType()%>
            </td>
            <td class="col text-center"><%=teachResourcePo.getUploadTime()%>
            </td>
            <td class="col text-center"><%=teachResourcePo.getTeacherByUploadUser().getName()%>
            </td>
            <td class="col text-center"><%=teachResourcePo.getDowns()%>
            </td>
            <td class="col text-center">
                <div class="btn-group" role="group">
                    <a href="download.action?id=<%=teachResourcePo.getId()%>" class="btn btn-outline-primary"
                       role="button" aria-pressed="true">下载</a>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
        <tfoot>
        </tfoot>
    </table>
</div>
</body>
</html>
