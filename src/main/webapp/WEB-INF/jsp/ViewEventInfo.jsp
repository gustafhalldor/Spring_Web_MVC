<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <h2>Event information</h2>
    <sf:form class="viewEventForm" commandName="info">

    <h3>Event name:</h3>
    <p class="viewEventInfo_name">${info.name}</p>

    <h3>Event description:</h3>
    <p class="viewEventInfo_description">${info.description}</p>

    <h3>Minimum age:</h3>
    <p class="viewEventInfo_ageMin">${info.ageMin}</p>

    <h3>Maximum age:</h3>
    <p class="viewEventInfo_ageMax">${info.ageMax}</p>

    <h3>Gender restriction</h3>
    <c:choose>
        <c:when test="${not info.genderRestriction}">
            <p class="viewEventInfo_genderRestriction">This is a gender restricted event</p>
        </c:when>
        <c:otherwise>
             <p class="viewEventInfo_genderRestriction">This is not a gender restricted event</p>
        </c:otherwise>
    </c:choose>

    <button class="viewEventInfo_attendBtn btn" type="button"> Attend Event! </button>

     <h3>Attendees</h3>
     <div id="attendees">
         <c:choose>
             <c:when test="${not empty attendeeNames}">
                 <c:forEach items="${attendeeNames}" var="attendee">
                     <%--<img src="http://graph.facebook.com/${attendeeFbId}/picture" alt="profile picture">--%>
                     <p>${attendee}</p>
                 </c:forEach>
             </c:when>
             <c:otherwise>
                 <h2>No one is going yet</h2>
             </c:otherwise>
         </c:choose>
     </div>
        <button type="button" class="hide_eventBtn btn">Hide event info</button>
     </sf:form>

