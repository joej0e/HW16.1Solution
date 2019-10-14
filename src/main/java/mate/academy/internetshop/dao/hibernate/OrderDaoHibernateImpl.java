package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Order create(Order order) {
        Transaction transaction = null;
        Session session = session = HibernateUtil.getSessionFactory().openSession();
        Long orderId = null;
        try {
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create Order " + order, e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        order.setId(orderId);
        return order;
    }

    @Override
    public Order get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Order order = session.get(Order.class, id);
            return order;
        }
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update Order " + order, e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        ;
        try {
            transaction = session.beginTransaction();
            Order order = get(id);
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete order with id " + id, e);
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
    public List<Order> getOrdersByUserId(Long id) {
        List<Order> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where user.id=:id");
            query.setParameter("id", id);
            list = query.getResultList();
            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                order.setUserId(id);
            }
        }
        return list;
    }
}

