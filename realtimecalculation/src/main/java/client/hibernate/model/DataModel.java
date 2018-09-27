package client.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Data", schema = "Etalon", catalog = "")
public class DataModel {
    private long id;
    private int device;
    private String rawData;
    private String time;

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
    @Column(name = "device", nullable = false)
    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    @Basic
    @Column(name = "rawData", nullable = true, length = 45)
    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    @Basic
    @Column(name = "time", nullable = false, length = 45)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataModel that = (DataModel) o;
        return id == that.id &&
                device == that.device &&
                Objects.equals(rawData, that.rawData) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, device, rawData, time);
    }

}
