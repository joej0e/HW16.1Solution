package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

import java.util.List;

public interface UserDao {
    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<User> getAll();
}