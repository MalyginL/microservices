package correctionservice.rest.model;

import java.math.BigDecimal;
import java.util.List;

public class AnswerModel {

    public AnswerModel(int channel, String command_time, String measurment_period, String measurment_time, String formula, BigDecimal rfd, List<AngTable> graphData) {
        this.channel = channel;
        this.command_time = command_time;
        this.measurment_period = measurment_period;
        this.measurment_time = measurment_time;
        this.formula = formula;
        this.rfd = rfd;
        this.graphData = graphData;
    }

    public AnswerModel() {
    }

    private int channel;
    private String command_time;
    private String measurment_period;
    private String measurment_time;
    private String formula;
    private BigDecimal rfd;
    private List<AngTable> graphData;


    public static class AngTable {
       private String date;
       private BigDecimal real;
       private BigDecimal calculated;

        public AngTable(String date, BigDecimal real, BigDecimal calculated) {
            this.date = date;
            this.real = real;
            this.calculated = calculated;
        }
        public AngTable() {
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public BigDecimal getReal() {
            return real;
        }

        public void setReal(BigDecimal real) {
            this.real = real;
        }

        public BigDecimal getCalculated() {
            return calculated;
        }

        public void setCalculated(BigDecimal calculated) {
            this.calculated = calculated;
        }
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getCommand_time() {
        return command_time;
    }

    public void setCommand_time(String command_time) {
        this.command_time = command_time;
    }

    public String getMeasurment_period() {
        return measurment_period;
    }

    public void setMeasurment_period(String measurment_period) {
        this.measurment_period = measurment_period;
    }

    public String getMeasurment_time() {
        return measurment_time;
    }

    public void setMeasurment_time(String measurment_time) {
        this.measurment_time = measurment_time;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public BigDecimal getRfd() {
        return rfd;
    }

    public void setRfd(BigDecimal rfd) {
        this.rfd = rfd;
    }

    public List<AngTable> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<AngTable> graphData) {
        this.graphData = graphData;
    }
}
