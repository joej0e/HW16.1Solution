package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

import java.util.List;

public interface OrderDao {

    List<Item> getItems(Long orderId);

    Order add(Order order);

    Order get(Long id);

    List<Order> getOrdersByUserId(Long id);

    void delete(Long id);

    Order update(Order order);
}

