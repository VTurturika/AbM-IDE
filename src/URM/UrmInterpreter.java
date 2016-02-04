package URM;

import interpreter.*;

public class UrmInterpreter extends Interpreter {

    private UrmCommandIndex index;
    private UrmConfiguration registerState;
    private boolean hasNextCommand;

    public UrmInterpreter() {

        this.index = new UrmCommandIndex();
        this.registerState = null;
        this.hasNextCommand = false;
    }

    @Override
    public void setInput(Configuration input) {
        this.registerState = (UrmConfiguration) input.getInstance();
    }

    @Override
    protected Command getFirstCommand() {

        return (registerState != null) ? program.getCommand(index) : new StopCommand();
    }

    @Override
    protected Command nextCommand() {

        if( !hasNextCommand ) {
            index.nextIndex();
        }
        hasNextCommand = false;

        if(index.getIndex() >= program.getCommandNumber() ) {
            return new StopCommand();
        }
        else {
            return program.getCommand(index);
        }
    }


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
