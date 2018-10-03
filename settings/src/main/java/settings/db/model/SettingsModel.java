package settings.db.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "settings", schema = "public", catalog = "")
public class SettingsModel {

    public SettingsModel(String device, String comport) {
        this.device = device;
        this.comport = comport;
    }

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
    @Column(name = "device", nullable = false, length = 10)
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Basic
    @Column(name = "comport", nullable = false, length = 10)
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
