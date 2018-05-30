<%--
  Created by IntelliJ IDEA.
  User: skaqn
  Date: 2018-05-30
  Time: 오후 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>File Upload</h1>
<form action="/helloworld/upload" method="POST" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit"/>
</form>
<img src="${url}"/>
</body>
</html>
