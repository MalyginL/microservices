package correctionservice.rest.model;

public class RequestModel {

   private String deviceName;
   private Integer[] channels;
   private String period;
   private String time;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer[] getChannels() {
        return channels;
    }

    public void setChannels(Integer[] channels) {
        this.channels = channels;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
