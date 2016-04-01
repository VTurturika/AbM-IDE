package URM;

import interpreter.*;
import java.util.ArrayList;

/**
 * Represents states of URM's registers
 */
public class UrmConfiguration extends Configuration {

    /**Saves values of registers*/
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

    /**
     * Returns value of specified register
     *
     * @param index zero-based index of needed register
     * @return value of specified register
     */
    public int getRegister(int index) {
        return registers.get(index);
    }

    /**
     * Sets value of specified register
     * @param index zero-based index of needed register
     * @param value value that will be assigned
     */
    public void setRegister(int index, int value) {
        registers.set(index, value);
    }

    /**
     * Gets value of registers as array of integer
     *
     * @return registers value as array of integer
     */
    public int[] getRegisters() {

        return registers.stream().mapToInt(i -> i).toArray();
    }

    /**
     *  Sets values of registers
     *
     * @param values values of registers that will be set
     */
    public void setRegisters(int[] values) {

        registers.clear();

        for (int i : values) {
            registers.add(i);
        }

    }

    public int getNumberOfRegisters() {
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
