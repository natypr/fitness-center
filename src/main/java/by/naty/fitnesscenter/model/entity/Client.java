package by.naty.fitnesscenter.model.entity;

import java.util.List;
import java.util.Objects;

public class Client extends User {
    private long id;
    private double discount;
    private List<Order> orderList;

    public Client() {
    }

    public Client(User user) {
        super(user.getRole(), user.getName(), user.getSurname(), user.getGender(), user.getYearOld(),
                user.getEmail(), user.getPassword());
        this.discount = 0;
        this.orderList = null;
    }

    public Client(User user, double discount, List<Order> orderList) {
        super(user.getRole(), user.getName(), user.getSurname(), user.getGender(), user.getYearOld(),
                user.getEmail(), user.getPassword());
        this.discount = discount;
        this.orderList = orderList;
    }

    public Client(User user, long id, double discount, List<Order> orderList) {
        super(user.getRole(), user.getName(), user.getSurname(), user.getGender(), user.getYearOld(),
                user.getEmail(), user.getPassword());
        this.id = id;
        this.discount = discount;
        this.orderList = orderList;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (Double.compare(client.discount, discount) != 0) return false;
        return Objects.equals(orderList, client.orderList);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (orderList != null ? orderList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", discount=").append(discount);
        sb.append(", orderList=").append(orderList);
        sb.append('}');
        return sb.toString();
    }
}
