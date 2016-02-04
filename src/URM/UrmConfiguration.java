package URM;

import interpreter.*;
import java.util.ArrayList;

public class UrmConfiguration extends Configuration {

    private ArrayList<Integer> registers;

    public UrmConfiguration(int size) {

        this.registers = new ArrayList<>(size);

        for (int i = 0; i <size ; i++) {
            registers.add(0);
        }
    }

    public UrmConfiguration() {
        this(0);
    }

    public int getRegister(int index) {
        return registers.get(index);
    }

    public void setRegister(int index, int value) {
        registers.set(index, value);
    }

    public int[] getRegisters() {

        return registers.stream().mapToInt(i -> i).toArray();
    }

    public void setRegisters(int[] values) {

        registers.clear();

        for (int i : values) {
            registers.add(i);
        }

    }

    public int getNumberRegisters() {
        return registers.size();
    }

    @Override
    public String toString() {

        String result = "";
        for(int i : registers){
            result +=( "|" + String.valueOf(i) );
        }
        result += "|";
        return result;
    }
}
