package ua.shield.enumer;

/**
 * Created by sa on 02.09.17.
 */
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER"), ROLE_GUEST("ROLE_GUEST");
    private String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}

