package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import java.util.Set;

public interface RoleService {

    void addRole(User user);

    Set<Role> getRoles(User user);
}

