
<!DOCTYPE html>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>The Event Finder</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    </head>
  <body>
    <h3>The Event Finder</h3>
    <form value="index" action="/connect/facebook" method="POST">
        <button type="submit">Facebook Login</button>
    </form>
    <div id="map"></div>

    <script   src="https://code.jquery.com/jquery-3.1.1.js"   integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="   crossorigin="anonymous"></script>
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDw_tl_1b0d4i3KviPUzVHvM7sFbmAz-RE&callback=initMap">
    </script>
<<<<<<< HEAD

    <h3><a href="/event">Create event</a></h3>

=======
    <script src="<c:url value="/js/main.js" />"></script>
>>>>>>> 7a1fd7233149501e4c3a98cb8c98a63f05f6e910
  </body>
</html>