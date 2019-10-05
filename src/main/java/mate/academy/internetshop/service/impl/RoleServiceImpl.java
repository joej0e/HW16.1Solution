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
    public void addRole(User user) {
        roleDao.addRole(user);
    }

    @Override
    public Set<Role> getRoles(User user) {
        return roleDao.getRoles(user);
    }
}



