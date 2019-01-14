package project.hibernate.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "externalcomparsion", schema = "public", catalog = "")
public class ExternalComparsionModel {


    public ExternalComparsionModel() {
    }

    public ExternalComparsionModel(String ref, String frc, BigDecimal value, int date) {
        this.ref = ref;
        this.frc = frc;
        this.value = value;
        this.date=date;
    }

    private long id;
    private int date;
    private String ref;
    private String frc;
    private BigDecimal value;

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
    @Column(name = "ref")
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    @Basic
    @Column(name = "frc")
    public String getFrc() {
        return frc;
    }

    public void setFrc(String frc) {
        this.frc = frc;
    }
    @Basic
    @Column(name = "value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    @Basic
    @Column(name = "date")
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
