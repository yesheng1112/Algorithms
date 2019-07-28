package book2;

public class Date {
    private final int value;

    public Date(int m, int d, int y) {
        value = y * 512 + m * 32 + d;
    }

    public int month() {
        return (value / 32) % 16;
    }

    public int day() {
        return value % 32;
    }

    public int year() {
        return value / 512;
    }

    @Override
    public String toString() {
        return month() + "/" + day() + "/" + year();
    }
}
