package project.db.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "devicereg", schema = "production", catalog = "")
public class DeviceModel {

   private int deviceid;
   private String devicename;
   private String comport;
   private short channel;

    public DeviceModel() {
    }

    public DeviceModel( String devicename, String comport, short channel) {
        this.devicename = devicename;
        this.comport = comport;
        this.channel = channel;
    }


    @Id
    @Primary
    @Column(name = "deviceid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    @Basic
    @Column(name = "devicename", nullable = false)
    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }
    @Basic
    @Column(name = "comport", nullable = false)
    public String getComport() {
        return comport;
    }

    public void setComport(String comport) {
        this.comport = comport;
    }
    @Basic
    @Column(name = "channel", nullable = false)
    public short getChannel() {
        return channel;
    }

    public void setChannel(short channel) {
        this.channel = channel;
    }
}
