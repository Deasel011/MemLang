import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.IntByReference;
import memlang.syntax.WindowsMemoryManipulator;
import memlang.syntax.memoryCheat.memInterface;
import memlang.syntax.memoryCheat.wsToPython;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pdesl on 2017-03-25.
 */
public class Test {
    public static int PROCESS_VM_READ= 0x0010;
    public static int PROCESS_VM_WRITE = 0x0020;
    public static int PROCESS_VM_OPERATION = 0x0008;
    public static int SIZE = 4;
    static WindowsMemoryManipulator manip = new WindowsMemoryManipulator();

    public static void main(String[] args){
        int readSize = 0;
        int readValue = 0;
        int readAddress = 0;
        int readOffset = 0;


//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s = null;
//        try {
//            s = br.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(s);
//        long id = WindowsMemoryManipulator.FindProcessId("Game.exe");
//        System.out.println(id);
//        assert id != 0;
//        Pointer process = WindowsMemoryManipulator.OpenProcess((int)id);
//
//        Memory output = new Memory(10000);
//        manip.ReadProcessMemory(process,0xFFFFFFFF,output,64,new IntByReference(0));
//        System.out.println(output.getByteBuffer(0,10000).asCharBuffer());

        memInterface memory = new wsToPython();
        memory.openProcess("8856");
    }
}
