package mate.academy.internetshop.model;

import mate.academy.internetshop.idgenerators.UserIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private List<Order> orders;
    private Bucket bucket;

    public User() {
        id = UserIdGenerator.generateId();
        orders = new ArrayList<>();
        bucket = new Bucket(this);
    }

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", orders=" + orders +
                ", bucket=" + bucket +
                '}';
    }
}