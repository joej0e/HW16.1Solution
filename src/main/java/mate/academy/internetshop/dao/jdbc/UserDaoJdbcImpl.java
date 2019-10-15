package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HashUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);
    @Inject
    private static RoleDao roleDao;
    @Inject
    private static BucketDao bucketDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users "
                + "(name, surname, login, password, token, salt) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setBytes(6, user.getSalt());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can't create user", e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Get user by id failed", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users "
                + "SET name = ?, surname = ?, login = ?, password = ? "
                + "WHERE (user_id = ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user", e);
        }
        return user;
    }

    @Override
    public void delete(Long userId) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user", e);
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user;
                user = buildUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Getting users failed", e);
        }
        return users;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = null;
        String query = "SELECT * FROM users WHERE login = ? AND password = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            byte[] salt = getSaltByLogin(login);
            String passwordToVerify = HashUtil.hashPassword(password, salt);
            statement.setString(1, login);
            statement.setString(2, passwordToVerify);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
                Bucket bucket = bucketDao.getByUserId(user.getId());
                user.setBucketId(bucket.getId());
            } else {
                throw new AuthenticationException("Incorrect login or password");
            }
        } catch (SQLException e) {
            logger.error("Checking login or password error", e);
        }
        return user;
    }

    private byte[] getSaltByLogin(String login) {
        String query = "SELECT users.salt FROM users WHERE login = ?";
        byte[] salt = null;
        try (PreparedStatement getUserIdStmt = connection.prepareStatement(query)) {
            getUserIdStmt.setString(1, login);
            ResultSet rs = getUserIdStmt.executeQuery();
            if (rs.next()) {
                salt = rs.getBytes("salt");
            }
        } catch (SQLException e) {
            logger.error("Can't get user_id by login = " + login, e);
        }
        return salt;
    }
    @Override
    public User getByToken(String token) {
        String query = "SELECT * FROM users WHERE token = ?;";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
                Bucket bucket = bucketDao.getByUserId(user.getId());
                user.setBucketId(bucket.getId());
            }
        } catch (SQLException e) {
            logger.error("Getting by token error", e);
        }
        return user;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setToken(resultSet.getString("token"));
        return user;
    }
}

