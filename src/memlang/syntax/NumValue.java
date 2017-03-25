package memlang.syntax;

import java.math.BigInteger;

/**
 * Created by pdesl on 2017-03-25.
 */
public class NumValue extends Value {

    private int value;

    NumValue(
            int value) {
        this.value = value;
    }

    public int getValue() {

        return this.value;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((NumValue) obj).value == this.value;
    }

    @Override
    public String toString() {

        return "" + this.value;
    }
}
