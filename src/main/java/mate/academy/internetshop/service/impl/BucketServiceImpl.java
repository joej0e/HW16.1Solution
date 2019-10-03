package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    private static BucketServiceImpl bucketService = new BucketServiceImpl();

    private BucketServiceImpl() {
    }

    public static BucketServiceImpl getInstance() {
        return bucketService;
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
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
    public Bucket addItem(Bucket bucket, Item item) {
        return bucketDao.addItem(bucket, item);
    }

    @Override
    public void clear(Long id) {
        bucketDao.clear(id);
    }

    @Override
    public List<Item> getItems(Long bucketId) {
        return bucketDao.getItems(bucketId);
    }

    @Override
    public void deleteItem(Long bucketId, Item item) {
        bucketDao.deleteItem(bucketId, item);
    }
}

