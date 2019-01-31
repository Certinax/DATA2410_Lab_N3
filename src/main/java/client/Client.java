package client;

import java.net.*;
import java.io.*;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        // String hostName = "127.0.0.1";
        // Håkon IP
        String hostName = "10.253.11.146";
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

                Date now = new Date();
                long time = now.getTime()/1000;
                String timestamp = String.valueOf(time);

                out.println(userIn + "&timestamp="+timestamp);

                String recieved = in.readLine();

                if(recieved.matches("^[1-9][0-9]{9}$")){
                    long targetTime = Long.valueOf(recieved);
                    Date targetDate = new Date(targetTime*1000);
                    System.out.println("Response: " + "\nLocal time in " + userIn + ":" + targetDate);
                } else {
                    System.out.println("Response: " + recieved);
                }

            }

        } catch (UnknownHostException e) {
            System.out.println("The host " + hostName + " is unknown");
        } catch (IOException e) {
            System.out.println("Error in IO");
        }
    }
}