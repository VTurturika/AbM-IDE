package URM;

import interpreter.Command;

public class UrmCommand extends Command {

    private String type;
    private int firstArgument;
    private int secondArgument;
    private int thirdArgument;

    public UrmCommand(String type, int firstArgument, int secondArgument , int thirdArgument) {

        super();
        this.type = type;
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
        this.thirdArgument = thirdArgument;
    }

    public UrmCommand() {
        this("", -1, -1, -1);
    }

    public UrmCommand(String type, int firstArgument, int secondArgument) {
        this(type, firstArgument, secondArgument, -1);
    }


    public UrmCommand(String type, int firstArgument) {
        this(type, firstArgument, -1, -1);
    }

    public int getThirdArgument() {
        return thirdArgument;
    }

    public String getType() {
        return type;
    }

    public int getFirstArgument() {
        return firstArgument;
    }

    public int getSecondArgument() {
        return secondArgument;
    }

    @Override
    public String toString() {
        return type + "(" + firstArgument +
               ( (secondArgument == -1) ? "" : " " + secondArgument) +
               ( (thirdArgument == -1) ? "" : " " + thirdArgument) + ")";
    }
}
