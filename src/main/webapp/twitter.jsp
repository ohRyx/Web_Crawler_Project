<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Twitter</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-400">
<%@include file="header.jsp" %>


<div class="absolute inset-x-1/3 inset-y-24 px-40 whitespace-nowrap">
    <p class="text-5xl font-semibold">Crawl Twitter Tweets</p>
</div>
<div class="text-gray-100 absolute inset-x-1/3 inset-y-36 px-64 py-2">
    <div class="w-56 h-8 relative bg-gray-700 p-1 rounded-md">
        <form action="tweet" method="post">
            <svg class="h-5 w-5 absolute left-0 ml-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
                 fill="currentColor">
                <path fill-rule="evenodd"
                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                      clip-rule="evenodd"/>
            </svg>
            <input id="search" type="search" placeholder="Search..." name="searchTwitter"
                   class="ml-6 bg-transparent text-gray-100 text-center"/>
        </form>
    </div>
</div>

<div class="container w-3/5 mx-auto absolute inset-x-64 inset-y-52">
    <c:forEach items="${requestScope.twitterlist}" var="twitterlist">
        <div class="border-2 px-2 py-2 rounded-md border-black hover:border-yellow-300">
            <p class="font-black">${twitterlist.name}: ${twitterlist.tweet}</p>
            <p class="font-black">Retweets: ${twitterlist.rtcount}</p>
        </div>
    </c:forEach>
</div>
</body>
</html>
