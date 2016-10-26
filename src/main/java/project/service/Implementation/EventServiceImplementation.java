package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Event;
import project.persistence.repositories.EventRepository;
import project.service.EventService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by geelo on 24-Oct-16.
 */

@Service
public class EventServiceImplementation implements EventService {

    // Instance Variables
    EventRepository repository;

    // Dependency Injection
    @Autowired
    public EventServiceImplementation(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    public void delete(Event event) {
        repository.delete(event);
    }

    @Override
    public ArrayList<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public ArrayList<Event> findAllReverseOrder() {
        // Get all the Postit notes
        ArrayList<Event> events = repository.findAll();

        // Reverse the list
        Collections.reverse(events);

        return events;
    }

    @Override
    public Event findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public ArrayList<Event> findByName(String name) {
        return repository.findByName(name);
    }
}
