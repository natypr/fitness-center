package by.naty.fitnesscenter.model.entity;

public enum UserType {
    ADMIN("admin", 0),
    TRAINER("trainer", 1),
    CLIENT("client", 2),
    GUEST("guest", 3);

    private final String typeUser;
    private final int id;

    UserType(String typeUser, int id) {
        this.typeUser = typeUser;
        this.id=id;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public int getId() {
        return id;
    }
}
