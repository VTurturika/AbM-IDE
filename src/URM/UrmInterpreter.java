package URM;

import interpreter.*;

/**
 * Implements Unlimited Register Machine
 */
public class UrmInterpreter extends Interpreter {

    /**Points to current execution command*/
    private UrmCommandIndex index;
    /**Saves current states of registers*/
    private UrmConfiguration registerState;
    /**Indicates that next command isn't finish*/
    private boolean hasNextCommand;

    public UrmInterpreter() {

        this.index = new UrmCommandIndex();
        this.registerState = null;
        this.hasNextCommand = false;
    }

    /**{@inheritDoc}*/
    @Override
    public void setInput(Configuration input) {
        this.registerState = (UrmConfiguration) input.getInstance();
    }

    /**{@inheritDoc}*/
    @Override
    protected Command getFirstCommand() {

        return (registerState != null) ? program.getCommand(index) : new StopCommand();
    }

    /**{@inheritDoc}*/
    @Override
    protected Command nextCommand() {

        if( !hasNextCommand ) {
            index.nextIndex();
        }
        hasNextCommand = false;

        if(index.getIndex() >= program.getNumberOfCommands() ) {
            return new StopCommand();
        }
        else {
            return program.getCommand(index);
        }
    }

    /**{@inheritDoc}*/
    @Override
    protected void execute(Command c) {

        UrmCommand command = (UrmCommand) c.getInstance();
        int first  = command.getFirstArgument(),
            second = command.getSecondArgument(),
            third  = command.getThirdArgument();

        switch( command.getType() ) {
            case "z":
                registerState.setRegister(first, 0);
                break;
            case "s":
                registerState.setRegister(first, registerState.getRegister(first) + 1);
                break;
            case "t":
                registerState.setRegister(second, registerState.getRegister(first));
                break;
            case "j":
                if( registerState.getRegister(first) == registerState.getRegister(second) ) {
                    index.setIndex(third-1);
                    hasNextCommand = true;
                }
                break;
            default:
                throw new RuntimeException("Wrong type of command");
        }

        //System.out.println(registerState);
    }
}
