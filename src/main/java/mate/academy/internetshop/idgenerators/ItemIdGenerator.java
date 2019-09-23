package mate.academy.internetshop.idgenerators;

public class ItemIdGenerator {
    private static long id = 0L;

    public static long generateId() {
        return id++;
    }
}

