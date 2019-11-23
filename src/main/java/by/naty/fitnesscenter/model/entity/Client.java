package by.naty.fitnesscenter.model.entity;

public class Client extends User {
    private long id;
    private String gender;
    private byte yearOld;
    private double discount;
    private boolean blocked;

    public Client() {
    }

    public Client(User user, String gender, byte yearOld, double discount) {
        super(user.getId(), user.getRole(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
        this.gender = gender;
        this.yearOld = yearOld;
        this.discount = discount;
    }

    public Client(User user, long id, String gender, byte yearOld, double discount) {
        super(user.getId(), user.getRole(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
        this.id = id;
        this.gender = gender;
        this.yearOld = yearOld;
        this.discount = discount;
    }

    public Client(long id, String gender, byte yearOld, double discount, boolean blocked) {
        this.id = id;
        this.gender = gender;
        this.yearOld = yearOld;
        this.discount = discount;
        this.blocked = blocked;
    }

    public Client(long idUser, String role, String name, String surname, String email, String password,
                  long id, String gender, byte yearOld, double discount, boolean blocked) {
        super(idUser, role, name, surname, email, password);
        this.id = id;
        this.gender = gender;
        this.yearOld = yearOld;
        this.discount = discount;
        this.blocked = blocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte getYearOld() {
        return yearOld;
    }

    public void setYearOld(byte yearOld) {
        this.yearOld = yearOld;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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

        Client client = (Client) o;

        if (id != client.id) return false;
        if (yearOld != client.yearOld) return false;
        if (Double.compare(client.discount, discount) != 0) return false;
        if (blocked != client.blocked) return false;
        return gender != null ? gender.equals(client.gender) : client.gender == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (int) yearOld;
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("idClient=").append(id);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", yearOld='").append(yearOld).append('\'');
        sb.append(", discount=").append(discount);
        sb.append(", blocked=").append(blocked);
        sb.append('}');
        return sb.toString();
    }
}
