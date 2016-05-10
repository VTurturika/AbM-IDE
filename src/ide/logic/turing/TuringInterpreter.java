package ide.logic.turing;

import ide.logic.alphabet.EmptySymbol;
import ide.logic.interpreter.*;

/**
 * Implements Turing Machine
 */
public class TuringInterpreter extends Interpreter {

    private TuringCommandIndex index;
    private TuringConfiguration tapeState; //state of input tape
    private int currentState;

    public TuringInterpreter() {

        this.index = new TuringCommandIndex();
        this.tapeState = null;
        this.currentState = 0;
    }

    /**{@inheritDoc}*/
    @Override
    public void setInput(Configuration input) {
        this.tapeState = (TuringConfiguration) input.getInstance();
    }

    /**{@inheritDoc}*/
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

    /**{@inheritDoc}*/
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

    /**{@inheritDoc}*/
    @Override
    protected void execute(Command c) {

        TuringCommand command = (TuringCommand) c.getInstance();

        tapeState.setSymbol( command.getSymbolAfter() );
        currentState = command.getStateAfter();

        switch( command.getDirection() ) {

            case LEFT:
                if( !tapeState.hasLeft() ) {
                    tapeState.insertLeft(EmptySymbol.get());
                }
                tapeState.moveHeadLeft();
                break;
            case RIGHT:
                if( !tapeState.hasRight() ) {
                    tapeState.insertRight(EmptySymbol.get());
                }
                tapeState.moveHeadRight();
                break;
            case NONE:
                break;
        }
    }

    @Override
    protected boolean canExecute() {
        return (super.program != null && tapeState != null);
    }

    @Override
    protected void reset() {
        super.reset();
    }


    @Override
    protected void writeLogs() {
        logger.add("Step: " + getCurrentStep() + "\n" +
                "Command: " + getCurrentCommand() + "\n" +
                "State: " + tapeState.toString());
    }
}
