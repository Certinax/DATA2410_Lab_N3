package server;

import server.api.ApiUrlBuilder;
import server.api.connection.Connection;
import server.api.timezone.Timezone;

import java.net.ConnectException;

public class DataHandler {

    private String URL;


    public DataHandler(String URL) {
        this.URL = URL;
    }


    public String[] geocodeConnection() {
        String[] latlong = new String[2];
        try {
            Connection geoConnect = new Connection("geocode");
            geoConnect.connector(URL);
            for (int i = 0; i < geoConnect.geocodeReader().getResults().length; i++) {
                latlong[i] = geoConnect.geocodeReader().getResults()[i];
            }
        } catch (Exception e) {
            System.out.println("Something's wrong with geocode API connection in DataHandler" + e.getMessage());
            return null;
        }
        return latlong;
    }


    public Timezone timezoneConnection(String[] latlong, String timestamp) {
        Timezone timezone = null;
        try {
            ApiUrlBuilder timezoneURL = new ApiUrlBuilder("timezone", "json", latlong, timestamp);
            Connection timezoneConnect = new Connection("timezone");
            timezoneConnect.connector(timezoneURL.getURL());
            timezone = timezoneConnect.timezoneReader();
        } catch (Exception e) {
            System.out.println("Something's wrong with timzeone API connection in DataHandler");
        }
        return timezone;
    }


    public String getData() {
        return "";
    }
}
