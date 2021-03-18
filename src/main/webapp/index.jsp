<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Web Crawler</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-500">
<%@include file="header.jsp" %>


<%--<c:forEach items="${requestScope.redditList}" var="redditList">
    <p>${redditList.title}</p>
    <a href="${redditList.url}" target="_blank">Link</a>
</c:forEach>--%>

<div class="box-border w-3/6 p-4 border-1">
    <div class="border-2 border-red-500">
        <c:forEach items="${requestScope.redditList}" var="redditList">
            <div class="border-2 border-black hover:border-yellow-300">
                    <%--<p>${redditList.title}</p>--%>
                <a href="${redditList.url}" target="_blank">${redditList.title}
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>

