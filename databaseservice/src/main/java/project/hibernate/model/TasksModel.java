package project.hibernate.model;


import javax.persistence.*;

@Entity
@Table(name = "tasks", schema = "public", catalog = "")
public class TasksModel {
    private long id;
    private String device;
    private short channel;
    private int period;
    private boolean status;
    private int starttime;


    public TasksModel(String device, short channel, int period, boolean status, int starttime) {
        this.device = device;
        this.channel = channel;
        this.period = period;
        this.status = status;
        this.starttime = starttime;

    }

    public TasksModel() {
    }

    @Id
    @Column(name = "id")
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
    @Column(name = "period")
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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
