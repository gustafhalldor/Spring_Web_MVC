package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Event;
import project.persistence.entities.User;
import project.service.EventService;
import project.service.UserService;

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
  /*  @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String getEventInfo(Model model, Event event) {

        // Puts the selected event into the "eventInfo" attribute
        model.addAttribute("eventInfo", eventService.findOne(event.getId()));

        // We need to decide on the name of the .jsp file which displays the info and where to place it.
        return "EventInfo";
    }
    */

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String createEvent(Model model) {

        model.addAttribute("eventInfo", new Event());

        return "CreateEvent";
    }

    /*
        @RequestMapping(value = "/event", method = RequestMethod.POST)
        public String saveEvent(@ModelAttribute("eventInfo") Event event, Model model) {

            // Save the event data we received from the form
            eventService.save(event);

            // TODO: Have to add the event to the user's created events

            // We probably want to display the created event so the user knows it was created successfully
            // or at least some indication that the event was created successfully
            // Don't know if we do it here or in some other way... At least the line of code below looks redundant.
            model.addAttribute("eventInfo", eventService.findOne(event.getId()));

            return "CreateEvent";
        }
    */
    @RequestMapping(value = "/myevents", method = RequestMethod.POST)
    public String deleteEvent(Event event, User user, Model model) {

        eventService.delete(event);

        // TODO: Have to remove the event from the user's created events

        // Something else we want to do?

        model.addAttribute("events", user.getCreatedEvents());

        // display updated version of myevents page, probably best to name it MyEvents.jsp
        return "MyEvents";
    }
    
}