
<!DOCTYPE html>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>The Event Finder</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-timepicker-addon.css"/>"/>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    </head>
  <body>
  <div class="wrapper">
      <div class="overlay"></div>
      <div class="welcomePage">
          <h1>The Event Finder</h1>
          <p>Discover new passions and meet new people with us.</p>
          <div class="loginDiv">
              <button type="button" id="login"></button>
          </div>
      </div>
      <nav class="navigationBar">
          <div class="leftSide">
              <button type="button" class="toggle_createEvent_sideBar_btn btn">Create Event</button>
              <a class="btn" href="user/1">My events</a>
          </div>

          <h3>The Event Finder</h3>
          <div class="rightSide">
              <img id="profilePic" src="">
              <span></span>
              <div class="btnContainer">
                  <img class="divImg" src="./images/fb_icon.png">
                  <button class="exbtn" type="button" id="logout">
                      <span>Logout</span>
                  </button>
              </div>
          </div>

      </nav>

      <div class="main">
            <div class="createEventSideBar hideMe"><jsp:include page="${request.contextPath}/event"></jsp:include></div>

            <div class="mapContainer">
                <!--<input id="mapSearchBox" class="controls" type="text" placeholder="Search Box">-->
                <div id="map"></div>
            </div>
            <div class="eventInfoSideBar hideMe"><jsp:include page="${request.contextPath}/eventinfo/"></jsp:include></div>

          <script src="https://code.jquery.com/jquery-3.1.1.js"   integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="   crossorigin="anonymous"></script>
          <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
          <script src="<c:url value="/js/jquery-ui-timepicker-addon.js" />"></script>
            <script src="<c:url value="/js/main.js" />"></script>
                <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
                <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
            <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDw_tl_1b0d4i3KviPUzVHvM7sFbmAz-RE&callback=initMap&libraries=places">
            </script>
          <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
            <!--<h3><a href="/eventinfo/1">Event Info</a></h3>-->
            <!--<h3><a href="/event">Create event</a></h3>-->
        </div>
    </div>

  </body>
</html>