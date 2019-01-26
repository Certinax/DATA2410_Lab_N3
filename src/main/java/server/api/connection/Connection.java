package server.api.connection;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.api.geocode.Geocode;
import server.api.geocode.GeocodeDeserializer;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            geoCodeReader();
        }
    }

    public String geoCodeReader() throws  Exception {
        String s;
        try(Reader reader = new InputStreamReader(url.openStream())) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Geocode.class, new GeocodeDeserializer());
            Gson gson = gsonBuilder.create();

            Geocode location = gson.fromJson(reader, Geocode.class);
            s = location.toString();

        }
        return s;
    }




}
