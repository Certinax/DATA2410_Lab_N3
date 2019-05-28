package server.api;

import java.sql.Timestamp;
import java.util.Date;
import java.util.StringJoiner;

public class ApiUrlBuilder {

    private static final String BASE_URL  = "https://maps.googleapis.com/maps/api";
    private static final String timezone = "timezone";
    private static final String geocode = "geocode";
    private static final String jsonOutputFormat = "json";
    private static final String xmlOutputFormat = "xml";

    private String apiType;
    private String outputFormat;
    private String[] parameters;
    private String apiParam;
    private String timestamp;

    private String URL;

    // Certinax's Private API-keys
    private static final String geoApiKey = "add_KEY";
    private static final String timeZoneApiKey = "add_KEY";



    public ApiUrlBuilder(String apiType, String outputFormat, String[] parameters) {
        this(apiType, outputFormat, parameters, "");
    }

    public ApiUrlBuilder(String apiType, String outputFormat, String[] parameters, String timestamp) {
        this.apiType = apiType;
        this.outputFormat = outputFormat;
        this.parameters = parameters;
        this.timestamp = timestamp;
        urlBuilder();
    }

    private void urlBuilder() {
        urlVerification();
        URL = BASE_URL + "/" + apiType + "/" + outputFormat + "?" + apiParam
            + "&key=" + apiKey();

    }

    private void urlVerification() {
        apiTypeValidator();
        apiOutputFormatValidator();
        if(apiType.equals(geocode)) {
            createGeocodeParam();
        }
        if(apiType.equals(timezone)) {
            createTimezoneParam();
        }
    }

    private void createGeocodeParam() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i]);
        }
        apiParam = "address=" + sb.toString();
    }

    private void createTimezoneParam() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length-1; i++) {
            sb.append(parameters[i]).append(",");
        }
        sb.append(parameters[parameters.length-1]);
        /*Date now = new Date();
        long time = now.getTime()/1000;
        String timestamp = String.valueOf(time);*/
        apiParam = "location=" + sb.toString() + "&timestamp="+timestamp;
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

    /*
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
    }*/

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
