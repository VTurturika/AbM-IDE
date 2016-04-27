package logic.alphabet;

/**
 * Represents special "EmptySymbol" from any {@code Alphabet}
 */
public class EmptySymbol {

    private static char value = '$';

    public static char get() {
        return value;
    }

    public static void set(char c) {
        value = c;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}