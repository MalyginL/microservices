package project.db.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rawdata", schema = "public", catalog = "")
public class SendModel {

    SendModel(){}

    public SendModel(String device, short channel, BigDecimal rawdata, int rawtime) {
        this.device = device;
        this.channel = channel;
        this.rawdata = rawdata;
        this.rawtime = rawtime;
    }

    private long id;
    private String device;
    private short channel;
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
    @Column(name = "device", nullable = false, length = 10)
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Basic
    @Column(name = "channel", nullable = false)
    public short getChannel() {
        return channel;
    }

    public void setChannel(short channel) {
        this.channel = channel;
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
