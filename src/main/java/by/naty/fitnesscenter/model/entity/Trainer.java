package by.naty.fitnesscenter.model.entity;

public class Trainer extends User {
    private long idTrainer;
    private String education;
    private double costPerHour;
    private boolean blocked;

    public Trainer() {
    }

    public Trainer(long idTrainer, String education, double costPerHour, boolean blocked) {
        this.idTrainer = idTrainer;
        this.education = education;
        this.costPerHour = costPerHour;
        this.blocked = blocked;
    }

    public Trainer(long idUser, String role, String name, String surname, String email, String password,
                   long idTrainer, String education, double costPerHour, boolean blocked) {
        super(idUser, role, name, surname, email, password);
        this.idTrainer = idTrainer;
        this.education = education;
        this.costPerHour = costPerHour;
        this.blocked = blocked;
    }

    public long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(long idTrainer) {
        this.idTrainer = idTrainer;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (idTrainer != trainer.idTrainer) return false;
        if (Double.compare(trainer.costPerHour, costPerHour) != 0) return false;
        if (blocked != trainer.blocked) return false;
        return education != null ? education.equals(trainer.education) : trainer.education == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (idTrainer ^ (idTrainer >>> 32));
        result = 31 * result + (education != null ? education.hashCode() : 0);
        temp = Double.doubleToLongBits(costPerHour);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("idTrainer=").append(idTrainer);
        sb.append(", education='").append(education).append('\'');
        sb.append(", costPerHour=").append(costPerHour);
        sb.append(", blocked=").append(blocked);
        sb.append('}');
        return sb.toString();
    }
}
