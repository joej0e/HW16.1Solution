package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private static ItemServiceImpl itemService = new ItemServiceImpl();

    private ItemServiceImpl() {
    }

    public static ItemServiceImpl getInstance() {
        return itemService;
    }

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item update(Item item) {
        itemDao.update(item);
        return item;
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }


    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }
}

