package util;

/**
 * Utility class used to compare objects of type Comparable
 */
public class Compare {
    private Compare() {
    }

    public static boolean lessThan(Comparable thisKey, Comparable thatKey) {
        return thisKey.compareTo(thatKey) < 0;
    }

    public static boolean largerThan(Comparable thisKey, Comparable thatKey) {
        return thisKey.compareTo(thatKey) > 0;
    }

    public static boolean equals(Comparable thisKey, Comparable thatKey) {
        return thisKey.compareTo(thatKey) == 0;
    }

    public static boolean lessOrEquals(Comparable thisKey, Comparable thatKey) {
        return thisKey.compareTo(thatKey) <= 0;
    }

    public static boolean largerOrEquals(Comparable thisKey, Comparable thatKey) {
        return thisKey.compareTo(thatKey) >= 0;
    }
}
