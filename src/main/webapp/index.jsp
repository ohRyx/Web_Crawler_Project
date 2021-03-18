<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Web Crawler</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-400">
<%@include file="header.jsp" %>

<div class="absolute inset-y-40 inset-x-16 px-1">
    <p class="text-3xl font-semibold">Reddit Popular Post </p>
</div>

<div class="container w-2/5 absolute inset-y-48 inset-x-14 p-2">
    <c:forEach items="${requestScope.redditList}" var="redditList" varStatus="theCount">
        <div class="border-2 px-2 py-2 rounded-md border-black hover:border-yellow-300">
            <a class="font-black" href="${redditList.url}" target="_blank">${theCount.count}) ${redditList.title}
                <P>Comments: ${redditList.comments} Upvotes: ${redditList.upvotes}%</p>
        </div>
    </c:forEach>
</div>

<div class="absolute inset-y-40 inset-x-1/2 px-2">
    <p class="text-3xl font-semibold whitespace-nowrap">Twitter Trends</p>
</div>

<div class="container w-2/5 absolute inset-y-48 inset-x-1/2 p-2">
    <%--    <c:forEach items="${requestScope.redditList}" var="redditList" varStatus="theCount">
            <div class="border-2 px-2 py-2 rounded-md border-black hover:border-yellow-300">
                <a class="font-black" href="${redditList.url}" target="_blank">${theCount.count}) ${redditList.title}
            </div>
        </c:forEach>--%>
</div>

</body>
</html>

