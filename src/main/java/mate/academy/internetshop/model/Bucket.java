package mate.academy.internetshop.model;

import mate.academy.internetshop.idgenerators.BucketIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private List<Item> items;
    private User user;

    public Bucket(User user) {
        this.id = BucketIdGenerator.generateId();
        items = new ArrayList<>();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", items=" + items +
                ", user=" + user +
                '}';
    }
}