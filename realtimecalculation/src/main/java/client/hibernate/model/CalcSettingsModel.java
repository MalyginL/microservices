package client.hibernate.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Calc_Settings", schema = "Etalon", catalog = "")
public class CalcSettingsModel {

    private long id;
    private String calc_time;
    private String calc_device;
    private String calc_channel;

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
    @Column(name = "calc_time")
    public String getCalc_time() {
        return calc_time;
    }

    public void setCalc_time(String calc_time) {
        this.calc_time = calc_time;
    }
    @Basic
    @Column(name = "calc_device")
    public String getCalc_device() {
        return calc_device;
    }

    public void setCalc_device(String calc_device) {
        this.calc_device = calc_device;
    }
    @Basic
    @Column(name = "calc_channel")
    public String getCalc_channel() {
        return calc_channel;
    }

    public void setCalc_channel(String calc_channel) {
        this.calc_channel = calc_channel;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalcSettingsModel that = (CalcSettingsModel) o;
        return Objects.equals(calc_time, that.calc_time) &&
                Objects.equals(calc_device, that.calc_device) &&
                Objects.equals(calc_channel, that.calc_channel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(calc_time, calc_device, calc_channel);
    }
}


