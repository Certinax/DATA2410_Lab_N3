import server.InputHandler;
import server.api.ApiUrlBuilder;
import server.api.QueryParams;
import server.api.connection.Connection;

public class Main {

    public static void main(String[] args) throws Exception{
        /*String geocode = "geocode";
        String timezone = "timezone";
        String json = "json";
        String xml = "xml";
        //String params = "location=60.47202399999999,8.468945999999999&timestamp=1331161200";
        String[] params = new String[2];
        params[0] = "60.47202399999999";
        params[1] = "8.468945999999999";
        ApiUrlBuilder url = new ApiUrlBuilder(timezone, json, params);
        System.out.println(url.getURL());*/

        String input = "New York";
        QueryParams paramss = new QueryParams(input);
        //System.out.println(paramss.queryFormatter());

        InputHandler dh = new InputHandler("New York");
        //System.out.println(dh.getData());
        String connectionUrl = dh.getData();
        Connection geoConnect = new Connection("geocode");
        geoConnect.connector(connectionUrl);
        //System.out.println(geoConnect.geocodeReader());


        String[] latlong = new String[2];
        for (int i = 0; i < geoConnect.geocodeReader().getResults().length; i++) {
            System.out.println(geoConnect.geocodeReader().getResults()[i]);
            latlong[i] = geoConnect.geocodeReader().getResults()[i];
        }

        ApiUrlBuilder timezoneURL = new ApiUrlBuilder("timezone", "json", latlong);
        Connection timezoneConnect = new Connection("timezone");
        //System.out.println(timezoneURL.getURL());
        timezoneConnect.connector(timezoneURL.getURL());
        System.out.println(timezoneConnect.timezoneReader().getTimeZoneId());
    }
}
