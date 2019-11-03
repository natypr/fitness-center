package by.naty.fitnesscenter.model.entity;

public class Client extends User {
    private long idClient;
    private String gender;
    private String yearOld;
    private double discount;
    private boolean blocked;

    public Client() {
    }

    public Client(long idClient, String gender, String yearOld, double discount, boolean blocked) {
        this.idClient = idClient;
        this.gender = gender;
        this.yearOld = yearOld;
        this.discount = discount;
        this.blocked = blocked;
    }

    public Client(long idUser, byte role, String name, String surname, String email, String password,
                  long idClient, String gender, String yearOld, double discount, boolean blocked) {
        super(idUser, role, name, surname, email, password);
        this.idClient = idClient;
        this.gender = gender;
        this.yearOld = yearOld;
        this.discount = discount;
        this.blocked = blocked;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYearOld() {
        return yearOld;
    }

    public void setYearOld(String yearOld) {
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

        if (idClient != client.idClient) return false;
        if (Double.compare(client.discount, discount) != 0) return false;
        if (blocked != client.blocked) return false;
        if (gender != null ? !gender.equals(client.gender) : client.gender != null) return false;
        return yearOld != null ? yearOld.equals(client.yearOld) : client.yearOld == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (yearOld != null ? yearOld.hashCode() : 0);
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("idClient=").append(idClient);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", yearOld='").append(yearOld).append('\'');
        sb.append(", discount=").append(discount);
        sb.append(", blocked=").append(blocked);
        sb.append('}');
        return sb.toString();
    }
}
