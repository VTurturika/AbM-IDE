package interpreter;

abstract public class Interpreter {

    private Command currentCommand;
    protected Program program;

    public void runProgram() {

        currentCommand = getFirstCommand();
        while (!isStop()) {

            execute(currentCommand);
            currentCommand = nextCommand();
        }
    }

    public void loadProgram(Program program) {
        this.program = program;
    }

    //    abstract public void setFirstCommand(CommandIndex c);
    abstract public void setInput(Configuration input);

    private boolean isStop() {
        return currentCommand.isStop();
    }

    abstract protected Command getFirstCommand();

    abstract protected Command nextCommand();

    abstract protected void execute(Command c);
}
