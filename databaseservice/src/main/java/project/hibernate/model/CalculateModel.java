package project.hibernate.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "calculate", schema = "production", catalog = "")
public class CalculateModel {



    private long id;
    private int deviceid;
    private int calc_time;
    private BigDecimal phase_diff;
    private BigDecimal rel_freq_diff;
    private BigDecimal cur_var_rel_freq_diff;
    private int time_type;


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
    @Column(name = "deviceid")
    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    @Basic
    @Column(name = "time_type")
    public int getTime_type() {
        return time_type;
    }

    public void setTime_type(int time_type) {
        this.time_type = time_type;
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
    public BigDecimal getCur_var_rel_freq_diff() {
        return cur_var_rel_freq_diff;
    }

    public void setCur_var_rel_freq_diff(BigDecimal cur_var_rel_freq_diff) {
        this.cur_var_rel_freq_diff = cur_var_rel_freq_diff;
    }
}
