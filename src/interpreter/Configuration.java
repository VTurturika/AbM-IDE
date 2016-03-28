package interpreter;

/**
 * Abstract class-wrapper that represents general interface
 */
abstract public class Configuration {

    /**
     * Returns reference to {@code Configuration} instance
     *
     * @return reference to {@code Configuration} instance
     */
    public Object getInstance() {
        return this;
    }
}
