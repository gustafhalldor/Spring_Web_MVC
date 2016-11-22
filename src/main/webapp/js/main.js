//Index initMap
var sideBarOn = false;
var eventInfoSideBarOn = false;

//Harðkóðaður logged in user - Þarf að ná í id úr facebook login.
var userID = 80085;
function initMap() {

    if(!sideBarOn) {
        var currInfoWindow;
        var rvkLOC = {lat: 64.138705, lng: -21.955501};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: rvkLOC,
            mapTypeId: 'roadmap'

        });
        // Create the search box and link it to the UI element.
        var input = document.getElementById('mapSearchBox');
        //console.log(input);
        //var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function () {
           // searchBox.setBounds(map.getBounds());
        });

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
         /*
        searchBox.addListener('places_changed', function () {
            var places = searchBox.getPlaces();
            console.log(places);
            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
            markers = [];
            console.log("erroR?");

            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();
            places.forEach(function (place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    map: map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location
                }));

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);
        });
        */

        $.getJSON("/js/data.json", function (Events) {
            $.each(Events, function (key, data) {
                var location = {lat: data["lat"], lng: data["lgt"]};
                var marker = new google.maps.Marker({
                    position: location,
                    map: map
                });
                var imgSrc = "" + data["imgSrc"];
                var infowindow = new google.maps.InfoWindow({
                    content: '' + data["name"] + '<br>' + data["description"] + '<br>' +
                    '<br> <Button>Sign Up!</Button>'
                });
                marker.addListener('click', function () {
                    if (typeof(currInfoWindow) !== "undefined")currInfoWindow.close();
                    currInfoWindow = infowindow;

                    infowindow.open(map, marker);
                    console.log(data["attendees"]);
                    eventInfoSideBar(data["name"], data["description"], data["ageMin"], data["ageMax"], data["genderRestriction"], data["attendees"], data["id"]);
                });
            });
        });
    }else{

        var currInfoWindow;
        var rvkLOC = {lat: 64.138705, lng: -21.955501};
        var radius;
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: rvkLOC,
            mapTypeId: 'roadmap'
        });

        //Add listener
        google.maps.event.addListener(map, "click", function (event) {
            var latitude = event.latLng.lat();
            var longitude = event.latLng.lng();
            fillLocationTextBox(latitude, longitude);
            if(radius) radius.setMap(null);
            radius = new google.maps.Circle({map: map,
                radius: 20,
                center: event.latLng,
                fillColor: '#777',
                fillOpacity: 0.1,
                strokeColor: '#AA0000',
                strokeOpacity: 0.8,
                strokeWeight: 4,
                draggable: true,    // Dragable
                editable: false      // Resizable
            });
            google.maps.event.addListener(radius, "mouseup", function(event){
                var latitude = event.latLng.lat();
                var longitude = event.latLng.lng();
                fillLocationTextBox(latitude, longitude);
            })

            // Center of map
            map.panTo(new google.maps.LatLng(latitude,longitude));
            function fillLocationTextBox(lat, lgt){
                var lgtBox = document.getElementsByClassName("lgt");
                var latBox = document.getElementsByClassName("lat");
                lgtBox[0].value = lgt;
                latBox[0].value = lat;
            }
        });
    }
}

function init() {
    $( '.createEventSideBar' ).hide();
    $( '.eventInfoSideBar' ).hide();
    $( '.datePicker' ).datetimepicker({});
    $( '.toggle_createEvent_sideBar_btn' ).on('click', function(){
        toggleMap()
    });
}


function toggleMap() {
    $( '.eventInfoSideBar' ).hide(500);
    $( '.createEventSideBar' ).toggle(500);
    sideBarOn = !sideBarOn;
    initMap();
}

function eventInfoSideBar(name, description, minAge, maxAge, genRestriction, attendees, eventID) {
    fillEventInfo(name, description, minAge, maxAge, genRestriction, attendees, eventID)
    $( '.createEventSideBar' ).hide(500);
    $( '.eventInfoSideBar' ).show(500);
    sideBarOn = false;
    initMap();
}

function fillEventInfo(name, description, minAge, maxAge, genRestriction, attendees, eventID){

 $('.viewEventInfo_name').html(name);
 $('.viewEventInfo_description').html(description);
 $('.viewEventInfo_ageMin').html(minAge);
 $('.viewEventInfo_ageMax').html(maxAge);
 $('.viewEventInfo_genderRestriction').html(genRestriction);
 $('.viewEventInfo_attendBtn').on("click", function(){ attend(eventID)});
 console.log(eventID);

 // PLACEHOLDER ATTEND // Ætti bara að kalla á þetta fall ef ýtt er á Attend takka sem virkar ekki núna!
 attend(eventID);

 if(!attendees) return;

 var attendeeList = document.getElementById("attendees");
 for(var i=0; i < attendees.length; i++){
    var attendee = document.createElement("p");
    var attendeeName = document.createTextNode(attendees[i]);
    attendee.appendChild(attendeeName);

    attendeeList.appendChild(attendee);
 }
}

function attend(eventID){

    $.ajax({
        'url': 'http://localhost:8080/attend',
        'type': 'POST',
        'contentType': 'application/json',
        'dateType': 'json',
        'data': userID+","+eventID,
        'success': function (data) {
            console.log('Attending Event! eventID:'+eventID);
        }
    });
}



//Create event initMap()
function initPlaceMarkerMap(){
    var currInfoWindow;
    var rvkLOC = {lat: 64.138705, lng: -21.955501};
    var radius;
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 14,
      center: rvkLOC,
      mapTypeId: 'roadmap'
    });

    //Add listener
    google.maps.event.addListener(map, "click", function (event) {
    var latitude = event.latLng.lat();
    var longitude = event.latLng.lng();
    fillLocationTextBox(latitude, longitude);
    if(radius) radius.setMap(null);
    radius = new google.maps.Circle({map: map,
        radius: 20,
        center: event.latLng,
        fillColor: '#777',
        fillOpacity: 0.1,
        strokeColor: '#AA0000',
        strokeOpacity: 0.8,
        strokeWeight: 4,
        draggable: true,    // Dragable
        editable: false      // Resizable
    });
    google.maps.event.addListener(radius, "mouseup", function(event){
        var latitude = event.latLng.lat();
        var longitude = event.latLng.lng();
        fillLocationTextBox(latitude, longitude);
    })

    // Center of map
    map.panTo(new google.maps.LatLng(latitude,longitude));
    function fillLocationTextBox(lat, lgt){
        var lgtBox = document.getElementsByClassName("lgt");
        var latBox = document.getElementsByClassName("lat");
        lgtBox[0].value = lgt;
        latBox[0].value = lat;
    }
}); //end addListener

}

/* *** START OF FACEBOOK CODE *** */

/*
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}
*/


window.fbAsyncInit = function() {
    FB.init({
        appId      : '549623305236918',
        auth       : true,
        status     : true,
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.8' // use graph api version 2.5
    });

    FB.Event.subscribe('auth.statusChange', function(response) {
        console.log(response.status);
        if(response.status === 'connected'){
            $('.loginDiv').hide();
            $('#logout').show();
            $('.main').show();
            $('.navigationBar').show();
            $('.welcomePage').hide();
        }

        else {
            $('.loginDiv').show();
            $('#logout').hide();
            $('.main').hide();
            $('.navigationBar').hide();
            $('.welcomePage').show();
        }
    });

    function userExists(userId) {
        if(userId != "") {
            $.ajax({
                'url': 'http://localhost:8080/user/check',
                'type': 'GET',
                'contentType': 'application/json; charset=utf-8',
                'dateType': 'json',
                'data': {"fbId": userId},
                'success': function (data) {
                    userExistsHandler(data, userId);
                }
            });
        }
        else alert("nothing happens");
    }

    function userExistsHandler(data, userId) {
        if(data === false) {
            // user doesn't exist so we need to create one in the database
            FB.api('/me?fields=id,name,email,birthday,permissions', function(response) {

                var name = response.name;
                var email = response.email;

                $.ajax({
                    'url': 'http://localhost:8080/user/create',
                    'type': 'POST',
                    'contentType': "application/json; charset=utf-8",
                    'dataType': 'json',
                    'data': JSON.stringify({
                        name: name,
                        email: email,
                        fbId: userId
                    }),
                    success: function(response)
                    {

                    },
                    error: function ()
                    {

                    }
                });
            });
        }
    };

    /*FB.getLoginStatus(function(response) {
        if(response.status === 'connected'){
            $('.loginDiv').hide();
            $('#logout').show();
        }

        else {
            $('.loginDiv').show();
            $('#logout').hide();
        }
    });*/

    $('#login').click(function(event) {
        event.preventDefault();

        FB.getLoginStatus(function(response) {

            if(response.status !== 'connected'){

                FB.login(function(response) {
                    //  console.log(response);
                    var getInfo = $('#test');
                    if(response.authResponse) {
                        //this response return expiresIn, userID, accessToken and signedRequest
                        //var accessToken = response.authResponse.accessToken;

                        var userId = response.authResponse.userID;
                        $('#profilePic').attr('src', 'http://graph.facebook.com/' + userId + '/picture');

                        // checks if user already exists and if not, creates one.
                        userExists(userId);

                        $('.loginDiv').hide();
                        $('#logout').show();

                    } else {
                        window.alert("failed");
                    }
                }, {
                    scope: 'email,user_birthday,public_profile'
                });
            }
        })
    });

    $('#logout').click(function(event) {
        event.preventDefault();
        FB.logout(function(response) {
            if(response.status !== 'connected') {
                console.log('goodbye');
            }
            $('.overlay').show();
            $('.loginDiv').show();
            $('#logout').hide();
        });
    });
};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

/* *** END OF FACEBOOK CODE *** */

init();
