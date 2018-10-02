package settings.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Settings", schema = "Production", catalog = "")
public class SettingsModel {

    private int id;
    private String device;
    private String comport;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "comport", nullable = false)
    public String getComport() {
        return comport;
    }

    public void setComport(String comport) {
        this.comport = comport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsModel that = (SettingsModel) o;
        return Objects.equals(device, that.device) &&
                Objects.equals(comport, that.comport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(device, comport);
    }
}
