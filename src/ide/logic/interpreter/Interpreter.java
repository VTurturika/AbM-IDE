package ide.logic.interpreter;

/**
 * Abstract ide.logic.interpreter that define main functionality without implementations
 */
abstract public class Interpreter {

    private Command currentCommand = new StopCommand();
    protected Program program;
    protected Logger logger = new Logger();
    private int stepCounter = 1;
    private volatile boolean isInterrupt = false;

    /**
     * Begins execution of current program by {@code Interpreter}
     */
    public void runProgram() {

        reset();
        currentCommand = getFirstCommand();
        while (!isInterrupt && !isStop()) {

            execute(currentCommand);
            writeLogs();
            currentCommand = nextCommand();
            stepCounter++;

            System.out.println("running");
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

    public void stop() {
      isInterrupt = true;
    }

    public boolean isInterrupted() {
        return isInterrupt;
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

    public void reset() {
        currentCommand = null;
        isInterrupt = false;
        stepCounter = 1;
        logger.clear();
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public Logger getLogger() {
        return logger;
    }

    protected int getCurrentStep() {
        return stepCounter;
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

    /**
     * Execute specified {@code Command} by {@code Interpreter}
     * @param c {@code Command} that will be executed
     */
    abstract protected void execute(Command c);

    abstract protected void writeLogs();
}
