package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Event;
import project.persistence.repositories.EventRepository;
import project.service.EventService;

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
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Event> findAllReverseOrder() {
        // Get all the Postit notes
        List<Event> events = repository.findAll();

        // Reverse the list
        Collections.reverse(events);

        return events;
    }

    @Override
    public Event findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<Event> findByName(String name) {
        return repository.findByName(name);
    }
}
