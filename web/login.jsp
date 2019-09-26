<%--
  Created by IntelliJ IDEA.
  User: yq
  Date: 2019/9/25
  Time: 下午4:58
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "~//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>家庭信息管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is a page">

    <style type="text/css">
        #login{
            border: 2px green solid;
            width:350px;
            height: 200px;
            margin-top:10%;
            margin-left:80%;
            color:black;
            font-weight: bold;
        }
        h2{
            text-align: center;
        }

        .td1{
            text-align: right;
        }
        .td2{
            text-align: center;
        }
        table{
            line-height: 2em;
        }
        a:hover{
            font-weight: bold;
            font-size:1.2em;
        }
        body{
            /*加载图片*/
            background-image:url("picture/bg2.jpg");
            /*背景图水平垂直居中*/
            background-position: center center;
            /*背景图不平铺*/
            background-repeat: no-repeat;
            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            background-attachment: fixed;
            /* 让背景图基于容器大小伸缩 */
            background-size: cover;
            /* 设置背景颜色，背景图加载过程中会显示背景色 */
            background-color:chocolate;
        }
    </style>

    <script type="text/javascript">
        function forgetPass() {
            var isForgetPassword=confirm("是否忘记密码？")
            if(isForgetPassword===true){
                var theAdd=prompt("跳转至官网","http://www.baidu.com");
                if(theAdd!=null){
                    window.open(theAdd,'width=400px,height=500,menubar=no,toolbar=no')
                }
            }
        }

    </script>
</head>
<body>
<h2>家庭信息管理系统_登录界面</h2>
<div id="login">
    <form action="doLogin.jsp" method="post">
        <table>
            <tr>
                <td class="td2" colspan="2"><label>登录窗口</label></td>
            </tr>
            <tr>
                <td><label>用户名：</label></td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td><label>密码：</label></td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="radio" name="stayLogin">十天内记住我</td>
                <td class="td1"><input type="radio" name="agreeProtocol" checked="checked">同意用户协议</td>
            </tr>
            <tr>
                <td class="td2">
                    <input type="submit" name="submit" value="登录">
                    <input type="reset" name="reset" value="重新输入">
                </td>
                <td colspan="2" class="td1"><a href="doLogin.jsp" onclick="forgetPass()" title="请到官网确认登录信息">忘记密码</a></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
