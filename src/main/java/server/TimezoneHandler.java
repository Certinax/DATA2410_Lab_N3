package server;

import server.api.timezone.Timezone;

import java.util.Date;

public class TimezoneHandler {

    private Timezone timezone;
    private String client_timestamp;

    public TimezoneHandler(Timezone timezone, String client_timestamp) {
        this.timezone = timezone;
        this.client_timestamp = client_timestamp;
    }


    public String getTimezone() {
        int dstOffset = Integer.valueOf(timezone.getDstOffset());
        int rawOffset = Integer.valueOf(timezone.getRawOffset());
        int timezone = (dstOffset + rawOffset)/3600;
        return String.valueOf(timezone);
    }

    public String getTime() {
        int serverOffset = -3600;
        int dstOffset = Integer.valueOf(timezone.getDstOffset());
        int rawOffset = Integer.valueOf(timezone.getRawOffset());
        int timestamp = Integer.valueOf(client_timestamp);
        int targetTime = timestamp + rawOffset + dstOffset + serverOffset;

        return String.valueOf(targetTime);
    }
}
