package turing;

import interpreter.*;

public class TuringCommand extends Command {

    private int stateAfter;
    private char symbolAfter;
    private Direction direction;

    private TuringCommandIndex before;

    public TuringCommand(int stateBefore, char symbolBefore, int stateAfter, char symbolAfter, Direction direction) {

        this.before = new TuringCommandIndex(stateBefore, symbolBefore);

        this.stateAfter = stateAfter;
        this.symbolAfter = symbolAfter;
        this.direction = direction;
    }

    public int getStateAfter() {
        return stateAfter;
    }

    public void setStateAfter(int stateAfter) {
        this.stateAfter = stateAfter;
    }

    public char getSymbolAfter() {
        return symbolAfter;
    }

    public void setSymbolAfter(char symbolAfter) {
        this.symbolAfter = symbolAfter;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TuringCommandIndex getBefore() {
        return before;
    }

    public void setBefore(TuringCommandIndex before) {
        this.before = before;
    }

    public void setStateBefore(int stateBefore) {
        before.setState(stateBefore);
    }

    public void setSymbolBefore(char symbolBefore) {
        before.setSymbol(symbolBefore);
    }

    @Override
    public String toString() {

        return  "q " + before.getState() + " " + before.getSymbol() + " -> " +
                "q " + ((stateAfter == -1) ? "*" : stateAfter) + " " +
                symbolAfter + " " + direction;
    }
}
