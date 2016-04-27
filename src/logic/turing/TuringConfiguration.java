package logic.turing;

import logic.interpreter.*;

import java.util.LinkedList;

public class TuringConfiguration extends Configuration {

    private LinkedList<Character> tape;
    private int head;
    private int currentState;

    public TuringConfiguration(String tape) {

        this.tape = new LinkedList<>();
        this.head = 0;
        this.tape.add('$');

        for (int i = 0; i <tape.length() ; i++) {
            this.tape.add(tape.charAt(i)) ;
        }
        this.tape.add('$');

        this.currentState = 0;
    }

    public TuringConfiguration() {

        this.tape = new LinkedList<>();
        this.tape.add('$');
        this.head = 0;
        this.currentState = 0;
    }

    /*Tape methods*/

    public char getSymbol() {

        return tape.get(head);
    }

    public void setSymbol(char s) {
        tape.set(head, s);
    }


    public void deleteSymbol() {
        if(tape.size() > 1 ) {

           if( !hasRight() ) {
               tape.remove(head);
               head--;
           }
           else {
               tape.remove(head);
           }
        }
    }

    public void eraseSymbol(){

        tape.set(head, '$');
    }

    public void insertLeft(char c) {
        tape.add(head, c);
        head++;
    }

    public void insertRight(char c) {
        if(hasRight()) {
            tape.add(head+1, c);
        }
        else {
            tape.add(c);
        }
    }

    public void clearTape() {
        tape.clear();
        tape.add('$');
        head = 0;
    }

    public void setTape(String str) {

        tape.clear();
        tape.add('$');
        for (int i = 0; i <str.length(); i++) {
            this.tape.add(str.charAt(i));
        }
        tape.add('$');

        moveHeadToBegin();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i <tape.size() ; i++) {
            if( i == head ) {
                result += "^ " + tape.get(i) + " ";
            }
            else {
                result += tape.get(i) + " ";
            }
        }
        return result;
    }

    /*State methods*/

    public int getState() {
        return currentState;
    }

    public void setState(int state) {

        currentState = state;
    }

    /*Head methods*/
    public void moveHeadLeft() {

        if(hasLeft()) {
            head--;
        }
    }

    public void moveHeadRight() {

        if( hasRight()) {
            head++;
        }
    }

    public void setHeadToSymbol(char c) {

        int newPosition = tape.indexOf(c);
        if( newPosition != -1) {
            head = newPosition;
        }
    }

    public boolean hasLeft() {

        return head > 0;
    }

    public boolean hasRight() {

        return head+1 != tape.size();
    }

    public void moveHeadToBegin() {
        head = 0;
    }

    public void moveHeadToEnd() {
        head = tape.size() - 1;
    }
}
