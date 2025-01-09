package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server {

    public void run() throws IOException, UnknownHostException{

        // port: The server listens on port 8010 for client connections.
        int port = 8010;

        // ServerSocket: Creates a socket to accept incoming connections.
        ServerSocket socket = new ServerSocket(port);

        // setSoTimeout: Sets a timeout of 20 seconds for client connections
        socket.setSoTimeout(20000);


        while(true){
            System.out.println("Server is listening on port: "+port);
            Socket acceptedConnection = socket.accept();

            // Displays the remote client's address (e.g., IP and port) using getRemoteSocketAddress().
            System.out.println("Connected to "+acceptedConnection.getRemoteSocketAddress());

            // Output Stream (toClient):
            // Sends data to the client using PrintWriter with auto-flushing enabled (true).
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);

            // Input Stream (fromClient):
            // Reads data sent by the client using BufferedReader.
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

            // Respond to the Client:
            // Sends a simple message, "Hello World from the server", to the connected client.
            toClient.println("Hello World from the server");
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
