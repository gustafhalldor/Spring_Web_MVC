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

    // Put right value of "value" here, we just need to decide on what it should be.
    // This displays the information on a selected event.
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public String getEventInfo(@PathVariable int id, Model model, Event event) {

        // Puts the selected event into the "eventInfo" attribute
        model.addAttribute("eventInfo", eventService.findOne(event.getId()));

        // We need to decide on the name of the .jsp file which displays the info and where to place it
        // and of course create it as well :)
        return "EventInfo";
    }


    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String createEvent(Model model) {

        model.addAttribute("eventInfo", new Event());

        return "CreateEvent";
    }


        @RequestMapping(value = "/event", method = RequestMethod.POST)
        public String saveEvent(@ModelAttribute("eventInfo") Event event, Model model) {

            // Save the event data we received from the form
            eventService.save(event);

            // TODO: Have to add the event to the user's created events

            // We probably want to display the created event so the user knows it was created successfully
            // or at least some indication that the event was created successfully
            // Don't know if we do it here or in some other way... At least the line of code below looks redundant.
           // model.addAttribute("eventInfo", eventService.findOne(event.getId()));

            return "CreateEvent";
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