package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return userService;
    }

    @Inject
    private static UserDao userDao;
    @Inject
    private static OrderDao orderDao;

    @Override
    public User create(User user) {
        User newUser = userDao.create(user);
        return newUser;
    }

    @Override
    public Optional<User> get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List getOrders(User user) {
        return orderDao.getOrdersByUserId(user.getId());
    }


    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }

    @Transactional
    @Override
    public Optional<User> getByToken(String token) {
        return Optional.ofNullable(userDao.getByToken(token));
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}

