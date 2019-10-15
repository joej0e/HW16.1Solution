package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {

    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    public void addRole(User user) {
        String role = user.getRoles().stream().findFirst().get().getRoleName().toString();
        Long userId = user.getId();
        String getRoleIdQuery = "SELECT * FROM roles WHERE role_name = ?;";
        String addRoleQuery = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?);";
        try (PreparedStatement getRoleIdStmt = connection.prepareStatement(getRoleIdQuery);
             PreparedStatement addRoleStmt = connection.prepareStatement(addRoleQuery)) {
            getRoleIdStmt.setString(1, role);
            ResultSet resultSet = getRoleIdStmt.executeQuery();
            Long roleId = null;
            if (resultSet.next()) {
                roleId = resultSet.getLong("role_id");
            }
            addRoleStmt.setLong(1, userId);
            addRoleStmt.setLong(2, roleId);
            addRoleStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add Role", e);
        }
    }

    public Set<Role> getRoles(User user) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT roles.role_name "
                + "FROM roles "
                + "INNER JOIN users_roles "
                + "ON users_roles.role_id=roles.role_id "
                + "WHERE users_roles.user_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roleString = resultSet.getString("role_name");
                Role role = Role.of(roleString);
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.error("Can't get roles", e);
        }
        return roles;
    }

    @Override
    public User addUserRole(Long roleId, Long userId) {
        logger.info("HibernateImpl method");
        return null;
    }

    @Override
    public Role addNewRole(Role role) {
        logger.info("HibernateImpl method");
        return null;
    }


    @Override
    public Set<Role> getRoles(Long userId) {
        logger.info("HibernateImpl method");
        return null;
    }

    @Override
    public void delete(Long roleId) {
        logger.info("HibernateImpl method");
    }

    @Override
    public Role get(Long id) {
        logger.info("HibernateImpl method");
        return null;
    }
}

