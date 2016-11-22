<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <button class="viewEventInfo_attendBtn" type="button"> Attend Event! </button>

     <h3>Attendees</h3>
     <div id="attendees">

     </div>
     </sf:form>
     <button class="hide_eventBtn">Hide event info</button>
