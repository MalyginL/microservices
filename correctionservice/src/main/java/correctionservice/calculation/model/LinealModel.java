package correctionservice.calculation.model;

import java.math.BigDecimal;

public class LinealModel {

    public LinealModel() {
    }

    public LinealModel(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    private BigDecimal a;
    private BigDecimal b;

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }
}
