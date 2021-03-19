<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reddit</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-400">
<%@include file="header.jsp" %>


<div class="absolute inset-x-1/3 inset-y-24 px-44 whitespace-nowrap">
    <p class="text-5xl font-semibold">Crawl Reddit Post</p>
</div>

<%--<div class="text-gray-100 absolute inset-x-1/3 inset-y-36 px-44 py-2">
    <div class="w-56 h-8 relative bg-gray-700 p-1 rounded-md">
        <form action="redditcrawl" method="post">
            <svg class="h-6 w-5 absolute left-0 ml-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
                 fill="currentColor">
                <path fill-rule="evenodd"
                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                      clip-rule="evenodd"/>
            </svg>
            <input id="search" type="search" placeholder="Search..." name="searchReddit"
                   class=" ml-6 bg-transparent text-gray-100 text-center"/>
        </form>
    </div>
</div>


<div class="text-gray-100 absolute inset-x-2/4 inset-y-36 px-1 py-2">
    <div class="w-32 h-8 relative bg-gray-700 p-1 rounded-md">
        <form action="redditcrawl" method="post">
            <svg class="h-6 w-5 absolute left-0 ml-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M7 20l4-16m2 16l4-16M6 9h14M4 15h14"/>
            </svg>
            <input id="noReddit" type="search" placeholder="of Post..." name="noReddit"
                   class="bg-transparent text-gray-100 ml-7 w-20 px-2"/>
        </form>
    </div>
</div>--%>


<%--<form action="redditcrawl" method="post">

    <div class="text-gray-100 absolute inset-x-1/3 inset-y-36 px-44 py-2">
        <div class="w-56 h-8 relative bg-gray-700 p-1 rounded-md">
            <svg class="h-6 w-5 absolute left-0 ml-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
                 fill="currentColor">
                <path fill-rule="evenodd"
                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                      clip-rule="evenodd"/>
            </svg>
            <input id="search" type="search" placeholder="Search..." name="searchReddit"
                   class=" ml-6 bg-transparent text-gray-100 text-center"/>
        </div>
    </div>

    <div class="text-gray-100 absolute inset-x-2/4 inset-y-36 px-1 py-2">
        <div class="w-32 h-8 relative bg-gray-700 p-1 rounded-md">
            <svg class="h-6 w-5 absolute left-0 ml-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M7 20l4-16m2 16l4-16M6 9h14M4 15h14"/>
            </svg>
            <input id="noPost" type="search" placeholder="of Post..." name="noReddit"
                   class="bg-transparent text-gray-100 ml-7 w-20 px-2"/>
        </div>
    </div>

    <div class="absolute inset-x-1/2 inset-y-36 px-40 py-2">
        <button class="w-32 h-8 relative bg-gray-700 p-1 rounded-md text-gray-100 hover:bg-gray-400" type="submit">
            Crawl
        </button>
    </div>


</form>--%>


<form action="redditcrawl" method="post">

    <div class="text-gray-100 absolute inset-x-96 inset-y-40">
        <div class="w-56 h-8 relative inset-x-32 bg-gray-700 p-1 rounded-md">
            <svg class="h-6 w-5 absolute left-0 ml-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
                 fill="currentColor">
                <path fill-rule="evenodd"
                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                      clip-rule="evenodd"/>
            </svg>
            <input id="search" type="text" placeholder="Search..." name="searchReddit"
                   class=" ml-6 bg-transparent text-gray-100 text-center"/>
        </div>

        <div class="w-32 h-8 relative inset-x-80 -inset-y-8 ml-10 bg-gray-700 inse p-1 rounded-md">
            <svg class="h-6 w-5 absolute left-0 ml-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M7 20l4-16m2 16l4-16M6 9h14M4 15h14"/>
            </svg>
            <input id="noReddit" type="text" placeholder="of Post..." name="noReddit"
                   class="bg-transparent text-gray-100 ml-7 w-20 px-2"/>
        </div>

    </div>


    <div class="absolute inset-x-1/3 inset-y-36 px-7  py-4"
    />
    <button class="w-32 h-8 relative bg-gray-700 p-1 rounded-md text-gray-100 hover:bg-gray-400" type="submit">
        Crawl
    </button>
    </div>


</form>


<div class="container w-3/5 mx-auto absolute inset-x-64 inset-y-52 overflow-auto">
    <c:forEach items="${requestScope.redditcrawl}" var="redditList" varStatus="theCount">
        <div class="border-2 px-2 py-2 rounded-md border-black hover:border-yellow-300">
            <a class="font-black" href="${redditList.url}" target="_blank">${theCount.count}) ${redditList.title}
                <P>Comments: ${redditList.comments} Upvotes: ${redditList.upvotes}%</p>
        </div>
    </c:forEach>
</div>

</body>
</html>
