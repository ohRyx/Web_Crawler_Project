<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Twitter</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="wordcloud.css">
</head>
<body class="bg-gray-400">
<%@include file="header.jsp" %>


<div class="absolute inset-x-1/3 inset-y-24 px-40 whitespace-nowrap">
    <p class="text-5xl font-semibold">Crawl Twitter Tweets</p>
</div>

<form action="tweet" method="post">
    <div class="text-gray-100 absolute inset-x-96 inset-y-40">
        <div class="w-56 h-8 relative inset-x-32 bg-gray-700 p-1 rounded-md">
            <svg class="h-6 w-5 absolute left-0 ml-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
                 fill="currentColor">
                <path fill-rule="evenodd"
                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                      clip-rule="evenodd"/>
            </svg>
            <input id="search" type="text" placeholder="Search..." name="searchTwitter"
                   class="ml-6 bg-transparent text-gray-100 text-center"/>
        </div>

        <div class="w-32 h-8 relative inset-x-80 -inset-y-8 ml-10 bg-gray-700 inse p-1 rounded-md">
            <svg class="h-6 w-5 absolute left-0 ml-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M7 20l4-16m2 16l4-16M6 9h14M4 15h14"/>
            </svg>
            <input id="noReddit" type="number" max="1000" placeholder="of Tweets..." name="noTweets"
                   class="bg-transparent text-gray-100 ml-6 w-24 px-2"/>
        </div>
    </div>

    <div class="absolute inset-x-1/3 inset-y-36 px-7 py-4">
        <button class="w-32 h-8 relative bg-gray-700 p-1 rounded-md text-gray-100 hover:bg-gray-400" type="submit">
            Crawl
        </button>
    </div>

    <div class=" relative inset-x-1/3 inset-y-40 py-1 ml-44">
        <p class="bg-red-600 text-xl font-semibold w-80 rounded-lg px-2 text-center">${error}</p>
    </div>
</form>

<div class="text-gray-100 absolute inset-x-2/3 inset-y-40 px-48 text-center cursor-pointer">
    <div class="w-36 h-8 relative bg-gray-700 p-1 rounded-md">
        <svg class="h-6 w-8 absolute left-0 ml-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
             fill="currentColor">
            <path d="M5.5 16a3.5 3.5 0 01-.369-6.98 4 4 0 117.753-1.977A4.5 4.5 0 1113.5 16h-8z"/>
        </svg>
        <a class="bg-transparent text-gray-100 ml-5" id="myBtn">Word Cloud</a>
    </div>
</div>


<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <h1>Word Cloud</h1>
        <img id="img" src="/image/wordcloud.png"/>
    </div>
</div>

<div class="container w-3/5 mx-auto absolute inset-x-64 inset-y-52 overflow-auto">
    <c:forEach items="${requestScope.twitterlist}" var="twitterlist" varStatus="theCount">
        <div class="border-2 px-2 py-2 rounded-md border-black hover:border-yellow-300">
            <p class="font-black">${theCount.count}) ${twitterlist.name}:</p>
            <p class="font-black">${twitterlist.tweet}</p>
            <p class="font-black">Retweets: ${twitterlist.rtcount}</p>
        </div>
    </c:forEach>
</div>

<script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function () {
        //refreshImage(document.getElementById("img"),"/image/wordcloud.png?t=");
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    // var timestamp = new Date().getTime();
    //
    // var el = document.getElementById("img");
    //
    // el.src = "/image/wordcloud.png?t=" + timestamp;

    function refreshImage(imgElement, imgURL) {
        // create a new timestamp
        var timestamp = new Date().getTime();

        var el = document.getElementById(imgElement);

        var queryString = "?t=" + timestamp;

        el.src = imgURL + queryString;
    }

</script>


</body>
</html>
