package project.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
/**
 * Created by geelo on 24-Oct-16.
 */
@Controller
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    // Allows for a new event to be created. Upon visiting /event the form to fill out is displayed.
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String createEvent(Model model) {

        model.addAttribute("eventDetails", new Event());

        return "CreateEventForm";
    }

    // When user submits his event form he is taken to /eventinfo and ViewEventInfo.jsp is displayed
    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public String saveEvent(@ModelAttribute("eventDetails") Event event, Model model) throws IOException {
        // Save the event data we received from the form
        eventService.save(event);

        // TODO: Have to add the event to the user's created events

        // Displays the event information through the "info" attribute, which is sent to ViewEventInfo.jsp
        model.addAttribute("info", event);

        return "redirect:/";
    }

    @RequestMapping(value = "/attend", method = RequestMethod.POST)
    public String attend(@RequestBody  String data) throws IOException {

        //The incoming data is in the form "eventID,userID"
        String[] attendeeData = data.split(",");
        int userID = Integer.parseInt(attendeeData[0]);
        int eventID = Integer.parseInt(attendeeData[1]);

        System.out.println(userID);
        System.out.println(eventID);
        Event event = eventService.findOne(eventID);

        if(event.getAttendees() == null){
            event.setAttendees(new ArrayList<Integer>());
            event.setAttendee(userID);
        }
        else{
            event.setAttendee(userID);
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
        // Displays the event information through the "info" attribute, which is sent to ViewEventInfo.jsp
        model.addAttribute("info", eventInfo);

        return "ViewEventInfo";
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