package mate.academy.internetshop.dao;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.User;

import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    User update(User user);

    void delete(Long id);

    User getByToken(String token);

    User login(String login, String password) throws AuthenticationException;
}

