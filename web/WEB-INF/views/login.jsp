<%--
  Created by IntelliJ IDEA.
  User: superkostya
  Date: 02.09.17
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <spring:url var = "loginUrl" value = "/j_spring_security_check" />
    <sec:authorize access="isAnonymous()">
        <div id = "login">
            <form title = "loginForm" action = "${loginUrl}" method = "post">
                <table>
                    <caption align = "left">Login:</caption>
                    <tr>
                        <td>User Name:</td>
                        <td><input type = "text"  name="username" placeholder="User"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type = "password" name="password" placeholder="Password"/></td>
                    </tr>
                    <tr>
                        <td colspan = "2" align = "center"><input title = "submit"
                                                                  type = "submit"
                                                                  value = "Login"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </sec:authorize>
</div>
</body>
</html>
