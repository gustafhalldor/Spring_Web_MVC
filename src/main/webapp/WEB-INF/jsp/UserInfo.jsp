<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>${info.name} page</title>


        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-timepicker-addon.css"/>"/>
    </head>
    <body>
    <div class="myEvents_wrapper">
         <nav class="myEventNavigationBar">
              <a class="btn" href="/home">Back</a>
              <h3>The Event Finder</h3>
              <div class="unUsed"></div>
        </nav>
        <div class="myEvents_main">
        <h1>This is ${info.name} events page!</a></h1>
        <p>Here are the events you are signed up for:</p>
            <c:forEach items="${upcomingEvents}" var="event">

                <div class="myEvents_eventInfo eventInfo">
                   <div class="myEvents_text">
                     <p>Name: ${event.name}</p>
                     <p>Description: ${event.description}</p>
                   </div>
                   <a href="/event/${event.id}" class="">Show on map</a>

                </div>
            </c:forEach>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script
                src="http://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
                integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
                crossorigin="anonymous"></script>
        <script src="<c:url value="/js/jquery-ui-timepicker-addon.js" />"></script>
        <script src="<c:url value="/js/main.js" />"></script>

        </div>
     </div>

    </body>
</html>
