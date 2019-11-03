package by.naty.fitnesscenter.model.entity;

public class Order {
    private long idOrder;
    private long idClient;

    public Order() {
    }

    public Order(long idOrder, long idClient) {
        this.idOrder = idOrder;
        this.idClient = idClient;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (idOrder != order.idOrder) return false;
        return idClient == order.idClient;
    }

    @Override
    public int hashCode() {
        int result = (int) (idOrder ^ (idOrder >>> 32));
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("idOrder=").append(idOrder);
        sb.append(", idClient=").append(idClient);
        sb.append('}');
        return sb.toString();
    }
}
