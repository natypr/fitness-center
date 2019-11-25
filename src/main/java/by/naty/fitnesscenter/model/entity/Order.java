package by.naty.fitnesscenter.model.entity;

public class Order {
    private long id;
    private String typeOfWorkout;
    private int countOfWorkout;
    private long idTrainer;
    private long idClient;

    public Order() {
    }

    public Order(long id, String typeOfWorkout, int countOfWorkout, long idTrainer, long idClient) {
        this.id = id;
        this.typeOfWorkout = typeOfWorkout;
        this.countOfWorkout = countOfWorkout;
        this.idTrainer = idTrainer;
        this.idClient = idClient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeOfWorkout() {
        return typeOfWorkout;
    }

    public void setTypeOfWorkout(String typeOfWorkout) {
        this.typeOfWorkout = typeOfWorkout;
    }

    public int getCountOfWorkout() {
        return countOfWorkout;
    }

    public void setCountOfWorkout(int countOfWorkout) {
        this.countOfWorkout = countOfWorkout;
    }

    public long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(long idTrainer) {
        this.idTrainer = idTrainer;
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

        if (id != order.id) return false;
        if (countOfWorkout != order.countOfWorkout) return false;
        if (idTrainer != order.idTrainer) return false;
        if (idClient != order.idClient) return false;
        return typeOfWorkout != null ? typeOfWorkout.equals(order.typeOfWorkout) : order.typeOfWorkout == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (typeOfWorkout != null ? typeOfWorkout.hashCode() : 0);
        result = 31 * result + countOfWorkout;
        result = 31 * result + (int) (idTrainer ^ (idTrainer >>> 32));
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", typeOfWorkout='").append(typeOfWorkout).append('\'');
        sb.append(", countOfWorkout=").append(countOfWorkout);
        sb.append(", idTrainer=").append(idTrainer);
        sb.append(", idClient=").append(idClient);
        sb.append('}');
        return sb.toString();
    }
}
