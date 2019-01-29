package server;

import server.api.ApiUrlBuilder;
import server.api.QueryParams;

public class InputHandler {

    private String input;
    private String client_location;
    private String client_timestamp;

    public InputHandler(String input) {
        this.input = input;
        String[] inputConversion = input.split("&timestamp=");
        client_location = inputConversion[0];
        client_timestamp = inputConversion[1];
    }

    public String getData() {
        String params = createParams(input);
        return createGeocodeUrl(params);
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getClient_location() {
        return client_location;
    }

    public void setClient_location(String client_location) {
        this.client_location = client_location;
    }

    public String getClient_timestamp() {
        return client_timestamp;
    }

    public void setClient_timestamp(String client_timestamp) {
        this.client_timestamp = client_timestamp;
    }
}
