package project.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Event;
import project.service.EventService;
import project.service.StringManipulationService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    // Instance Variables
    StringManipulationService stringService;
    EventService eventService;

    // Dependency Injection
    @Autowired
    public HomeController(StringManipulationService stringService) {
        this.stringService = stringService;
    }



    // Request mapping is the path that you want to map this method to
    // In this case, the mapping is the root "/", so when the project
    // is running and you enter "localhost:8080" into a browser, this
    // method is called
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() throws IOException {


        // The string "Index" that is returned here is the name of the view
        // (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
        // If you change "Index" to something else, be sure you have a .jsp
        // file that has the same name
        return "Index";
    }

    // To call this method, enter "localhost:8080/user" into a browser
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model){

        // Here we will show how to add attributes to a model and send it to the view

        // Since this small example is for a user, let's create some attributes
        // that users might usually have in a system
        String name = "Rincewind";
        String job  = "Wizzard";
        String email = "rincewizz@unseenuni.edu";
        String description = "most likely to survive in a dungeon dimension.";


        // Since we want our attributes regarding the user always in the same format,
        // we are going to convert some strings using our StringManipulationService

        // Let's assume that the name, job and description always have
        // the first character in upper case
        name = stringService.convertsFirstCharInStringToUpperCase(name);
        job = stringService.convertsFirstCharInStringToUpperCase(job);
        description = stringService.convertsFirstCharInStringToUpperCase(description);

        // Let's assume that we always want e-mail in lower case
        email = stringService.convertStringToLowerCase(email);


        // Now let's add the attributes to the model
        model.addAttribute("name",name);
        model.addAttribute("job",job);
        model.addAttribute("email",email);
        model.addAttribute("description",description);

        // By adding attributes to the model, we can pass information from the controller
        // to the view (the .jsp file).
        // Look at the User.jsp file in /main/webapp/WEB-INF/jsp/ to see how the data is accessed
        return "User";
    }
    //@RequestMapping(value = "/tojson", method = RequestMethod.GET)
    public void dataToJSONFile() throws IOException {
        //String[] events emulator
        List<Event> eventList = eventService.findAll();
/*      JSONObject event;
        JSONArray coords;
        JSONArray events = new JSONArray();*/
        System.out.println("toJson!");
        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String events = gson.toJson(eventList);

        String path = System.getProperty("user.dir");
        path += "\\src\\main\\webapp\\js\\data.json";
        FileWriter file = new FileWriter(path);
        try {
            file.write(events);
            //  System.out.println(System.getProperty("user.dir"));
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            file.flush();
            file.close();
        }
    }

}
