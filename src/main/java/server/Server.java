package server;

import server.api.ApiUrlBuilder;
import server.api.QueryParams;
import worldtime.WorldTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public static void main(String[] args) throws IOException {

        // Default port number
        int port = 5555;


        if (args.length > 0) {
            if (args.length == 1)
                port = Integer.parseInt(args[0]);
            else
            {
                System.err.println("ERROR: Feil i input-parameter");
                System.exit(1);
            }
        }

        System.out.println("Server up and running - TCP");

        try(
                // Create the server socket.
                ServerSocket serverSocket = new ServerSocket(port);

                // Start listening on the socket.
                Socket socket = serverSocket.accept();

                // Data back to client
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // In data from client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ) {

            // Get clients address;
            InetAddress clientIP = socket.getInetAddress();
            // Get clients port
            int clientPort = socket.getPort();
            // Input from client

            String input;


            while ((input = in.readLine()) != null) {

                System.out.println("Input from client: " +input);

                // Sends input to getTime function

                String outText = getTime(input) ;


                // Sends outText to client
                out.println(outText);

                System.out.println("Retur: " + outText);

            }


        } catch (IOException e)
        {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }

    }

    // Send time in a compact format
    public static String getTime(String input) {
        DataHandler dh = new DataHandler(input);
        dh.getData();
        String info;
        WorldTime time = new WorldTime();
        info = time.showTime(input);
        return info;
    }


}