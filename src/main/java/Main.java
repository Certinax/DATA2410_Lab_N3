import server.DataHandler;
import server.api.ApiUrlBuilder;
import server.api.QueryParams;
import server.api.connection.Connection;

public class Main {

    public static void main(String[] args) throws Exception{
        String geocode = "geocode";
        String timezone = "timezone";
        String json = "json";
        String xml = "xml";
        //String params = "location=60.47202399999999,8.468945999999999&timestamp=1331161200";
        String[] params = new String[2];
        params[0] = "60.47202399999999";
        params[1] = "8.468945999999999";
        ApiUrlBuilder url = new ApiUrlBuilder(timezone, json, params);
        System.out.println(url.getURL());

        String input = "New York";
        QueryParams paramss = new QueryParams(input);
        System.out.println(paramss.queryFormatter());


        DataHandler dh = new DataHandler("New York");
        System.out.println(dh.getData());
        Connection loc = new Connection("geocode");
        loc.connector(dh.getData());
        System.out.println(loc.geoCodeReader());
    }
}
