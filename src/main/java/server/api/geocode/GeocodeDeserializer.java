package server.api.geocode;

import com.google.gson.*;

import java.lang.reflect.Type;


public class GeocodeDeserializer implements JsonDeserializer<Geocode> {

    @Override
    public Geocode deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        // This is the JSON-object that is retrieved from google API request
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray jsonResultsArray = jsonObject.get("results").getAsJsonArray();
        final JsonObject jsonResultsObject = jsonResultsArray.get(0).getAsJsonObject();
        final JsonObject jsonGeometryObject = jsonResultsObject.get("geometry").getAsJsonObject();
        final JsonObject jsonLocationObject = jsonGeometryObject.get("location").getAsJsonObject();

        final String[] results = new String[2];
        final String lat = jsonLocationObject.get("lat").getAsString();
        final String lng = jsonLocationObject.get("lng").getAsString();
        results[0] = "lat " + lat;
        results[1] = "lng " + lng;

        final String status = jsonObject.get("status").getAsString();

        final Geocode geocode = new Geocode();
        geocode.setStatus(status);
        geocode.setResults(results);
        return geocode;
    }
}
