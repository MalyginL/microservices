package client.rest.model;

import java.math.BigDecimal;

public class RequestAnswer {
    private int key;
    private BigDecimal value;

    public RequestAnswer(int key, BigDecimal value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BigDecimal getValue() {
      return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
