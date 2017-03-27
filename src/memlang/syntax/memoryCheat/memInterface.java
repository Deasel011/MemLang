package memlang.syntax.memoryCheat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philippe on 2017-03-27.
 */
public interface memInterface {
    boolean openProcess(String name);
    HashMap<String,String> find(String value);//Returns a list of addresses
    boolean modify(String address, String newValue);
}
