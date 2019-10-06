package mate.academy.internetshop.dao;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> get(Long id);

    byte[] getSalt(User user);

    User update(User user);

    void delete(Long id);

    List<User> getAll();

    Optional<User> getByToken(String token);

    User login(String login, String password) throws AuthenticationException;
}

