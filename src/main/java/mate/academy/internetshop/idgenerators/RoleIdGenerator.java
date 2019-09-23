package mate.academy.internetshop.idgenerators;

public class RoleIdGenerator {
    private static long id = 0L;

    public static long generateId() {
        return id++;
    }
}

