package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketService {
    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long id);

    Bucket update(Bucket bucket);

    Bucket addItem(Bucket bucket, Item item);

    void clear(Long id);

    List<Item> getItems(Long bucketId);

    void deleteItem(Long bucketId, Item item);
}

