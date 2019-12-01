package by.naty.fitnesscenter.model.entity;

public enum UserType {
    ADMIN("admin"),
    TRAINER("trainer"),
    CLIENT("client");

    private final String typeUser;

    UserType(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getTypeUser() {
        return typeUser;
    }
}
