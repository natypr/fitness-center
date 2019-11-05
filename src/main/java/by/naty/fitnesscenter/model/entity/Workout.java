package by.naty.fitnesscenter.model.entity;

public class Workout {
    private long id;
    private String typeWorkout;
    private String nameOfWorkout;
    private String equipment;
    private String description;
    private double costPerOneWorkout;
    private int numberOfVisit;
    private long idTrainer;
    private long idOrder;

    public Workout() {
    }

    public Workout(long id, String typeWorkout, String nameOfWorkout, String equipment,
                   String description, double costPerOneWorkout, int numberOfVisit, long idTrainer, long idOrder) {
        this.id = id;
        this.typeWorkout = typeWorkout;
        this.nameOfWorkout = nameOfWorkout;
        this.equipment = equipment;
        this.description = description;
        this.costPerOneWorkout = costPerOneWorkout;
        this.numberOfVisit = numberOfVisit;
        this.idTrainer = idTrainer;
        this.idOrder = idOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeWorkout() {
        return typeWorkout;
    }

    public void setTypeWorkout(String typeWorkout) {
        this.typeWorkout = typeWorkout;
    }

    public String getNameOfWorkout() {
        return nameOfWorkout;
    }

    public void setNameOfWorkout(String nameOfWorkout) {
        this.nameOfWorkout = nameOfWorkout;
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

    public double getCostPerOneWorkout() {
        return costPerOneWorkout;
    }

    public void setCostPerOneWorkout(double costPerOneWorkout) {
        this.costPerOneWorkout = costPerOneWorkout;
    }

    public int getNumberOfVisit() {
        return numberOfVisit;
    }

    public void setNumberOfVisit(int numberOfVisit) {
        this.numberOfVisit = numberOfVisit;
    }

    public long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(long idTrainer) {
        this.idTrainer = idTrainer;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workout workout = (Workout) o;

        if (id != workout.id) return false;
        if (Double.compare(workout.costPerOneWorkout, costPerOneWorkout) != 0) return false;
        if (numberOfVisit != workout.numberOfVisit) return false;
        if (idTrainer != workout.idTrainer) return false;
        if (idOrder != workout.idOrder) return false;
        if (typeWorkout != workout.typeWorkout) return false;
        if (nameOfWorkout != null ? !nameOfWorkout.equals(workout.nameOfWorkout) : workout.nameOfWorkout != null)
            return false;
        if (equipment != null ? !equipment.equals(workout.equipment) : workout.equipment != null) return false;
        return description != null ? description.equals(workout.description) : workout.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (typeWorkout != null ? typeWorkout.hashCode() : 0);
        result = 31 * result + (nameOfWorkout != null ? nameOfWorkout.hashCode() : 0);
        result = 31 * result + (equipment != null ? equipment.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(costPerOneWorkout);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numberOfVisit;
        result = 31 * result + (int) (idTrainer ^ (idTrainer >>> 32));
        result = 31 * result + (int) (idOrder ^ (idOrder >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Workout{");
        sb.append("idWorkout=").append(id);
        sb.append(", typeWorkout=").append(typeWorkout);
        sb.append(", nameOfWorkout='").append(nameOfWorkout).append('\'');
        sb.append(", equipment='").append(equipment).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", costPerOneWorkout=").append(costPerOneWorkout);
        sb.append(", numberOfVisit=").append(numberOfVisit);
        sb.append(", idTrainer=").append(idTrainer);
        sb.append(", idOrder=").append(idOrder);
        sb.append('}');
        return sb.toString();
    }
}
