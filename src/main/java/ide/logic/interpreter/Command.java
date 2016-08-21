package ide.logic.interpreter;

/**
 * Abstract class that represents general interface for commands of different abstract machines
 */
abstract public class Command {

    /**Indicates that this {@code Command} is final */
    boolean stop;

    public Command() {
        stop = false;
    }

    /**
     * Checks if this {@code Command} is final
     *
     * @return true if this {@code Command} is final, else returns false
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Sets type of {@code Command} (final / not final)
     *
     * @param stop type of {@code Command} (if true - command is final, else command isn't final)
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Returns reference to {@code Command} instance
     *
     * @return reference to {@code Command} instance
     */
    public Object getInstance() {
        return this;
    }
}
