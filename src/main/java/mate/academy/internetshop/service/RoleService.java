package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import java.util.Set;

public interface RoleService {

    Role addNewRole(Role role);

    User addUserRole(Long userId, Long RoleId);

    Set<Role> getRoles(User user);

    void delete(Long roleId);
}

