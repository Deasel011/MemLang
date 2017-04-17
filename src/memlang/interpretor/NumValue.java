package memlang.interpretor;

/**
 * Created by pdesl on 2017-03-25.
 * Future: extend this to dynamically look for floats, 4 byte ints, 8 bytes ints and more!
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
