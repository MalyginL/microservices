package project.rest.model;

import java.math.BigDecimal;

public class AnswerModel {

    public AnswerModel() {
    }

    public AnswerModel(String FRC, String data, BigDecimal value) {
        this.FRC = FRC;
        this.data = data;
        this.value = value;
    }

    private String FRC;
    private String data;
    private BigDecimal value;

    public String getFRC() {
        return FRC;
    }

    public void setFRC(String FRC) {
        this.FRC = FRC;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
