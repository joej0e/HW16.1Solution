package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Optional;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static final Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Inject
    private static ItemDao itemDao;

    @Transactional
    public Bucket create(User user) {
        Transaction transaction = null;
        Session session = session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Bucket bucket = new Bucket();
            bucket.setUser(user);
            session.save(bucket);
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
        return get(user.getId()).orElseThrow();
    }

    @Override
    public void clear(Long bucketId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            get(bucketId).get().getItems().clear();
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create Bucket ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public Optional<Bucket> get(Long bucketId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = session.get(Bucket.class, bucketId);
            return Optional.ofNullable(bucket);
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update Bucket " + bucket, e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket getByUserId(Long id) {
        Bucket bucket = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Bucket where user_id=:id");
            query.setParameter("id", id);
            bucket = (Bucket) query.uniqueResult();
        } catch (RuntimeException e) {
            logger.error("Can't get bucket by userId = " + id, e);
        }
        return bucket;
    }
}

