package interpreter;

/**
 * Interface that represents abstract container for commands with general interface for different abstract machines
 */
public interface Program extends Iterable<Command> {

    /**
     * Adds command to {@code Program}
     *
     * @param c {@code Command} that will be added
     */
    void addCommand(Command c);

    /**
     * Returns {@code Command} with specified {@code CommandIndex}
     *
     * @param i {@code CommandIndex} that specifies necessary {@code Command}
     * @return specified {@code Command}
     */
    Command getCommand( CommandIndex i );

    /**
     * Returns number of commands in {@code Program}
     *
     * @return number of commands in {@code Program}
     */
    int getNumberOfCommands();

    /**
     * Removes all commands from {@code Program}
     */
    void clearProgram();

    default Object getInstance() {return this;}
}
