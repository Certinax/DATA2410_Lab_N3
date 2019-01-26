package server.api.connection;

import server.api.ApiUrlBuilder;

import java.net.URL;

public class Connection {

    private String input;
    private String connectionType;
    private String url;

    public Connection(String input, String connectionType) {
        this.input = input;
        this.connectionType = connectionType;
        checkConnectionType();
    }

    private void checkConnectionType() {
        if(connectionType.equals("geocode")) {
        }
    }

    public void connect() {
        URL url = new URL
    }
}
