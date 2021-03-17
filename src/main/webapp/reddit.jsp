<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reddit</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-500">
<%@include file="header.jsp" %>

<c:forEach items="${requestScope.result}" var="tweetlist">
    <p>${tweetlist.title}</p>
    <p>${tweetlist.url}</p>
</c:forEach>

</body>
</html>
