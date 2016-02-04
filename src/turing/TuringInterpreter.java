package turing;

import interpreter.*;

public class TuringInterpreter extends Interpreter {

    private TuringCommandIndex index;
    private TuringConfiguration tapeState;
    private int currentState;

    public TuringInterpreter() {

        this.index = new TuringCommandIndex();
        this.tapeState = null;
        this.currentState = 0;
    }

    @Override
    public void setInput(Configuration input) {
        this.tapeState = (TuringConfiguration) input.getInstance();
    }

    @Override
    protected Command getFirstCommand() {

        if(tapeState == null) {
            return new StopCommand();
        }
        else {
            index.setState( tapeState.getState() );
            index.setSymbol( tapeState.getSymbol() );

            return program.getCommand(index);
        }
    }

    @Override
    protected Command nextCommand() {

        if(currentState == -1) {
            return new StopCommand();
        }
        else {

            index.setSymbol(tapeState.getSymbol());
            index.setState(currentState);

            return program.getCommand(index);
        }
    }

    @Override
    protected void execute(Command c) {

        TuringCommand command = (TuringCommand) c.getInstance();

        tapeState.setSymbol( command.getSymbolAfter() );
        currentState = command.getStateAfter();

        switch( command.getDirection() ) {

            case LEFT:
                if( !tapeState.hasLeft() ) {
                    tapeState.insertLeft('$');
                }
                tapeState.moveHeadLeft();
                break;
            case RIGHT:
                if( !tapeState.hasRight() ) {
                    tapeState.insertRight('$');
                }
                tapeState.moveHeadRight();
                break;
            case NONE:
                break;
        }
    }
}
