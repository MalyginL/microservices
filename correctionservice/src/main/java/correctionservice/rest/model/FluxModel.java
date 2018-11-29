package correctionservice.rest.model;

import java.math.BigDecimal;

public class FluxModel {

    private String key;
    private BigDecimal value;

    public FluxModel(String key, BigDecimal value) {
        this.key = key;
        this.value = value;
    }

    public FluxModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
