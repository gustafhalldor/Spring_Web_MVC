<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
<%--that is added to the model that is passed to the view.--%>

<h2>Event information</h2>
<sf:form class="viewEventForm" commandName="info">

    <h3>Name:</h3>
    <p class="viewEventInfo_name">${info.name}</p>

    <h3>Description:</h3>
    <p class="viewEventInfo_description">${info.description}</p>

    <h3>Minimum age:</h3>
    <p class="viewEventInfo_ageMin">${info.ageMin}</p>

    <h3>Maximum age:</h3>
    <p class="viewEventInfo_ageMax">${info.ageMax}</p>

    <h3>Gender restrict event?</h3>
    <p class="viewEventInfo_genderRestriction">${info.genderRestriction}</p>

</sf:form>

<h2>Attendees:</h2>
<%--Choose what code to generate based on tests that we implement--%>
<c:choose>

    <%--If the model has an attribute with the name `listOfAttendees`--%>
    <c:when test="${not empty listOfAttendees}">
        <ul class="attendeeList">

            <c:forEach var="attendee" items="${listOfAttendees}">
                <li>${attendee.name}</li>
            </c:forEach>
        </ul>
    </c:when>

    <%--If all tests are false, then do this--%>
    <c:otherwise>
        <h3>No one is going yet!</h3>
    </c:otherwise>
</c:choose>
