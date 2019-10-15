package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.RoleService;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public User addUserRole(Long roleId, Long userId) {
        return roleDao.addUserRole(roleId, userId);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleDao.addNewRole(role);
    }

    @Override
    public Set<Role> getRoles(User user) {
        return user.getRoles();
    }

    @Override
    public void delete(Long roleId) {
        roleDao.delete(roleId);
    }
}

