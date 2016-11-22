<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>${info.name} page</title>


        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    </head>
    <body>
    <div class="myEvents_wrapper">
         <nav class="navigationBar">
              <h3>The Event Finder</h3>
        </nav>
        <div class="myEvents_main">
        <h1>This is ${info.name} events page!</a></h1>
        <p>Here are the events you are signed up for:</p>
            <c:forEach items="${upcomingEvents}" var="event">
                <div class="myEvents_eventInfo">
                    <div class="myEvents_text">
                        <p>Name: ${event.name}</p>
                        <p>Description: ${event.description}</p>
                    </div>
                    <a href="/event/${event.id}" class="userInfo_showOnMap">Show on map</a>
                </div>
            </c:forEach>
        </div>
     </div>
    </body>
</html>
