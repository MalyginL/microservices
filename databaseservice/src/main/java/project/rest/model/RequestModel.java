package project.rest.model;

public class RequestModel {
    private String device;
    private short channel;
    private int startTime;
    private int endTime;

    public String getDevice() {
        return device;
        }

public void setDevice(String device) {
        this.device = device;
        }

public short getChannel() {
        return channel;
        }

public void setChannel(short channel) {
        this.channel = channel;
        }

public int getStartTime() {
        return startTime;
        }

public void setStartTime(int startTime) {
        this.startTime = startTime;
        }

public int getEndTime() {
        return endTime;
        }

public void setEndTime(int endTime) {
        this.endTime = endTime;
        }
        }
