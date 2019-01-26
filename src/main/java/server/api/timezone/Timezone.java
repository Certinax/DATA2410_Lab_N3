package server.api.timezone;

public class Timezone {

    private String dstOffset;
    private String rawOffset;
    private String status;
    private String timeZoneId;
    private String timeZoneName;

    public Timezone(String dstOffset, String rawOffset, String status, String timeZoneId, String timeZoneName) {
        this.dstOffset = dstOffset;
        this.rawOffset = rawOffset;
        this.status = status;
        this.timeZoneId = timeZoneId;
        this.timeZoneName = timeZoneName;
    }

    @Override
    public String toString() {
        return "dstOffset: " + dstOffset + " rawOffset: " + rawOffset + " status: " + status +
                " timeZoneId: " + timeZoneId + " timeZoneName: " + timeZoneName;
    }

    public String getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(String dstOffset) {
        this.dstOffset = dstOffset;
    }

    public String getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(String rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }
}
