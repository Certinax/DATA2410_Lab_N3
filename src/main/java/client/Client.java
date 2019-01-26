package client;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 5555;

        // Checking if its only one argument
        if (args.length > 0) {
            hostName = args[0];
            if (args.length > 1) {
                portNumber = Integer.parseInt(args[1]);
                if (args.length > 2) {
                    System.out.println("Too many parameters");
                    System.exit(1);
                }
            }
        }

        System.out.println("I am your TCP client!");

        try
                (
                        Socket clientSocket = new Socket(hostName, portNumber);

                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                        BufferedReader keys = new BufferedReader(new InputStreamReader(System.in))
                )
        {
            String userIn;

            while((userIn = keys.readLine()) != null && !userIn.isEmpty()) {
                out.println(userIn);

                String recieved = in.readLine();

                System.out.println("Response: " + recieved);
                //System.out.println("Response: " + clientSocket.getLocalAddress().getHostAddress() + ": Localport: " + clientSocket.getLocalPort());

            }

        } catch (UnknownHostException e) {
            System.out.println("The host " + hostName + " is unknown");
        } catch (IOException e) {
            System.out.println("Error in IO");
        }
    }
}