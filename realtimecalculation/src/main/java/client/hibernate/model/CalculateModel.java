package client.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Calculate", schema = "Etalon", catalog = "")
public class CalculateModel {



    private long id;
    private String time;
    private String device;
    private int channel;
    private String phase_diff;
    private String rel_freq_diff;
    private String curr_var_rel_freq_diff;


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
    @Column(name = "time", nullable = false)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "device", nullable = false)
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Basic
    @Column(name = "phase_diff")
    public String getPhase_diff() {
        return phase_diff;
    }

    public void setPhase_diff(String phase_diff) {
        this.phase_diff = phase_diff;
    }

    @Basic
    @Column(name = "rel_freq_diff")
    public String getRel_freq_diff() {
        return rel_freq_diff;
    }

    public void setRel_freq_diff(String rel_freq_diff) {
        this.rel_freq_diff = rel_freq_diff;
    }
    @Basic
    @Column(name = "curr_var_rel_freq_diff")
    public String getCurr_var_rel_freq_diff() {
        return curr_var_rel_freq_diff;
    }

    public void setCurr_var_rel_freq_diff(String curr_var_rel_freq_diff) {
        this.curr_var_rel_freq_diff = curr_var_rel_freq_diff;
    }
    @Basic
    @Column(name = "channel")
    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateModel that = (CalculateModel) o;
        return id == that.getId() &&
                channel == that.getChannel() &&
                Objects.equals(device, that.getDevice()) &&
                Objects.equals(time, that.getTime()) &&
                Objects.equals(phase_diff, that.getPhase_diff()) &&
                Objects.equals(rel_freq_diff, that.getRel_freq_diff()) &&
                Objects.equals(curr_var_rel_freq_diff, that.getCurr_var_rel_freq_diff());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, device, channel, time,phase_diff,rel_freq_diff,curr_var_rel_freq_diff);
    }




}
