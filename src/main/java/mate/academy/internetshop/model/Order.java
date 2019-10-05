package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Item> items;
    private Long userId;

    public Order(Long id, List<Item> items, Long userId) {
        this.id = id;
        this.items = items;
        this.userId = userId;
    }

    public Order() {
    }

    public Order(List<Item> items, Long userId) {
        this.items = items;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", userId=" + userId +
                '}';
    }
}

