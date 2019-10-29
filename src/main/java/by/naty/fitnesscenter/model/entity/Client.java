package by.naty.fitnesscenter.model.entity;

public class Client extends User {
    private long idClient;
    private double discount;

    public Client() {
    }

    public Client(User user) {
        super(user.getIdUser(), user.getRole(), user.getName(), user.getSurname(),
                user.getSex(), user.getYearOld(), user.getEmail(), user.getPassword());
        this.discount = 0.0;
    }

    public Client(User user, long idClient, double discount) {
        super(user.getIdUser(), user.getRole(), user.getName(), user.getSurname(),
                user.getSex(), user.getYearOld(), user.getEmail(), user.getPassword());
        this.idClient = idClient;
        this.discount = discount;
    }

    public Client(long idUser, String role, String name, String surname, String email, String password,
                  int yearOld, String sex, long idClient, double discount) {
        super(idUser, role, name, surname, sex, yearOld, email, password);
        this.idClient = idClient;
        this.discount = discount;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (idClient != client.idClient) return false;
        return Double.compare(client.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", discount=" + discount +
                '}';
    }
}
