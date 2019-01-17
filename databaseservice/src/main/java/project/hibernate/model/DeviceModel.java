package project.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "devicestatus", schema = "production", catalog = "")
//@SecondaryTables({
//        @SecondaryTable(name="devicereg",schema = "production", pkJoinColumns={
//                @PrimaryKeyJoinColumn(name="deviceid", referencedColumnName="deviceid") })
//})
public class DeviceModel {

    private long id;
    private int deviceid;
    private boolean status;
    private int starttime;

    public DeviceModel(){

    }

    public DeviceModel(long id, int deviceid, boolean status, int starttime) {
        this.id = id;
        this.deviceid = deviceid;
        this.status = status;
        this.starttime = starttime;
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
    @Column(name = "deviceid")
    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }
    @Basic
    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Basic
    @Column(name = "starttime")
    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }
}
