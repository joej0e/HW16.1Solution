package mate.academy.internetshop.model;

import mate.academy.internetshop.idgenerators.RoleIdGenerator;

public class Role {
    private Long id;
    private RoleName roleName;

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Role() {
        this.id = RoleIdGenerator.generateId();
    }

    public enum RoleName {
        USER, ADMIN
    }
}

