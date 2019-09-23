package mate.academy.internetshop.idgenerators;

public class UserIdGenerator {
    private static long id = 0L;

    public static long generateId() {
        return id++;
    }
}


