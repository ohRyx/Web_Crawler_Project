<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reddit</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-400">
<%@include file="header.jsp" %>


<form action="redditcrawl" method="post">
    <label for="search">Search</label>
    <input id="search" type="search" name="searchReddit">

    <button type="submit">Submit</button>

</form>

<c:forEach items="${requestScope.result}" var="redditlist">
    <p>${redditlist.title}</p>
    <p>${redditlist.url}</p>
    <p>${redditlist.comments} comments</p>
    <p>${redditlist.upvotes} upvotes</p>
</c:forEach>

</body>
</html>
