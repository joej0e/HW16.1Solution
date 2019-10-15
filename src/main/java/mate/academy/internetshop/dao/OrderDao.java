package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Order;

import java.util.List;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    List<Order> getOrdersByUserId(Long id);

    void delete(Long id);

    Order update(Order order);
}

