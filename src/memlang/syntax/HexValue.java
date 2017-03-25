package memlang.syntax;

import java.math.BigInteger;

/**
 * Created by pdesl on 2017-03-25.
 */
public class HexValue extends Value {

    private BigInteger value;

    HexValue(
            BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {

        return this.value;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((HexValue) obj).value == this.value;
    }

    @Override
    public String toString() {

        return "" + this.value;
    }
}