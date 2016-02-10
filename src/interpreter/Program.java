package interpreter;

public interface Program  {

    void addCommand(Command c);
    Command getCommand( CommandIndex i );
    int getCommandNumber();
    void clearProgram();

    default Object getInstance() {return this;}
}
