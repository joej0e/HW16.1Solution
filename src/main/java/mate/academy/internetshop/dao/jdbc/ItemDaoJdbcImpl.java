package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    public static final String DB_NAME = "internetshop";

    @Override
    public Item create(Item item) {
        Item newItem = null;
        String query = "INSERT INTO " + DB_NAME + ".items (name, price) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                newItem = get(rs.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can't create new item", e);
        }
        return newItem;
    }

    @Override
    public Item get(Long id) {
        String query = "SELECT * FROM items WHERE item_id=?;";
        Item item = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                item = new Item();
                item.setId(id);
                item.setName(name);
                item.setPrice(price);
            }
        } catch (SQLException e) {
            logger.error("Can't get item by id " + id, e);
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE items SET name= ?, price = ? WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update item", e);
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM items WHERE (item_id = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item", e);
        }
    }

    @Override
    public List<Item> getAll() {
        String query = "SELECT * FROM items;";
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item();
                item.setId(id);
                item.setName(name);
                item.setPrice(price);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Creating list of items failed", e);
        }
        return items;
    }
}

