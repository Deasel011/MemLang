package memlang.syntax.memoryCheat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Created by Philippe on 2017-03-27.
 */
public class wsToPython implements memInterface{
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdIn;
    public wsToPython(){
        try {

            socket = new Socket("127.0.0.1", 51337);

            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            stdIn = new BufferedReader(
                    new InputStreamReader(System.in));

            out.print("Test 12");

            out.close();
            in.close();
            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("Unknown Host.");
            // System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection.");
            //  System.exit(1);
        }
    }

    @Override
    public boolean openProcess(String name) {
        try {
            writeToSocket("open:"+name);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public HashMap<String, String> find(String value) {

        return null;
    }

    @Override
    public boolean modify(String address, String newValue) {
        return false;
    }


    private void writeToSocket(String s) throws IOException {
        socket = new Socket("127.0.0.1", 51337);

        out = new PrintWriter(socket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        stdIn = new BufferedReader(
                new InputStreamReader(System.in));

        out.print(s);

        out.close();
        in.close();
        socket.close();
    }

}
