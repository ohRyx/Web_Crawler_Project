<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Twitter</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-400">
<%@include file="header.jsp" %>


<form action="tweet2" method="post">
    <label for="search">Search</label>
    <input id="search" type="search" name="search">

    <button type="submit">Submit</button>

</form>

<c:forEach items="${requestScope.twitterlist}" var="redditList">
    <p>${redditList.name}</p>
    <p>${redditList.tweet}</p>
</c:forEach>

</body>
</html>
