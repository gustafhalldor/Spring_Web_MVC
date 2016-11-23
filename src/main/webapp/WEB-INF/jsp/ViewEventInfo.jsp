<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <h2>Event information</h2>
    <sf:form class="viewEventForm" commandName="info">

    <h3 class="viewEventInfo_name"></h3>

    <p class="viewEventInfo_description"></p>

    <h4>Age</h4>
    <p class="viewEventInfo_ageMin"></p>

    <h4>Gender</h4>
    <p class="viewEventInfo_genderRestriction"></p>

    <h4>Time</h4>
    <p class="viewEventInfo_date"></p>

    <button class="viewEventInfo_attendBtn btn" type="button"> Attend Event! </button>

     <h3>Attendees</h3>
     <div id="attendees"></div>
        <button type="button" class="hide_eventBtn btn">Hide event info</button>
     </sf:form>

