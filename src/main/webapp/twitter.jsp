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
<div class="text-gray-100 absolute inset-x-1/3 inset-y-36 px-64 py-2">
    <div class="w-56 h-8 relative bg-gray-700 p-1 rounded-md">
<%--        action="tweet;wordcloud--%>
<%--    onclick="a();"--%>
        <form name="submitbtn" action="tweet" method="post">
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



<button id="myBtn">Open Word Cloud</button>


<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <h1>Word Cloud</h1>
        <img id="img" src="/image/wordcloud.png"/>
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

<script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function() {
        //refreshImage(document.getElementById("img"),"/image/wordcloud.png?t=");
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    // var timestamp = new Date().getTime();
    //
    // var el = document.getElementById("img");
    //
    // el.src = "/image/wordcloud.png?t=" + timestamp;

    function refreshImage(imgElement, imgURL){
        // create a new timestamp
        var timestamp = new Date().getTime();

        var el = document.getElementById(imgElement);

        var queryString = "?t=" + timestamp;

        el.src = imgURL + queryString;
    }

</script>



</body>
</html>
