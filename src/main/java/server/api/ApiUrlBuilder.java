package server.api;

public class ApiUrlBuilder {

    private static final String BASE_URL  = "https://maps.googleapi.com/maps/api";
    private static final String timezone = "timezone";
    private static final String geocode = "geocode";
    private static final String jsonOutputFormat = "json";
    private static final String xmlOutputFormat = "xml";

    private String apiType;
    private String outputFormat;
    private String parameters;

    private String URL;

    // Certinax's Private API-keys
    private static final String geoApiKey = "AIzaSyDPs6roqPE6IYw5JPxOAMFpWD6sXyYBPqY";
    private static final String timeZoneApiKey = "AIzaSyDPs6roqPE6IYw5JPxOAMFpWD6sXyYBPqY";



    public ApiUrlBuilder(String apiType, String outputFormat, String parameters) {
        this.apiType = apiType;
        this.outputFormat = outputFormat;
        this.parameters = parameters;
        urlBuilder();
    }

    private void urlBuilder() {
        urlVerification();
        URL = BASE_URL + "/" + apiType + "/" + outputFormat + "?" + parameters
            + "&key=" + apiKey();

    }

    private void urlVerification() {
        apiTypeValidator();
        apiOutputFormatValidator();
        if(apiType.equals(geocode)) {
            geocodeParamsValidator();
        }
        if(apiType.equals(timezone)) {
            timezoneParamsValidator();
        }
    }

    private String apiKey() {
        if(apiType.equals(geocode)) {
            return geoApiKey;
        } else {
            return timeZoneApiKey;
        }
    }

    public String getURL() {
        return URL;
    }

    private void geocodeParamsValidator() throws RuntimeException {
        String[] params = parameters.split("=");
        if(!params[0].equals("address")) {
            throw new RuntimeException("Not valid parameters");
        }
    }

    private void timezoneParamsValidator() throws RuntimeException {
        String[] params = parameters.split("&");
        if(!params[0].startsWith("location")) {
            throw new RuntimeException("First parameter is not valid.");
        }
        if(!params[1].startsWith("timestamp")) {
            throw new RuntimeException("Second parameter is not valid.");
        }
    }

    private void apiTypeValidator() throws RuntimeException {
        if(!(apiType.equals(timezone) || apiType.equals(geocode))) {
            throw new RuntimeException("The requested API is not supported");
        }
    }

    private void apiOutputFormatValidator() throws RuntimeException {
        if(!(jsonOutputFormat.equals(outputFormat))) {
            throw new RuntimeException("The requested outformat " + outputFormat + " is not supported yet");
        }
    }
}
