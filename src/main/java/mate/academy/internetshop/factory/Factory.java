package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BucketDaoImpl;
import mate.academy.internetshop.dao.impl.ItemDaoImpl;
import mate.academy.internetshop.dao.impl.OrderDaoImpl;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

public class Factory {
    public static ItemDao getItemDao() {
        return ItemDaoImpl.getInstance();
    }

    public static UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public static OrderDao getOrderDao() {
        return OrderDaoImpl.getInstance();
    }

    public static BucketDao getBucketDao() {
        return BucketDaoImpl.getInstance();
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
}