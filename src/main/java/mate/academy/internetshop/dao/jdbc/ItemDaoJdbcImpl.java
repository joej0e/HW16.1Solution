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
import java.util.List;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String DB_NAME = "internetshop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO " + DB_NAME + ".items (name, price) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            Item newItem = null;
            if (rs.next()) {
                newItem = get(rs.getLong(1));
            }
            return newItem;
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement stmt = null;
        String query = "SELECT * FROM " + DB_NAME + ".items where item_id=" + id + ";";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long item_id = rs.getLong("item_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Item item = new Item(item_id, name, price);
                return item;
            }
        } catch (SQLException e) {
            logger.warn("Can't get item by id = " + id);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME + ".items SET name= '?', price = '?' WHERE item_id = '?';";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Statement stmt = null;
        String query = "DELETE FROM " + DB_NAME + ".items where item_id=" + id + ";";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return;
        } catch (SQLException e) {
            logger.warn("Can't delete item by id = " + id);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}

