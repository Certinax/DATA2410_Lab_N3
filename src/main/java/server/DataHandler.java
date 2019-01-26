package server;

import server.api.ApiUrlBuilder;
import server.api.QueryParams;

public class DataHandler {

    private String input;
    private String params;
    private String url;

    public DataHandler(String input) {
        this.input = input;
    }

    public String getData() {
        params = createParams(input);
        url = createGeocodeUrl(params);
        return url;
    }

    private String createParams(String client_input) {
        QueryParams client_params = new QueryParams(client_input);
        return client_params.queryFormatter();
    }

    private String createGeocodeUrl(String apiParams) {
        String[] paramQuery = new String[1];
        paramQuery[0] = apiParams;
        ApiUrlBuilder url = new ApiUrlBuilder("geocode", "json", paramQuery);
        return url.getURL();
    }
}
