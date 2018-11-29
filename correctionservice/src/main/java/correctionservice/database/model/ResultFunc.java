package correctionservice.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

public class ResultFunc {

    public ResultFunc(Integer result_time, BigDecimal avg_result) {
        this.result_time = result_time;
        this.avg_result = avg_result;
    }

    public ResultFunc() {
    }

    private Integer result_time;
    private BigDecimal avg_result;
    @Basic
    @Column(name = "result_time")
    public Integer getResult_time() {
        return result_time;
    }

    public void setResult_time(Integer result_time) {
        this.result_time = result_time;
    }
    @Basic
    @Column(name = "avg_result")
    public BigDecimal getAvg_result() {
        return avg_result;
    }

    public void setAvg_result(BigDecimal avg_result) {
        this.avg_result = avg_result;
    }


}
