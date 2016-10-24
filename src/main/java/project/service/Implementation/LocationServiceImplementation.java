package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Location;
import project.persistence.repositories.LocationRepository;
import project.service.LocationService;

import java.util.Collections;
import java.util.List;

/**
 * Created by geelo on 24-Oct-16.
 */

@Service
public class LocationServiceImplementation implements LocationService {

    // Instance Variables
    LocationRepository repository;

    // Dependency Injection
    @Autowired
    public LocationServiceImplementation(LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Location save(Location location) {
        return repository.save(location);
    }
/*
    @Override
    public void delete(Location location) {
        repository.delete(location);
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Location> findAllReverseOrder() {
        // Get all the Postit notes
        List<Location> locations = repository.findAll();

        // Reverse the list
        Collections.reverse(locations);

        return locations;
    }

    @Override
    public Location findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<Location> findByName(String name) {
        return repository.findByName(name);
    }
    */
}