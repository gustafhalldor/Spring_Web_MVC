/**
 * Created by SareenAith on 23/11/2016.
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

        if(response.status === 'connected'){
            $('#profilePic').attr('src', 'http://graph.facebook.com/' + response.authResponse.userID + '/picture');
            userID = response.authResponse.userID;
            window.location.href = 'http://localhost:8080/home'
        }

        else {

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

    $('#login').click(function(event) {
        event.preventDefault();
        console.log("clicked")
        FB.getLoginStatus(function(response) {

            if(response.status !== 'connected'){

                FB.login(function(response) {
                    var getInfo = $('#test');
                    if(response.authResponse) {
                        //this response return expiresIn, userID, accessToken and signedRequest
                        //var accessToken = response.authResponse.accessToken;

                        var userId = response.authResponse.userID;

                        // checks if user already exists and if not, creates one.
                        userExists(userId);

                    } else {
                        window.alert("failed");
                    }
                }, {
                    scope: 'email,user_birthday,public_profile'
                });
            }
        })
    });

};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
