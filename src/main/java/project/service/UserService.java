package project.service;

import project.persistence.entities.User;

import java.util.List;

/**
 * Created by Hoai Nam Duc Tran on 23/10/2016.
 */
public interface UserService {
    /**
     * Save a {@link User}
     * @param user {@link User} to be saved
     * @return {@link User} that was saved
     */
    User save(User user);

    /**
     * Delete {@link User}
     * @param user {@link User} to be deleted
     */
    void delete(User user);

    /**
     * Get all {@link User}s
     * @return A list of {@link User}s
     */
    List<User> findAll();

    /**
     * Get all {@link User}s in a reverse order
     * @return A reversed list of {@link User}s
     */
    List<User> findAllReverseOrder();

    /**
     * Find a {@link User} based on {@link Integer id}
     * @param id {@link Integer}
     * @return A {@link User} with {@link Integer id}
     */
    User findOne(Integer id);

    User findOneByString(String fbid);

    int findIdByString(String user);
    /**
     * Find all {@link User}s with {@link String name}
     * @param name {@link String}
     * @return All {@link User}s with the {@link String name} passed
     */
    List<User> findByName(String name);

}
