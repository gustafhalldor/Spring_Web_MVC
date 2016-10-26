package project.service;

import project.persistence.entities.Event;
import project.persistence.entities.User;

import java.util.List;

/**
 * Created by geelo on 24-Oct-16.
 */
public interface EventService {
    /**
     * Save a {@link Event}
     * @param event {@link Event} to be saved
     * @return {@link Event} that was saved
     */
    Event save(Event event);

    /**
     * Delete {@link Event}
     * @param event {@link Event} to be deleted
     */
    void delete(Event event);

    /**
     * Get all {@link Event}s
     * @return A list of {@link Event}s
     */
    List<Event> findAll();

    /**
     * Get all {@link Event}s in a reverse order
     * @return A reversed list of {@link Event}s
     */
    List<Event> findAllReverseOrder();

    /**
     * Find a {@link Event} based on {@link Integer id}
     * @param id {@link Integer}
     * @return A {@link Event} with {@link Integer id}
     */
    Event findOne(Integer id);

    /**
     * Find all {@link Event}s with {@link String name}
     * @param name {@link String}
     * @return All {@link Event}s with the {@link String name} passed
     */
    List<Event> findByName(String name);

}