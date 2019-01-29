package server.api.connection;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.api.geocode.Geocode;
import server.api.geocode.GeocodeDeserializer;
import server.api.timezone.Timezone;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;

public class Connection {

    private String connectionType;
    private URL url;
    private HttpURLConnection connection;

    public Connection(String connectionType) {
        this.connectionType = connectionType;
    }

    public void connector(String apiUrl) throws Exception {
        url = new URL(apiUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responsecode = connection.getResponseCode();
        if(responsecode != 200) throw new RuntimeException("HttpResponseCode: " + responsecode);

        if(connectionType.equals("geocode")) {
            geocodeReader();
        }

        if(connectionType.equals("timezone")) {
            timezoneReader();
        }
    }

    public Geocode geocodeReader() throws Exception {
        Geocode location;
        try(Reader reader = new InputStreamReader(url.openStream())) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Geocode.class, new GeocodeDeserializer());
            Gson gson = gsonBuilder.create();
            location = gson.fromJson(reader, Geocode.class);

        }
        return location;
    }

    public Timezone timezoneReader() throws Exception {
        Timezone timezone;
        try(Reader reader = new InputStreamReader(url.openStream())) {
            Gson gson = new GsonBuilder().create();
            timezone = gson.fromJson(reader, Timezone.class);
            if(!timezone.getStatus().equals("OK")) throw new NoSuchElementException("Ojektet finnes ikke");
        }
        return timezone;
    }



}
