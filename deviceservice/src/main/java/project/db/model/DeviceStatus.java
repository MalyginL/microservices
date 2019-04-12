package project.db.model;


import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "devicestatus", schema = "production", catalog = "")
public class DeviceStatus {

    public DeviceStatus() {
    }

    public DeviceStatus(int deviceid, boolean status, int starttime) {
        this.deviceid = deviceid;
        this.status = status;
        this.starttime = starttime;
    }

    public DeviceStatus(int deviceid, boolean status) {
        this.deviceid = deviceid;
        this.status = status;
    }

    private int id;
    private int deviceid;
    private boolean status;
    private int starttime;

    @Id
    @Primary
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "deviceid", nullable = false)
    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Basic
    @Column(name = "starttime", nullable = false)
    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }
}
