package project.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "devicestatus", schema = "public", catalog = "")
public class DeviceModel {

    private long id;
    private String device;
    private short channel;
    private short status;
    private String servicename;
    private String comport;

    public DeviceModel(){

    }

    public DeviceModel(String device, short channel, short status, String servicename, String comport) {
        this.device = device;
        this.channel = channel;
        this.status = status;
        this.servicename = servicename;
        this.comport = comport;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "device")
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    @Basic
    @Column(name = "channel")
    public short getChannel() {
        return channel;
    }

    public void setChannel(short channel) {
        this.channel = channel;
    }
    @Basic
    @Column(name = "status")
    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
    @Basic
    @Column(name = "servicename")
    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    @Basic
    @Column(name = "comport")
    public String getComport() {
        return comport;
    }

    public void setComport(String comport) {
        this.comport = comport;
    }



}
