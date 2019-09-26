package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

import java.sql.Connection;
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
        Statement stmt = null;
        String query = String.format("INSERT INTO %s.items (name, price) VALUES ('%s', %d)",
                DB_NAME, item.getName(), item.getPrice().intValue());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't create item = " + item.getName());
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
                Item item = new Item(item_id);
                item.setName(name);
                item.setPrice(price);
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
        Statement stmt = null;
        String query = String.format("UPDATE %s.items SET name='%s', price=%d where item_id=%d",
                DB_NAME, item.getName(), item.getPrice().intValue(), item.getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't updete item " + item.getName());
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

