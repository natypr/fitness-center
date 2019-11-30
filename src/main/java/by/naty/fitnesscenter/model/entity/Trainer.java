package by.naty.fitnesscenter.model.entity;

public class Trainer extends User {
    private long id;
    private String education;
    private double costPerOneWorkout;

    public Trainer() {
    }

    public Trainer(User user) {
        super(user.getRole(), user.getName(), user.getSurname(), user.getGender(), user.getYearOld(),
                user.getEmail(), user.getPassword());
        this.education = "master";
        this.costPerOneWorkout = 0.0;
    }

    public Trainer(User user, String education, double costPerOneWorkout) {
        super(user.getRole(), user.getName(), user.getSurname(), user.getGender(), user.getYearOld(),
                user.getEmail(), user.getPassword());
        this.education = education;
        this.costPerOneWorkout = costPerOneWorkout;
    }

    public Trainer(User user, long id, String education, double costPerOneWorkout) {
        super(user.getRole(), user.getName(), user.getSurname(), user.getGender(), user.getYearOld(),
                user.getEmail(), user.getPassword());
        this.id = id;
        this.education = education;
        this.costPerOneWorkout = costPerOneWorkout;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public double getCostPerOneWorkout() {
        return costPerOneWorkout;
    }

    public void setCostPerOneWorkout(double costPerOneWorkout) {
        this.costPerOneWorkout = costPerOneWorkout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (id != trainer.id) return false;
        if (Double.compare(trainer.costPerOneWorkout, costPerOneWorkout) != 0) return false;
        return education != null ? education.equals(trainer.education) : trainer.education == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (education != null ? education.hashCode() : 0);
        temp = Double.doubleToLongBits(costPerOneWorkout);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("id=").append(id);
        sb.append(", education='").append(education).append('\'');
        sb.append(", costPerOneWorkout=").append(costPerOneWorkout);
        sb.append('}');
        return sb.toString();
    }
}
