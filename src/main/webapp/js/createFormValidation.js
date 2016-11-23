/**
 * Created by geelo on 21-Nov-16.
 */

$().ready(function(){

    $(".createEventForm").validate({
        errorElement: 'div',
        rules: {
            name: {
                required: true,
                minlength: 3,
                maxlength: 255
            },
            description: {
                required: true,
                minlength: 3,
                maxlength: 255
            },
            startDate: {
                required: true,
                nonPast: getCurrentDateTime()
            },
            endDate: {
                required: true,
                greaterThan: "#startDate"
            },
            ageMin: {
                required: true,
                number: true,
                min: 18,
                max: 110
            },
            ageMax: {
                required: true,
                number: true,
                min: 18,
                max: 110
            },
            lat: {
                required: true,
                digits: true
            },
            lgt: {
                required: true,
                digits: true,
                validLocation: "#lat"
            }
        },
        messages: {
            name: {
                required: "Event name is required"
            },
            description: {
                required: "Event description is required"
            },
            startDate: {
                required: "Event start date is required"
            },
            endDate: {
                required: "Event end date is required"
            }
        }

    });

    function getCurrentDateTime() {
        var d = new Date();
        var minutes = d.getMinutes();
        minutes = minutes < 10 ? "0" + minutes : minutes;

        var hour = d.getHours();
        hour = hour < 10 ? "0" + hour : hour;

        var month = (d.getMonth()+1);
        month = month < 10 ? "0" + month : month;

        var date = d.getDate();
        date = date < 10 ? "0" + date : date;

        return month + "/" + date + "/" + d.getFullYear() + " " + hour + ":" + minutes;
    }

    // This one doesn't work... Not able to call it for some reason.
    // value:  input from form
    // params: the value of latitude
    jQuery.validator.addMethod("validLocation", function(value, element, params) {
        //need to implement logic

        return (value != 0.0 && params != 0.0);
    }, "The event must have a location ^_^");


    jQuery.validator.addMethod("greaterThan",
        function(value, element, params) {

            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value) > new Date($(params).val());
            }

            return isNaN(value) && isNaN($(params).val())
                || (Number(value) > Number($(params).val()));
        },'End date must be greater than start date.');

    // checks if date is in the past
    // value:  input from form
    // params: date and time at the moment the page was loaded. Not ideal, but will have to do for now.
    jQuery.validator.addMethod("nonPast",
        function(value, element, params) {
            return value >= params;
        },'Event cannot start in the past.');
});