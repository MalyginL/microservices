package project.rest.model;

import project.hibernate.model.CalculateModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TimeModel {

    private int type;
    private double dzch;
    private BigDecimal drift;
    private BigDecimal sko;
    private List<CalculateModel> data = new ArrayList<>();



    public TimeModel() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getDzch() {
        return dzch;
    }

    public void setDzch(double dzch) {
        this.dzch = dzch;
    }

    public BigDecimal getDrift() {
        return drift;
    }

    public void setDrift(BigDecimal drift) {
        this.drift = drift;
    }

    public BigDecimal getSko() {
        return sko;
    }

    public void setSko(BigDecimal sko) {
        this.sko = sko;
    }

    public List<CalculateModel> getData() {
        return data;
    }

    public void setData(List<CalculateModel> data) {
        this.data = data;
    }
}
