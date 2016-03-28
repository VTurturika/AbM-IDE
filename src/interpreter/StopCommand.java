package interpreter;

/**
 * Command that already is final
 */
public class StopCommand extends Command {

    public StopCommand() {
        super();
        stop = true;
    }
}
