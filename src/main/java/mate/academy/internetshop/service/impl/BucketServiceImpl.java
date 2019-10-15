package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    Logger logger = Logger.getLogger(BucketServiceImpl.class);

    private static BucketServiceImpl bucketService = new BucketServiceImpl();

    private BucketServiceImpl() {
    }

    public static BucketServiceImpl getInstance() {
        return bucketService;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).orElseThrow();
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        bucketDao.update(bucket);
        return bucketDao.get(bucketId).orElseThrow();
    }

    @Override
    public void clear(Long id) {
        Bucket bucket = bucketDao.get(id).orElseThrow();
        bucket.getItems().clear();
        bucketDao.update(bucket);
    }

    @Override
    public Bucket create(User user) {
        return bucketDao.create(user);
    }

    @Override
    public List<Item> getItems(Long bucketId) {
        return bucketDao.get(bucketId).get().getItems();
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).orElseThrow();
        Item item = itemDao.get(itemId);
        bucket.getItems().removeIf(i -> i.getId().equals(itemId));
        bucketDao.update(bucket);
    }
}

