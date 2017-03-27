package memlang.syntax;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * Adapted from Hybris95's raw buggy code from UnknownCheats.com
 */
public class WindowsMemoryManipulator {
    private static final boolean DEBUG = false;
    static Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
    public static MyKernel32 myKernel32 = (MyKernel32) Native.loadLibrary("kernel32", MyKernel32.class);
    static String usage = "Usage: java MyProcessReaderExample [processName] [readSize] [readAddress] [readOffset]" + System.getProperty("line.separator") + "processName example: firefox.exe" + System.getProperty("line.separator") + "readSize (in bytes) example : 4" + System.getProperty("line.separator") + "readAddress (hexadecimal) example : 00010000" + System.getProperty("line.separator") + "readOffset (decimal) example : 0";
    public static int PROCESS_VM_READ= 0x0010;
    public static int PROCESS_VM_WRITE = 0x0020;
    public static int PROCESS_VM_OPERATION = 0x0008;
    private static int PID = 0;

    interface MyKernel32 extends StdCallLibrary
    {
        boolean WriteProcessMemory(Pointer p, int address, Pointer buffer, int size, IntByReference written);
        boolean ReadProcessMemory(Pointer hProcess, int inBaseAddress, Pointer outputBuffer, int nSize, IntByReference outNumberOfBytesRead);
        Pointer OpenProcess(int desired, boolean inherit, int pid);
        int GetLastError();
    }

    public static long FindProcessId(String processName)
    {
        // This Reference will contain the processInfo that will be parsed to recover the ProcessId
        Tlhelp32.PROCESSENTRY32.ByReference processInfo = new Tlhelp32.PROCESSENTRY32.ByReference();

        // This Handle allows us to parse the process map
        WinNT.HANDLE processesSnapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0L));
        if(processesSnapshot == kernel32.INVALID_HANDLE_VALUE)
        {
            if(DEBUG) System.err.println("INVALID_HANDLE_VALUE");
            return 0L;
        }

        try{// This will parse all the processes to find the process id corresponding to the process name
            kernel32.Process32First(processesSnapshot, processInfo);
            if(processName.equals(Native.toString(processInfo.szExeFile)))
            {
                if(DEBUG) System.out.println("Process " + processName + " found : " + processInfo.th32ProcessID.longValue());
                return processInfo.th32ProcessID.longValue();
            }

            while(kernel32.Process32Next(processesSnapshot, processInfo))
            {
                if(processName.equals(Native.toString(processInfo.szExeFile)))
                {
                    if(DEBUG) System.out.println("Process " + processName + " found : " + processInfo.th32ProcessID.longValue());
                    return processInfo.th32ProcessID.longValue();
                }
            }

            if(DEBUG) System.out.println("Did not found the requested Process: " + processName);
            return 0L;
        }
        finally
        {
            kernel32.CloseHandle(processesSnapshot);
        }
    }

    public static void getBaseAddress(int pid){
        WinNT.HANDLE process = kernel32.OpenProcess(PROCESS_VM_READ|PROCESS_VM_WRITE|PROCESS_VM_OPERATION, true, pid);
//        kernel32
    }

    public static Pointer OpenProcess(int pid) {
        PID = pid;
        WinNT.HANDLE process = kernel32.OpenProcess(PROCESS_VM_READ|PROCESS_VM_WRITE|PROCESS_VM_OPERATION, true, pid);
        assert process != null;
        return process.getPointer();
    }

    public boolean ReadProcessMemory(Pointer hProcess, int inBaseAddress, Pointer outputBuffer, int nSize, IntByReference outNumberOfBytesRead){
        return myKernel32.ReadProcessMemory(hProcess, inBaseAddress, outputBuffer, nSize, outNumberOfBytesRead);
    }

    public int GetLastError(){
        return myKernel32.GetLastError();
    }
}
