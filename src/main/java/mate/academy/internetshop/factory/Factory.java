package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.hibernate.ItemDaoHibernateImpl;
import mate.academy.internetshop.dao.jdbc.BucketDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.OrderDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.RoleDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.UserDaoJdbcImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.RoleServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/internetshop?user=root&password=bubblegum1308");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Can't establish connection to our DB", e);
        }
    }

    public static ItemDao getItemDao() {
        return new ItemDaoHibernateImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoJdbcImpl(connection);
    }

    public static OrderDao getOrderDao() {
        return new OrderDaoJdbcImpl(connection);
    }

    public static BucketDao getBucketDao() {
        return new BucketDaoJdbcImpl(connection);
    }

    public static RoleDao getRoleDao() {
        return new RoleDaoJdbcImpl(connection);
    }

    public static ItemService getItemService() {
        return ItemServiceImpl.getInstance();
    }

    public static UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static OrderService getOrderService() {
        return OrderServiceImpl.getInstance();
    }

    public static BucketService getBucketService() {
        return BucketServiceImpl.getInstance();
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl();
    }

}

