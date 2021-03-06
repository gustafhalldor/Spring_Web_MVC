package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import project.persistence.entities.User;

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
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);

    void delete(User user);

    List<User> findAll();

    // If we need a custom query that maybe doesn't fit the naming convention used by the JPA repository,
    // then we can write it quite easily with the @Query notation, like you see below.
    // This method returns all PostitNotes where the length of the name is equal or greater than 3 characters.
    @Query(value = "SELECT p FROM User p where length(p.name) >= 3 ")
    List<User> findAllWithNameLongerThan3Chars();

    @Query(value = "SELECT p FROM User p WHERE p.fbId = :facebookId")
    User findOneByString(@Param("facebookId") String fbId);

    @Query(value = "SELECT p.id FROM User p WHERE p.fbId = :user")
    int findIdByString(@Param("user") String fbId);

    // Instead of the method findAllReverseOrder() in PostitNoteService.java,
    // We could have used this method by adding the words
    // ByOrderByIdDesc, which mean: Order By Id in a Descending order
    //
    List<User> findAllByOrderByIdDesc();

    User findOne(Integer id);

    List<User> findByName(String name);
}
