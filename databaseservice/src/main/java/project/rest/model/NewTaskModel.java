package project.rest.model;

public class NewTaskModel {

    public NewTaskModel() {
    }

    private String device;
    private int channel;
    private int period;


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
