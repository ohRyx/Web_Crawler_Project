<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About Us</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-400">
<%--Directive tags link to header.jsp--%>
<%@include file="header.jsp" %>

<%--Display message--%>
<div class="relative inset-x-96 inset-y-96 bg-gray-500 w-3/5 rounded-3xl ml-36">
    <p class="text-3xl font-semibold text-center p-2">Hello, welcome to our website. We are students from Singapore
        Institute of Technology(SIT). This website is currently in alpha testing.
        The purpose is to crawl various data from different websites and social media platforms and create useful
        information for the users.
        For now, the features is, show popular/trends posts, crawl data, put into a word cloud & generate sentiment
        analysis. Twitter & Reddit is the only one that is available.
        In the future, we are planning to implement more features, websites and social media platforms. We thrive for
        our system to be the all-in-one Data Crawler for the public. Thank you for visiting our website</p>
</div>

</body>
</html>
