package interpreter;

/**
 * Abstract interpreter that define main functionality without implementations
 */
abstract public class Interpreter {

    private Command currentCommand;
    protected Program program;

    /**
     * Begins execution of current program by {@code Interpreter}
     */
    public void runProgram() {

        currentCommand = getFirstCommand();
        while (!isStop()) {

            execute(currentCommand);
            currentCommand = nextCommand();
        }
    }

    /**
     * Sets concrete {@code Program} instance to {@code Interpreter}
     *
     * @param program instance of {@code Program} interface that will set
     */
    public void loadProgram(Program program) {
        this.program = program;
    }

    /**
     * Sets concrete {@code Configuration} input of system to {@code Interpreter}
     *
     * @param input instance of {@code Configuration} class that will set
     */
    abstract public void setInput(Configuration input);

    /**
     * Checking if currentCommand of {@code Program} is final
     *
     * @return true if {@code currentCommand} is final, else returns false
     */
    private boolean isStop() {
        return currentCommand.isStop();
    }

    /**
     * Determines and returns first command of {@code Program}
     *
     * @return fist command of {@code Program}
     */
    abstract protected Command getFirstCommand();

    /**
     * Determines and returns next command of {@code Program} that will be executed
     *
     * @return next command that will be executed
     */
    abstract protected Command nextCommand();

    abstract protected void execute(Command c);
}
