package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import java.util.Set;

public interface RoleDao {

    void addRole(User user);

    Set<Role> getRoles(User user);
}

