package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Put right value of "value" here, we just need to decide on what it should be.
    // This displays the information on a selected event.
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getEventInfo(Model model, Event event) {

        // Puts the selected event into the "eventInfo" attribute
        model.addAttribute("eventInfo", eventService.findOne(event.getId()));

        // We need to decide on the name of the .jsp file which displays the info and where to place it into the
        // project structure relative to the root.
        return "EventInfo";
    }

    // Method that receives the POST request on the URL /users
    // and receives the ModelAttribute("eventInfo")
    // That attribute is the attribute that is mapped to the form, so here
    // we can save the event info because we get the data that was entered into the form.
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("eventInfo") Event event, Model model) {

        // Save the event data we received from the form
        eventService.save(event);

        // TODO: Have to add the event to the user's created events

        // We probably want to display the created event so the user knows it was created successfully.
        // Don't know if we do it here or in some other way...
        model.addAttribute("eventInfo", eventService.findOne(event.getId()));

        return "EventInfo";
    }








    // Method that returns the correct view for the URL /users/{name}
    // The {name} part is a Path Variable, and we can reference that in our method
    // parameters as a @PathVariable. This enables us to create dynamic URLs that are
    // based on the data that we have.
    // This method finds all Postit Notes posted by someone with the requested {name}
    // and returns a list with all those Postit Notes.
    @RequestMapping(value = "/users/{name}", method = RequestMethod.GET)
    public String userGetUsersFromName(@PathVariable String name, Model model) {
        // Get all Postit Notes with this name and add them to the model
        model.addAttribute("userNames", eventService.findByName(name));

        // Add a new Postit Note to the model for the form
        // If you look at the form in PostitNotes.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("userName", new Event());

        return "User";
    }
}