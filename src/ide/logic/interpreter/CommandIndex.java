package ide.logic.interpreter;

/**
 * Abstarct pointer that represents current position of {@code Command} in {@code Program}
 */
abstract public class CommandIndex {

    /**
     * Returns reference to {@code CommandIndex} instance
     *
     * @return reference to {@code CommandIndex} instance
     */
    public Object getIndexInstance() {
        return this;
    }
    abstract public void setIndex(CommandIndex c);

}
