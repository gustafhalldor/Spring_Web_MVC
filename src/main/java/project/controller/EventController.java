package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Event;
import project.persistence.entities.User;
import project.service.EventService;
import project.service.UserService;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    @RequestMapping(value = "/eventinfo", method = RequestMethod.POST)
    public String saveEvent(@ModelAttribute("eventDetails") Event event, Model model) {

        // Save the event data we received from the form
        eventService.save(event);

        // TODO: Have to add the event to the user's created events

        // Displays the event information through the "info" attribute, which is sent to ViewEventInfo.jsp
        model.addAttribute("info", event);

        return "ViewEventInfo";
    }

    @RequestMapping(value = "/myevents", method = RequestMethod.POST)
    public String deleteEvent(Event event, User user, Model model) {

        eventService.delete(event);

        // TODO: Have to remove the event from the user's created events

        // Something else we want to do?

        model.addAttribute("events", user.getCreatedEvents());

        // display updated version of myevents page, probably best to name it MyEvents.jsp
        return "MyEvents";
    }

    @RequestMapping(value = "/tojson", method = RequestMethod.GET)
    public void dataToJSONFile() throws IOException{
        //String[] events emulator
        List<Event> eventList = eventService.findAll();
        JSONObject event;
        JSONArray coords;
        JSONArray events = new JSONArray();
        for(int i=0; i<eventList.size();i++){
            event = new JSONObject();
            event.put("eventName", eventList.get(i).getName());
            event.put("eventDesc", eventList.get(i).getDescription());
            coords = new JSONArray();
            coords.add("lat:"+eventList.get(i).getLat());
            coords.add("lgt:"+eventList.get(i).getLgt());
            event.put("coordinates", coords);
            events.add("Event:"+event);
        }

       /* JSONObject obj = new JSONObject();
        obj.put("EiCaramba", "crunchify.com");
        obj.put("Author", "App Shah");

        JSONArray company = new JSONArray();
        company.add("Company: eBay");
        company.add("company: Paupal");
        company.add("Company: Google");
        obj.put("CompanyList", company);
        */

        String path = System.getProperty("user.dir");
        path += "\\src\\main\\webapp\\js\\data.json";
        FileWriter file = new FileWriter(path);
        try {
            file.write(events.toJSONString());
            System.out.println(System.getProperty("user.dir"));
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            file.flush();
            file.close();
        }
    }

}