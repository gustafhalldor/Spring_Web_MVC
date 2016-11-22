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

    UserService userService; EventService eventService;

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

    @RequestMapping(value = "user/check", method = RequestMethod.GET)
    public @ResponseBody Boolean userExists(@RequestParam String fbId, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = userService.findOneByString(fbId);

        // if no user exists, null is returned and we return false
        if(user == null) {
            return false;
        }

        return true;
    }


    @RequestMapping(value = "user/{userID}", method = RequestMethod.GET)
    public String userInfo(@ModelAttribute User userModel,
                           @ModelAttribute ArrayList<Event> eventIDs,
                           Model model,
                           @PathVariable("userID") Integer id2) {

        User user = userService.findOne(id2);
        model.addAttribute("info", user);



        model.addAttribute("upcomingEvents", userUpcomingEvents(80085));
        return "UserInfo";
    }

    public ArrayList<Event> userUpcomingEvents(int userID){
        List <Event> upcomingEvents = eventService.findAll(); //Nota annað fall hér
        ArrayList<Event> events = new ArrayList<Event>();
        ArrayList<Integer> attendees = new ArrayList<Integer>();
        //Cycle through all upcoming event attendees and see where the userID is present.
        for(int i = 0; i < upcomingEvents.size(); i++){
            System.out.println(i);

            //Verður inní try á meðan attendee listinn getur verið tómur
            try {
                attendees = upcomingEvents.get(i).getAttendees();
                for(int j = 0; j < attendees.size(); j++){
                    if(attendees.get(j) == userID){
                        events.add(upcomingEvents.get(i));
                        break; //Taka út seinna þegar það er ekki hægt að skrá sig tvisvar á event.
                    }
                }
            }
            catch(Exception e){

            }

        }
        return events;
    }
}
