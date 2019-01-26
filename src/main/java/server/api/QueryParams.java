package server.api;

public class QueryParams {

    private String params;

    public QueryParams (String params) {
        this.params = params;
    }

    public String queryFormatter() {
        return params.replaceAll("\\s","");
    }
}
