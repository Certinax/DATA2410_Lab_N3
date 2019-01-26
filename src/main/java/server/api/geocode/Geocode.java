package server.api.geocode;

import java.util.StringJoiner;

public class Geocode {
    private String[] results;
    private String status;

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        for (String s: results) {
            sj.add(s);
        }
        return "Status: " + status + "\nLocation: " + sj.toString();
    }

    public String[] getResults() {
        return results;
    }

    public void setResults(String[] results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
