import server.api.ApiUrlBuilder;
import server.api.QueryParams;

public class Main {

    public static void main(String[] args) {
        String geocode = "geocode";
        String timezone = "timezone";
        String json = "json";
        String xml = "xml";
        String params = "location=60.47202399999999,8.468945999999999&timestamp=1331161200";
        ApiUrlBuilder url = new ApiUrlBuilder(timezone, json, params);
        System.out.println(url.getURL());

        String input = "New York";
        QueryParams paramss = new QueryParams(input);
        System.out.println(paramss.queryFormatter());

    }
}
