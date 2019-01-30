package server;

import server.api.timezone.Timezone;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;
    private InetAddress clientAddr;
    private int serverPort;
    private int clientPort;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        clientAddr = socket.getInetAddress();
        clientPort = socket.getPort();
        serverPort = socket.getLocalPort();
    }

    @Override
    public void run() {
        try
        (
                // Data back to client
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // In data from client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String input;


            while ((input = in.readLine()) != null) {
                System.out.println("Client [" + clientAddr.getHostAddress() +  ":" + clientPort +"] > " + input);


                System.out.println("Input from client: " + input);

                // Sends input to getTime function

                String outText = getTime(input) ;


                // Sends outText to client
                out.println(outText);

                System.out.println("Retur: " + outText);

                System.out.println("I (Server) [" + socket.getLocalAddress().getHostAddress() + ":" + serverPort +"] > " + outText);

            }
        } catch (IOException e) {
            // skriv ut feilmelding
        }
    }


    // Send time in a compact format
    public static String getTime(String input) {
        String response = "";
        InputHandler ih = new InputHandler(input);
        String connectionUrl = ih.getData();
        DataHandler dh = new DataHandler(connectionUrl);
        String[] latlong = dh.geocodeConnection();

        if(latlong == null) {
            return "We cannot find the location you are searching for.";
        }

        Timezone timezone = dh.timezoneConnection(latlong, ih.getClient_timestamp());
        TimezoneHandler th = new TimezoneHandler(timezone, ih.getClient_timestamp());
        if(th.getTime() != null) {
            response = th.getTime();
        }
        return response;
    }
}
