package interpreter;

public interface Program extends Iterable<Command> {

    void addCommand(Command c);
    Command getCommand( CommandIndex i );
    int getCommandNumber();
    void clearProgram();

    default Object getInstance() {return this;}
}
