package by.naty.fitnesscenter.model.entity;

import java.util.Objects;

public class User {
    private long id;
    private String role;
    private String name;
    private String surname;
    private String gender;
    private byte yearOld;
    private String email;
    private String password;
    private boolean blocked;

    public User() {
    }

    public User(String role, String name, String surname,
                String gender, byte yearOld, String email, String password) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.yearOld = yearOld;
        this.email = email;
        this.password = password;
    }

    public User(long id, String role, String name, String surname,
                String gender, byte yearOld, String email, String password, boolean blocked) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.yearOld = yearOld;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        User user = (User) o;

        if (id != user.id) return false;
        if (yearOld != user.yearOld) return false;
        if (blocked != user.blocked) return false;
        if (!Objects.equals(role, user.role)) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!Objects.equals(gender, user.gender)) return false;
        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (int) yearOld;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", role='").append(role).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", yearOld=").append(yearOld);
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", blocked=").append(blocked);
        sb.append('}');
        return sb.toString();
    }
}
