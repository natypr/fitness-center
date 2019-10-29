package by.naty.fitnesscenter.model.entity;

public class Trainer extends User {
    private long idTrainer;
    private String education;
    private double costPerHour;

    public Trainer() {
    }

    public Trainer(User user) {
        super(user.getIdUser(), user.getRole(), user.getName(), user.getSurname(),
                user.getSex(), user.getYearOld(), user.getEmail(), user.getPassword());
        education = "master";
        costPerHour = 0.0;
    }

    public Trainer(User user, long idTrainer, String education, double costPerHour){
        super(user.getIdUser(), user.getRole(), user.getName(), user.getSurname(),
                user.getSex(), user.getYearOld(), user.getEmail(), user.getPassword());
        this.idTrainer = idTrainer;
        this.education = education;
        this.costPerHour = costPerHour;
    }

    public Trainer(long idUser, String role, String name, String surname, String email, String password,
                   int yearOld, String sex, long idTrainer, String education, double costPerHour) {
        super(idUser, role, name, surname, sex, yearOld, email, password);
        this.idTrainer = idTrainer;
        this.education = education;
        this.costPerHour = costPerHour;
    }

    public long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (idTrainer != trainer.idTrainer) return false;
        if (Double.compare(trainer.costPerHour, costPerHour) != 0) return false;
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
        return result;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "idTrainer=" + idTrainer +
                ", education='" + education + '\'' +
                ", costPerHour=" + costPerHour +
                '}';
    }
}
