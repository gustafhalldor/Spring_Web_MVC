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
import project.persistence.entities.User;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // This method is called from an AJAX method in main.js when a user first logs in with
    // Facebook. Using @RequestBody gives us access to the data included in the AJAX call.
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public @ResponseBody String createUser(@RequestBody User user) {


        userService.save(user);

        return "Halló gaman!";
    }

    @RequestMapping(value = "user/check", method = RequestMethod.GET)
    public @ResponseBody String check(@RequestParam String fdId, HttpServletRequest request, HttpServletResponse response, Model model) {
        userService.findAll();

        return "test";
    }
}
