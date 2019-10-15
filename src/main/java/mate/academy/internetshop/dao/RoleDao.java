package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import java.util.Set;

public interface RoleDao {

    User addUserRole(Long roleId, Long userId);

    Role addNewRole(Role role);

    Set<Role> getRoles(Long userId);

    void delete(Long roleId);

    Role get(Long id);
}

