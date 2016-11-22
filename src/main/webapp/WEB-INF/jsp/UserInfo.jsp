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
        <h1>This is ${info.name} events page!</a></h1>
        <p>Here are the events you are signed up for:</p>


            <c:forEach items="${upcomingEvents}" var="event">
                <div class "userInfo_event">
                    <li>Name: ${event.name}</li>
                    <li>Description: ${event.description}</li>
                    <button>Show on map</button>
                </div>
            </c:forEach>


    </body>
</html>
