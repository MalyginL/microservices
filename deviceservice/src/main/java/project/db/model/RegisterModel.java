package project.db.model;

public class RegisterModel {

    private String devicename;
    private String maxchannels;
    private String servicename;
    private String comport;

    public RegisterModel(String device, String maxchannel, String servicename, String comport) {
        this.devicename = device;
        this.maxchannels = maxchannel;
        this.servicename = servicename;
        this.comport = comport;
    }

    public RegisterModel(){}

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getMaxchannels() {
        return maxchannels;
    }

    public void setMaxchannels(String maxchannels) {
        this.maxchannels = maxchannels;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getComport() {
        return comport;
    }

    public void setComport(String comport) {
        this.comport = comport;
    }
}
