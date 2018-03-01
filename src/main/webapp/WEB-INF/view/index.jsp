<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2018/2/22
  Time: 上午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <script src="/Users/apple/MLProject/ticketServiceSystem/src/main/webapp/WEB-INF/statics/js/vue.min.js"></script>
</head>
<body>
<p id="app">{{message}}</p>

<script>
    new Vue({
        el:'#app',
        data:{
            message:'aaa'
        }
    })
</script>
</body>
</html>
