package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.Optional;

public interface BucketService {

    Optional<Bucket> get(Long id);

    Bucket update(Bucket bucket);

    Bucket addItem(Long bucketId, Long itemId);

    void clear(Long id);

    Bucket create(User user);

    List<Item> getItems(Long bucketId);

    void deleteItem(Long bucketId, Long itemId);
}

