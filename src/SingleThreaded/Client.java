package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException, IOException {

        // this is the server port.
        int port = 8090;

        // address: Defines the server's hostname (localhost), resolving to the loopback IP address (127.0.0.1).
        // InetAddress is a class in Java's java.net package used to represent Internet Protocol (IP) addresses.
        // It provides methods to work with both IPv4 and IPv6 addresses and to perform DNS lookups.
        // Resolving a hostname to an IP address (forward DNS lookup).
        // It's immutable and thread-safe, making it safe for use in concurrent programs.
        InetAddress address = InetAddress.getByName("localhost");

        // Socket: Creates a TCP connection to the server at the specified address and port.
        Socket socket = new Socket(address, port);

        // BufferedReader and PrintWriter: Handle input and output streams.

        // toSocket: Sends data to the server.
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);

        // fromSocket: Reads data from the server.
        // Wraps an InputStreamReader with a BufferedReader for efficient reading.
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        toSocket.println("Hello World from socket "+socket.getLocalSocketAddress());

//        Receive a Response:
//        Reads a single line of the server's response.
        String line = fromSocket.readLine();

        toSocket.close();
        fromSocket.close();
        socket.close();
    }
    public static void main(String[] args) {
        Client singleThreadedWebServer_Client = new Client();
        try{
            singleThreadedWebServer_Client.run();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
