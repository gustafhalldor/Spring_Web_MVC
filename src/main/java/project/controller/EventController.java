package project.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Event;
import project.persistence.entities.User;
import project.service.EventService;
import project.service.UserService;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

/**
 * Created by geelo on 24-Oct-16.
 */
@Controller
public class EventController {

    EventService eventService;
    UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }
/*
    @Autowired
    @Qualifier("eventValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
*/
    // Allows for a new event to be created. Upon visiting /event the form to fill out is displayed.
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String createEvent(Model model) {

        model.addAttribute("eventDetails", new Event());

        return "CreateEventForm";
    }


    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public String saveEvent(@ModelAttribute("eventDetails") @Validated Event event,
                                BindingResult bindingResult, Model model) throws IOException {

       /* if (bindingResult.hasErrors()) {
            model.addAttribute("formHasErrors", true);
            return "redirect:/";
        }

        else {

        */
            // Save the event data we received from the form
            eventService.save(event);

            // TODO: Have to add the event to the user's created events

            // Displays the event information through the "info" attribute, which is sent to ViewEventInfo.jsp
            model.addAttribute("info", event);

            return "redirect:/";
        //}

    }

    @RequestMapping(value = "/attend", method = RequestMethod.POST)
    public String attend(@RequestBody String data) throws IOException {

        // cleanup the string so it only contains numbers and a comma
        String snip1 = data.replaceAll("[{}\":a-zA-Z]", "");
        // split the string into 2 parts, first one is eventID, second is FB userID
        String[] snip2 = snip1.split(",");

        int eventID = Integer.parseInt(snip2[0]);
        String user = snip2[1];

        Event event = eventService.findOne(eventID);
        int person = userService.findIdByString(user);

        if(event.getAttendees() == null){
            event.setAttendees(new ArrayList<Integer>());
            event.setAttendee(person);
        }
        else{
            event.setAttendee(person);
        }

        eventService.save(event);

        // TODO: Have to add the event to the user's created events

        // Displays the event information through the "info" attribute, which is sent to ViewEventInfo.jsp
        //model.addAttribute("info", event);

        return "redirect:/";
    }

    // When user submits his event form he is taken to /eventinfo and ViewEventInfo.jsp is displayed
    @RequestMapping(value = {"/eventinfo", "/eventinfo/{id}"}, method = RequestMethod.GET)
    public String viewEvent(@ModelAttribute("eventDetails") Event event, Model model,
                            @PathVariable("id") Integer id2) throws IOException {

        // TODO: Have to add the event to the user's created events
        Event eventInfo = eventService.findOne(id2);

        ArrayList attendees = eventInfo.getAttendees();

        ArrayList attendeeName = new ArrayList();
        ArrayList attendeeFbId = new ArrayList();


        for (int i = 0; i < attendees.size(); i++){

            User user = userService.findOne((Integer)attendees.get(i));
            if (user != null){
                System.out.println(user);
                String name = user.getName();
                System.out.println(name);
                attendeeName.add(i, name);

                String fbId = user.getfbId();
                attendeeFbId.add(i, fbId);
            }
        }

        // Displays the event information through the "info" attribute, which is sent to ViewEventInfo.jsp
        model.addAttribute("info", eventInfo);
        model.addAttribute("attendeeNames", attendeeName);

        return "ViewEventInfo";
    }
    // When user submits his event form he is taken to /eventinfo and ViewEventInfo.jsp is displayed
    @RequestMapping(value = {"/event/{id}"}, method = RequestMethod.GET)
    public String showEvent(@ModelAttribute("eventDetails") Event event, Model model,
                            @PathVariable("id") Integer id2) throws IOException {

        Event eventInfo = eventService.findOne(id2);
        ArrayList attendees = eventInfo.getAttendees();

        ArrayList attendeeName = new ArrayList();
        ArrayList attendeeFbId = new ArrayList();


        for (int i = 0; i < attendees.size(); i++){

            User user = userService.findOne((Integer)attendees.get(i));
            if (user != null){
                System.out.println(user);
                String name = user.getName();
                System.out.println(name);
                attendeeName.add(i, name);

                String fbId = user.getfbId();
                attendeeFbId.add(i, fbId);
            }
        }

        // Displays the event information through the "info" attribute, which is sent to MyEvents.jsp
        model.addAttribute("info", eventInfo);
        model.addAttribute("attendeeNames", attendeeName);
        model.addAttribute("attendeeFbId", attendeeFbId);

        return "MyEvents";
    }



    @RequestMapping(value = "/myevents", method = RequestMethod.POST)
    public String deleteEvent(Event event, User user, Model model) {

        eventService.delete(event);

        // TODO: Have to remove the event from the user's created events

        // Something else we want to do?

        //model.addAttribute("events", user.getCreatedEvents());

        // display updated version of myevents page, probably best to name it MyEvents.jsp
        return "MyEvents";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dataToJSONFile() throws IOException{

        List<Event> eventList = eventService.findAll();

        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String events = gson.toJson(eventList);

        String path = System.getProperty("user.dir");
        path += "\\src\\main\\webapp\\js\\data.json";
        FileWriter file = new FileWriter(path);
        try {
            file.write(events);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            file.flush();
            file.close();
        }
        return "Index";
    }

}