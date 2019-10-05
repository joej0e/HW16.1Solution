package mate.academy.internetshop.model;

public class Role {
    private Long id;
    private RoleName roleName;

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }
}

