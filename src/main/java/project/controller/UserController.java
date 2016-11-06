package project.controller;

/**
 * Created by Hoai Nam Duc Tran on 23/10/2016.
 */

import org.hibernate.mapping.Array;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.service.UserService;


@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Method that returns the correct view for the URL /users
    // This handles the GET request for this URL
    // Notice the `method = RequestMethod.GET` part
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userViewGet(Model model) {
        // Add new username+note to the model for the form.
        // If you look at the form in UserInfo.jsp, you can see that we
        // reference this attribute there by the name `userName`.
        model.addAttribute("userName", new User());
        model.addAttribute("userNames", userService.findAll());

        //return the view
        return "UserInfo";
    }

    // Method that receives the POST request on the URL /users
    // and receives the ModelAttribute("userName")
    // That attribute is the attribute that is mapped to the form, so here
    // we can save the user name and his note because we get the data that was entered
    // into the form.
    // Notice the `method = RequestMethod.POST` part
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String userViewPost(@ModelAttribute("userName") User user, Model model) {

        // Save the user's name and note that we received from the form
        userService.save(user);

        // Here we get all the Postit Notes (in a reverse order) and add them to the model
        model.addAttribute("userNames", userService.findAll());
        model.addAttribute("userName", new User());

        return "UserInfo";
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
        model.addAttribute("userNames", userService.findByName(name));

        // Add a new Postit Note to the model for the form
        // If you look at the form in PostitNotes.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("userName", new User());

        return "User";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createUser(Array data) {

        User user = new User();
        System.out.println(data);
//        user.setName(json.name);


        userService.save(user);

        return "Index";
    }
}
