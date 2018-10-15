package project.hibernate.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "calculate", schema = "public", catalog = "")
public class CalculateModel {



    private long id;
    private String device;
    private short channel;
    private int type;
    private int calc_time;
    private BigDecimal phase_diff;
    private BigDecimal rel_freq_diff;
    private BigDecimal curr_var_rel_freq_diff;


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
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Basic
    @Column(name = "calc_time")
    public int getCalc_time() {
        return calc_time;
    }

    public void setCalc_time(int calc_time) {
        this.calc_time = calc_time;
    }
    @Basic
    @Column(name = "phase_diff")
    public BigDecimal getPhase_diff() {
        return phase_diff;
    }

    public void setPhase_diff(BigDecimal phase_diff) {
        this.phase_diff = phase_diff;
    }
    @Basic
    @Column(name = "rel_freq_diff")
    public BigDecimal getRel_freq_diff() {
        return rel_freq_diff;
    }

    public void setRel_freq_diff(BigDecimal rel_freq_diff) {
        this.rel_freq_diff = rel_freq_diff;
    }
    @Basic
    @Column(name = "cur_var_rel_freq_diff")
    public BigDecimal getCurr_var_rel_freq_diff() {
        return curr_var_rel_freq_diff;
    }

    public void setCurr_var_rel_freq_diff(BigDecimal curr_var_rel_freq_diff) {
        this.curr_var_rel_freq_diff = curr_var_rel_freq_diff;
    }
}
