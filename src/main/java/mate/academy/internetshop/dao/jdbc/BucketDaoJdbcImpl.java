package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    @Inject
    private static UserDao userDao;

    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets (user_id) VALUES (?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            Long userId = bucket.getUserId();
            statement.setLong(1, userId);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                bucket.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can't create bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String query = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            statementBuckets.setLong(1, bucket.getUserId());
            statementBuckets.setLong(2, bucket.getId());
            statementBuckets.executeUpdate();
            ResultSet keys = statementBuckets.getGeneratedKeys();
            if (keys.next()) {
                bucket.setId(keys.getLong("bucket_id"));
            }
        } catch (SQLException e) {
            logger.error("Can't update bucket", e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        String query = "SELECT * FROM buckets WHERE bucket_id = ?;";
        Bucket bucket = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long bucketId = resultSet.getLong("bucket_id");
                Long userId = resultSet.getLong("user_id");
                List<Item> items = getItems(bucketId);
                bucket = new Bucket();
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                bucket.setItems(items);
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket by id = " + id, e);
        }
        return Optional.ofNullable(bucket);
    }

    @Override
    public Bucket addItem(Bucket bucket, Item item) {
        String query = "INSERT INTO buckets_items (bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucket.getId());
            statement.setLong(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add item to bucket", e);
        }
        return get(bucket.getId()).orElseThrow();
    }

    @Override
    public void clear(Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't clear bucket with id = " + bucketId, e);
        }
    }

    @Override
    public void deleteItem(Long bucketId, Item item) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item from bucket with id = " + bucketId, e);
        }
    }

    @Override
    public List<Item> getItems(Long bucketId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * "
                + "FROM items INNER JOIN buckets_items "
                + "ON buckets_items.item_id=items.item_id "
                + "WHERE buckets_items.bucket_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setId(itemId);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Getting items from bucket failed", e);
        }
        return items;
    }
}

