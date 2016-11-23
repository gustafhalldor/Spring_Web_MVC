package project.controller;

/**
 * Created by Hoai Nam Duc Tran on 23/10/2016.
 */

import org.hibernate.mapping.Array;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Event;
import project.persistence.entities.User;
import project.service.EventService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    UserService userService;
    EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }


    // This method is called from an AJAX method in main.js when a user first logs in with
    // Facebook. Using @RequestBody gives us access to the data included in the AJAX call.
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public @ResponseBody String createUser(@RequestBody User user) {

        userService.save(user);

        return "Halló gaman!";
    }

    // Check to see if user already exists (i.e. has logged in before)
    @RequestMapping(value = "user/check", method = RequestMethod.GET)
    public @ResponseBody Boolean userExists(@RequestParam String fbId, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = userService.findOneByString(fbId);

        // if no user exists, null is returned and we return false
        if(user == null) {
            return false;
        }
        return true;
    }

    // Check to see userName
    @RequestMapping(value = "user/name", method = RequestMethod.GET)
    public @ResponseBody String getUsername(@RequestParam int userID, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = userService.findOne(userID);
        if(user == null){
            return "User does not exist";
        }

        System.out.println(user.getName());
        return user.getName();
    }

    // Check to see userName
    @RequestMapping(value = "user/id", method = RequestMethod.GET)
    public @ResponseBody int getID(@RequestParam String fbId, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = userService.findOneByString(fbId);

        System.out.println(user.getName());
        return user.getId();
    }


    //Display the user page for a user. This page will show the events that the user has created and is attending.
    @RequestMapping(value = "user/{userID}", method = RequestMethod.GET)
    public String userInfo(@ModelAttribute User userModel,
                           @ModelAttribute ArrayList<Event> eventIDs,
                           Model model,
                           @PathVariable("userID") Integer id2) {

        User user = userService.findOne(id2);
        model.addAttribute("info", user);
        model.addAttribute("createdEvents", userCreatedEvents(id2));
        model.addAttribute("upcomingEvents", userUpcomingEvents(id2));
        return "UserInfo";
    }

    //Returns the ArrayList of events that the user created.
    public ArrayList<Event> userCreatedEvents(int userID) {
        List <Event> upcomingEvents = eventService.findAll();
        ArrayList<Event> events = new ArrayList<Event>();
        ArrayList<Integer> attendees = new ArrayList<Integer>();
        //Cycle through all upcoming event attendees and see where the userID is present.
        for(int i = 0; i < upcomingEvents.size(); i++){
           if(upcomingEvents.get(i).getCreatorId() == userID){
               events.add(upcomingEvents.get(i));
           }
        }
        return events;
        }

    //Returns the ArrayList of events that the user is attending.
    public ArrayList<Event> userUpcomingEvents(int userID){
        List <Event> upcomingEvents = eventService.findAll();
        ArrayList<Event> events = new ArrayList<Event>();
        ArrayList<Integer> attendees = new ArrayList<Integer>();
        //Cycle through all upcoming event attendees and see where the userID is present.
        for(int i = 0; i < upcomingEvents.size(); i++){


            try {
                attendees = upcomingEvents.get(i).getAttendees();
                for(int j = 0; j < attendees.size(); j++){
                    if(attendees.get(j) == userID){
                        events.add(upcomingEvents.get(i));
                        break;
                    }
                }
            }
            catch(Exception e){
            }

        }
        return events;
    }
}
