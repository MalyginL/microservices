package settings.db.model;

public class RegisterModel {

    private String device;
    private short maxchannel;
    private String servicename;
    private String comport;

    public RegisterModel(String device, short maxchannel, String servicename, String comport) {
        this.device = device;
        this.maxchannel = maxchannel;
        this.servicename = servicename;
        this.comport = comport;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public short getMaxchannel() {
        return maxchannel;
    }

    public void setMaxchannel(short maxchannel) {
        this.maxchannel = maxchannel;
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
