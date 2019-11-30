package by.naty.fitnesscenter.model.entity;

public class Order {
    private long id;
    private String typeOfWorkout;
    private int numberOfWorkout;
    private long idTrainer;
    private String equipment;
    private String description;
    private long idClient;
    private boolean isPaid;

    public Order() {
    }

    public Order(long id, String typeOfWorkout,
                 int numberOfWorkout, long idTrainer, long idClient) {
        this.id = id;
        this.typeOfWorkout = typeOfWorkout;
        this.numberOfWorkout = numberOfWorkout;
        this.idTrainer = idTrainer;
        this.idClient = idClient;
        this.isPaid = false;
    }

    public Order(long id, String typeOfWorkout, int numberOfWorkout, long idTrainer,
                 long idClient, boolean isPaid) {
        this.id = id;
        this.typeOfWorkout = typeOfWorkout;
        this.numberOfWorkout = numberOfWorkout;
        this.idTrainer = idTrainer;
        this.idClient = idClient;
        this.isPaid = isPaid;
    }

    public Order(long id, String typeOfWorkout, int numberOfWorkout, long idTrainer,
                 String equipment, String description, long idClient, boolean isPaid) {
        this.id = id;
        this.typeOfWorkout = typeOfWorkout;
        this.numberOfWorkout = numberOfWorkout;
        this.idTrainer = idTrainer;
        this.equipment = equipment;
        this.description = description;
        this.idClient = idClient;
        this.isPaid = isPaid;
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

    public int getNumberOfWorkout() {
        return numberOfWorkout;
    }

    public void setNumberOfWorkout(int numberOfWorkout) {
        this.numberOfWorkout = numberOfWorkout;
    }

    public long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(long idTrainer) {
        this.idTrainer = idTrainer;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (numberOfWorkout != order.numberOfWorkout) return false;
        if (idTrainer != order.idTrainer) return false;
        if (idClient != order.idClient) return false;
        if (isPaid != order.isPaid) return false;
        if (typeOfWorkout != null ? !typeOfWorkout.equals(order.typeOfWorkout) : order.typeOfWorkout != null)
            return false;
        if (equipment != null ? !equipment.equals(order.equipment) : order.equipment != null) return false;
        return description != null ? description.equals(order.description) : order.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (typeOfWorkout != null ? typeOfWorkout.hashCode() : 0);
        result = 31 * result + numberOfWorkout;
        result = 31 * result + (int) (idTrainer ^ (idTrainer >>> 32));
        result = 31 * result + (equipment != null ? equipment.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        result = 31 * result + (isPaid ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", typeOfWorkout='").append(typeOfWorkout).append('\'');
        sb.append(", numberOfWorkout=").append(numberOfWorkout);
        sb.append(", idTrainer=").append(idTrainer);
        sb.append(", equipment='").append(equipment).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", idClient=").append(idClient);
        sb.append(", isPaid=").append(isPaid);
        sb.append('}');
        return sb.toString();
    }
}
