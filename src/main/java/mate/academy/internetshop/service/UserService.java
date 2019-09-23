package mate.academy.internetshop.service;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<Order> getOrders(User user);

    List<User> getAll();

    Optional<User> getByToken(String token);

    User login(String login, String password) throws AuthenticationException;
}

