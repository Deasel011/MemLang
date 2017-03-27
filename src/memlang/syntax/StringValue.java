package memlang.syntax;

/**
 * Created by pdesl on 2017-03-25.
 */
public class StringValue extends Value {

    private String value;

    StringValue(
            String value) {
        this.value = value;
    }

    public String getValue() {

        return this.value;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((StringValue) obj).value.equals(this.value);
    }

    @Override
    public String toString() {

        return "" + this.value.substring(1,this.value.length()-1);
    }
}