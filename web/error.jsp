<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2018/12/29
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>错误页</title>
    <style type="text/css">
        html {
            margin: 0;
            padding: 0;
            font-family: 'San Francisco Text', 'Helvetica Neue', 'PingFang SC',
            'Hiragino Sans GB', 'Microsoft YaHei', sans-serif;
            color: #333;
        }

        body {
            margin: 0;
            padding: 0;
            background-color: #fff;
        }

        ul {
            padding: 0;
            margin: 0;
            list-style: none;
        }

        a, a:hover, a:focus {
            text-decoration: none;
            color: #06C1AE;
        }

        a:focus {
            outline: none;
        }

        #wrapper {
            width: 1036px;
            margin: 15px auto;
        }

        #content, #footer {
            margin-left: 90px;
        }

        #content {
            padding: 50px 0 55px;
        }

        #not-found, #not-found-img {
            display: inline-block;
            vertical-align: top;
        }

        #not-found {
            padding: 60px 0 20px;
        }

        #not-found-tip {
            font-size: 24px;
            line-height: 1;
            margin-bottom: 182px;
        }

        #not-found-img {
            width: 178px;
            height: 311px;
            float: right;
            margin-right: 254px;
        }

        #footer {
            border-top: 1px solid #f0f0f0;
            display: inline-block;
        }

        #other-links-tip {
            font-size: 14px;
            line-height: 18px;
            color: #999;
            margin-top: 10px;
        }

        #app-list {
            margin-top: 30px;
        }

        .app-item {
            margin-right: 50px;
            white-space: nowrap;
        }

        .app-item:last-child {
            margin-right: 0;
        }

        .app-item-link {
            display: block;
        }

        .app-item-link > img {
            width: 40px;
            height: 40px;
            vertical-align: top;
            margin-right: 10px;
        }

        .app-item-link > span {
            display: inline-block;
            vertical-align: top;
            font-size: 20px;
            line-height: 40px;
        }

        @media (max-width: 540px) {
            body {
                background-color: #f2f2f2;
                text-align: center;
            }

            #wrapper {
                width: 100%;
                margin: 0;
            }

            #header {
                background-color: #fff;
                padding-top: 10px;
            }

            #content, #footer {
                margin-left: 0;
            }

            #content {
                padding: 25px 0 85px;
            }

            #not-found-img {
                float: none;
                width: 69px;
                height: 122px;
                margin-right: 0;
            }

            #not-found {
                padding: 20px 0 0 0;
                display: block;
            }

            #not-found-tip {
                font-size: 16px;
                line-height: 18px;
                margin-bottom: 50px;
            }

            #footer {
                display: block;
            }

            #other-links-tip {
                margin-top: 28px;
            }

            #app-list {
            }

            .app-item {
                margin-right: 10%;
                text-align: left;
                margin-top: 10px;
            }

            .app-item-link > img {
                width: 23px;
                height: 23px;
                margin-right: 4px;
            }

            .app-item-link > span {
                font-size: 14px;
                line-height: 23px;
            }

        }
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <div id="content">
            <img id="not-found-img" src="./images/error.png" alt="nothing-found-pic">
            <div id="not-found">
                <div id="not-found-tip">很抱歉，您要访问的页面出错啦！请刷新重试！</div>
            </div>
        </div>
    </div>
    <div id="footer">
        <div id="other-links-tip">或者试试下面的链接：</div>
        <ul id="app-list">
            <li class="app-item">
                <a href="javascript:history.back(-1)">
                    <span>返回上一页</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
