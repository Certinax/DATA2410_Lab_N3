package server;

import server.api.connection.Connection;
import server.api.timezone.Timezone;
import worldtime.WorldTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // Default port number
        int port = 5555;


        if (args.length > 0) {
            if (args.length == 1)
                port = Integer.parseInt(args[0]);
            else {
                System.err.println("ERROR: Feil i input-parameter");
                System.exit(1);
            }
        }

        System.out.println("Server up and running - TCP");

        try (
                // Create the server socket.
                ServerSocket serverSocket = new ServerSocket(port);


        ) {
            while (true) {
                // create and start a new ClientServer thread for each connected client
                ClientHandler client = new ClientHandler(serverSocket.accept());
                client.start();
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}