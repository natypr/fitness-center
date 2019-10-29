package by.naty.fitnesscenter.model.entity;

public enum UserType {
    ADMIN("admin"),
    TRAINER("trainer"),
    CLIENT("client"),
    GUEST("guest");

    private String typeUser;

    UserType(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
