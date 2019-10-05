package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketDao {

    Bucket addItem(Bucket bucket, Item item);

    void deleteItem(Long bucketId, Item item);

    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long id);

    Bucket update(Bucket bucket);

    void clear(Long id);

    List<Item> getItems(Long bucketId);
}

