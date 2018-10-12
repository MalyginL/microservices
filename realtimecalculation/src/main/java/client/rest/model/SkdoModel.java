package client.rest.model;

import java.math.BigDecimal;

public class SkdoModel {

    public SkdoModel(BigDecimal summ, BigDecimal temp) {
        this.summ = summ;
        this.temp = temp;

    }


    private BigDecimal summ;
    private BigDecimal temp;


    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }
}
