//Index initMap
var sideBarOn = false;

var userID;
//Not the facebook userid
var userIdNormal;

function initMap() {
    console.log(userIdNormal);
    if(userIdNormal == undefined){
        addUserIDToBox();
    }

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
        var markers = [];

        $.getJSON("/js/data.json", function (Events) {
            $.each(Events, function (key, data) {
                var location = {lat: data["lat"], lng: data["lgt"]};
                var marker = new google.maps.Marker({
                    position: location,
                    map: map
                });
                marker.addListener('click', function () {
                    eventInfoSideBar(data["name"], data["description"], data["ageMin"], data["ageMax"],
                        data["genderRestriction"], data["attendees"], data["id"], data["startDate"], data["endDate"]);
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

function initFocusEventMap() {
        $( '.eventInfoSideBar' ).show();
        var rvkLOC = {lat: 64.138705, lng: -21.955501};

        var focusedID = getEventIdFromUrl();
        var markers = [];
        $.getJSON("/js/data.json", function (Events) {
            $.each(Events, function (key, data) {
            if(data["id"] == focusedID){

                var location = {lat: data["lat"], lng: data["lgt"]};
                 var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 14,
                            center: location,
                            mapTypeId: 'roadmap'
                 });

                var marker = new google.maps.Marker({
                    position: location,
                    map: map
                });
              }
            });
        });

  }

function init() {
    $( '.datePicker1' ).datetimepicker({
        dateFormat: "yy-mm-dd",
        timeFormat: "HH:mm:ss",
        stepMinute: 5,
        showSecond: false
/*        onSelect: function(){
            $('.datePicker2').val(this.value);
        },*/

    });
    $( '.datePicker2' ).datetimepicker({
        dateFormat: "yy-mm-dd",
        timeFormat: "HH:mm:ss",
        stepMinute: 5,
        showSecond: false
    });

    $( '.toggle_createEvent_sideBar_btn' ).on('click', function(e){
        addUserIDToBox();
        var sideBar = $('.createEventSideBar').hasClass('hideMe');
        if(sideBar) {
            $(this).text('hide event');
        } else {
            $(this).text('create event');
        }
        toggleMap();
    });
    hideEventInfo();
}

function toggleMap() {
    $( '.eventInfoSideBar' ).removeClass('showMe');
    $( '.eventInfoSideBar' ).addClass('hideMe');
    $('.createEventSideBar').toggleClass('hideMe');
    sideBarOn = !sideBarOn;
    initMap();
}

function eventInfoSideBar(name, description, minAge, maxAge, genRestriction, attendees, eventID, startDate, endDate) {
    fillEventInfo(name, description, minAge, maxAge, genRestriction, attendees, eventID, startDate, endDate)
    //$( '.createEventSideBar' ).removeClass('showMe');
    $( '.eventInfoSideBar' ).removeClass('hideMe');
    $( '.eventInfoSideBar' ).addClass('showMe');
    sideBarOn = false;
    initMap();
}

function hideEventInfo() {
    $('.hide_eventBtn').on('click', function(e) {
        e.preventDefault();
        $('.eventInfoSideBar').removeClass('showMe');
        $('.eventInfoSideBar').addClass('hideMe');
    });
}

function fillEventInfo(name, description, minAge, maxAge, genRestriction, attendees, eventID, startDate, endDate){

    let start = new Date(startDate);
    let end = new Date(endDate);
    let startArr = start.toString().split(" ")
    let endArr = end.toString().split(" ")

 $('.viewEventInfo_name').html(name);
 $('.viewEventInfo_description').html(description);
 if(maxAge === 99){
     $('.viewEventInfo_ageMin').html(minAge + "+" );
 }else {
     $('.viewEventInfo_ageMin').html(minAge + " - " + maxAge);
 }

 if(genRestriction) $('.viewEventInfo_genderRestriction').html("This is a gender restricted event.");
 else $('.viewEventInfo_genderRestriction').html("This is not a gender restricted event.");

 if(start.getDate() === end.getDate()){
     $('.viewEventInfo_date').html(startArr[1] + " " + startArr[2] + " " + startArr[3] + " " + startArr[4].substring(0,5) + " - " + endArr[4].substring(0,5));
 }else {
     $('.viewEventInfo_date').html(startArr[1] + " " + startArr[2] + " " + startArr[3] + " " + startArr[4].substring(0,5) + " - " +
         endArr[1] + " " + endArr[2] + " " + endArr[3] + " " + endArr[4].substring(0,5));
 }

 if(attendees.indexOf(userIdNormal) >= 0){
    $('.viewEventInfo_attendBtn').hide();
 }
 else{
    $('.viewEventInfo_attendBtn').show();
 }
 $('.viewEventInfo_attendBtn').on("click", function(){ attend(eventID)});


 if(!attendees) return;

 var attendeeList = document.getElementById("attendees");
 attendeeList.innerHTML ="";
 for(var i=0; i < attendees.length; i++){
    addAttendeeToList(attendees[i], attendeeList);
 }
}

 function addAttendeeToList(userId, attendeeList) {
            $.ajax({
                'url': 'http://localhost:8080/user/name',
                'type': 'GET',
                'contentType': 'application/json; charset=utf-8',
                'dateType': 'json',
                'data': {"userID": userId},
                'success': function (data) {
                    createElementForAttendee(data, attendeeList);
                }
            });
}

function createElementForAttendee(name, attendeeList){
        var attendee = document.createElement("p");
        var attendeeName = document.createTextNode(name);
        attendee.appendChild(attendeeName);
        attendeeList.appendChild(attendee);
}

 function addUserIDToBox() {
            $.ajax({
                'url': 'http://localhost:8080/user/id',
                'type': 'GET',
                'contentType': 'application/json; charset=utf-8',
                'dateType': 'json',
                'data': {"fbId": userID},
                'success': function (data) {
                    addUserIDToBoxCallBack(data);
                }
            });
}

function addUserIDToBoxCallBack(data){
    $('.creatorId').val(data);
    userIdNormal = data;
    console.log("userid set to " + userIdNormal);
}


function attend(eventID){

    $.ajax({
        'url': 'http://localhost:8080/attend',
        'type': 'POST',
        'contentType': 'application/json; charset=utf-8',
        'data': JSON.stringify({
            eventId: eventID,
            fbId: userID
        }),
        'success': function (data) {
            showSuccessToaster("Event successfully joined!");
        }
    });
}

function showSuccessToaster(param){
    Command: toastr["success"](param, "Success!")

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-bottom-center",
        "preventDuplicates": true,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "3000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
}


function getEventIdFromUrl(){
    var url = window.location.href;
    var eventIdPos = url.indexOf("event/");
    var eventId = url.substring(eventIdPos+6, url.length);
    return eventId;
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

        if(response.status === 'connected'){
            $('.loginDiv').hide();
            $('.btnContainer').show();
            $('.main').show();
            $('.navigationBar').show();
            $('.welcomePage').hide();
            $('#profilePic').attr('src', 'http://graph.facebook.com/' + response.authResponse.userID + '/picture');
            userID = response.authResponse.userID;

            $.ajax({
                'url': 'http://localhost:8080/user/id',
                'type': 'GET',
                'contentType': 'application/json; charset=utf-8',
                'dateType': 'json',
                'data': {"fbId": userID},
                'success': function (data) {
                    addUserIDToBoxCallBack(data);
                    appendUserIdToMyEventsURL(data);
                }
            });
        }

        else {
            $('.loginDiv').show();
            $('.btnContainer').hide();
            $('.main').hide();
            $('.navigationBar').hide();
            $('.welcomePage').show();
            //window.location.href = 'http://localhost:8080/';
        }
    });

    function appendUserIdToMyEventsURL(data) {
        var a = document.getElementById('myEventsBtn');
        a.href = "user/"+data;
    }
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

    $('#login').click(function(event) {
        event.preventDefault();
        console.log("hellooooo");
        FB.getLoginStatus(function(response) {
            console.log(response);
            if(response.status !== 'connected'){
                console.log("asdfasdfasds");
                FB.login(function(response) {
                    var getInfo = $('#test');
                    if(response.authResponse) {
                        //this response return expiresIn, userID, accessToken and signedRequest
                        //var accessToken = response.authResponse.accessToken;

                        var userId = response.authResponse.userID;

                        // checks if user already exists and if not, creates one.
                        userExists(userId);

                        $('.loginDiv').hide();
                        $('.btnContainer').show();

                    } else {
                        window.alert("failed");
                    }
                }, {
                    scope: 'email,user_birthday,public_profile'
                });
            }
        })
    });

    $('.btnContainer').click(function(event) {
        event.preventDefault();
        FB.logout(function(response) {
            if(response.status !== 'connected') {
            }
            $('.overlay').show();
            $('.loginDiv').show();
            $('.btnContainer').hide();
        });
    });

    $( document ).ready(
        FB.getLoginStatus(function(response) {
            console.log("yuyu");
            if(response.status !== 'connected'){
                console.log("yuyu");
                $('.btnContainer').hide();
                $('.main').hide();
                $('.navigationBar').hide();
            }else{

            }
        })
    )
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
