package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import project.persistence.entities.Event;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 
 /**
 * Created by Hoai Nam Duc Tran on 23/10/2016.
 */
@Component
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event save(Event event);

    void delete(Event event);

    List<Event> findAll();

    // If we need a custom query that maybe doesn't fit the naming convention used by the JPA repository,
    // then we can write it quite easily with the @Query notation, like you see below.
    // This method returns all PostitNotes where the length of the name is equal or greater than 3 characters.
    @Query(value = "SELECT p FROM Event p where length(p.name) >= 3 ")
    List<Event> findAllWithNameLongerThan3Chars();

    // Find all upcoming events.
    @Query(value = "SELECT p FROM Event p") //Remember to add time constraints!
    List<Event> findAllUpcoming();

    // Instead of the method findAllReverseOrder() in PostitNoteService.java,
    // We could have used this method by adding the words
    // ByOrderByIdDesc, which mean: Order By Id in a Descending order
    //
    List<Event> findAllByOrderByIdDesc();

    Event findOne(Integer id);

    List<Event> findByName(String name);
}
