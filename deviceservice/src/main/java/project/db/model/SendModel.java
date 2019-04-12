package project.db.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rawdata", schema = "production", catalog = "")
public class SendModel {

    SendModel(){}

    public SendModel(int deviceid, BigDecimal rawdata, int rawtime) {
        this.deviceid = deviceid;
        this.rawdata = rawdata;
        this.rawtime = rawtime;
    }

    private long id;
    private int deviceid;
    private BigDecimal rawdata;
    private int rawtime;

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
    @Column(name = "deviceid", nullable = false, length = 10)
    public int getDevice() {
        return deviceid;
    }

    public void setDevice(int deviceid) {
        this.deviceid = deviceid;
    }

    @Basic
    @Column(name = "rawdata", nullable = false, scale=18)
    public BigDecimal getRawdata() {
        return rawdata;
    }

    public void setRawdata(BigDecimal rawdata) {
        this.rawdata = rawdata;
    }
    @Basic
    @Column(name = "rawtime", nullable = false)
    public int getRawtime() {
        return rawtime;
    }

    public void setRawtime(int rawtime) {
        this.rawtime = rawtime;
    }
}
