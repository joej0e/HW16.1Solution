package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    @Inject
    private static UserDao userDao;
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order add(Order order) {
        String addOrderQuery = "INSERT INTO orders (user_id) VALUES (?);";
        String addItemsQuery = "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?);";
        try (PreparedStatement addOrderStmt = connection.prepareStatement(
                addOrderQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addItemsStmt = connection.prepareStatement(addItemsQuery)) {
            addOrderStmt.setLong(1, order.getUserId());
            addOrderStmt.executeUpdate();
            ResultSet keys = addOrderStmt.getGeneratedKeys();
            if (keys.next()) {
                order.setId(keys.getLong(1));
            }
            addItemsStmt.setLong(1, order.getId());
            for (Item item : order.getItems()) {
                addItemsStmt.setLong(2, item.getId());
                addItemsStmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return order;
    }

    @Override
    public Order get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?;";
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                Long userId = resultSet.getLong("user_id");
                order = new Order();
                order.setId(orderId);
                User user = userDao.get(userId).orElseThrow();
                List<Item> items = getItems(orderId);
                order.setUserId(user.getId());
                order.setItems(items);
            }
        } catch (SQLException e) {
            logger.error("Get order by id failed", e);
        }
        return order;
    }

    @Override
    public void delete(Long orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order with id = " + orderId, e);
        }
    }

    @Override
    public Order update(Order order) {
        String addOrderQuery = "UPDATE orders set user_id WHERE order_id =?;";
        String addItemsQuery = "UPDATE orders_items SET item_id = ? WHERE order_id=?;";
        try (PreparedStatement addOrderStmt = connection.prepareStatement(
                addOrderQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addItemsStmt = connection.prepareStatement(addItemsQuery)) {
            addOrderStmt.setLong(1, order.getUserId());
            addOrderStmt.executeUpdate();
            ResultSet generatedKeys = addOrderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }
            for (Item item : order.getItems()) {
                addItemsStmt.setLong(1, item.getId());
                addItemsStmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return order;
    }

    @Override
    public List<Item> getItems(Long orderId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT items.item_id, name, price "
                + "FROM items, orders_items "
                + "WHERE orders_items.item_id=items.item_id "
                + "AND orders_items.order_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("items.item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setId(itemId);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get items from order", e);
        }
        return items;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT order_id FROM orders WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                orders.add(get(orderId));
            }
        } catch (SQLException e) {
            logger.error("Can't get orders for user with id = " + userId, e);
        }
        return orders;
    }
}

