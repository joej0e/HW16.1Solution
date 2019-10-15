package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.Set;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {

    @Inject
    private static RoleDao roleDao;
    @Inject
    private static UserDao userDao;

    public User addUserRole(Long roleId, Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            Role role = session.get(Role.class, roleId);
            user.getRoles().add(role);
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return userDao.get(userId).orElseThrow();
    }

    @Override
    public void delete(Long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Role role = get(roleId);
        try {
            transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Role role = session.get(Role.class, id);
            return role;
        }
    }

    @Override
    @Transactional
    public Role addNewRole(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;
        try {
            transaction = session.beginTransaction();
            id = (Long) session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        role.setId(id);
        return role;
    }

    @Override
    public Set<Role> getRoles(Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(User.class, userId).getRoles();
    }
}

