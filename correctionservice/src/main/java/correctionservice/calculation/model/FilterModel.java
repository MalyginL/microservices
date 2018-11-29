package correctionservice.calculation.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class FilterModel {

    public FilterModel() {
        normal = new HashMap<>();
        fake= new HashMap<>();
    }

    public FilterModel(HashMap<BigDecimal, BigDecimal> normal, HashMap<BigDecimal, BigDecimal> fake) {
        this.normal = normal;
        this.fake = fake;
    }

    private HashMap<BigDecimal,BigDecimal> normal;
    private HashMap<BigDecimal,BigDecimal> fake;

    public HashMap<BigDecimal, BigDecimal> getNormal() {
        return normal;
    }

    public void setNormal(HashMap<BigDecimal, BigDecimal> normal) {
        this.normal = normal;
    }

    public HashMap<BigDecimal, BigDecimal> getFake() {
        return fake;
    }

    public void setFake(HashMap<BigDecimal, BigDecimal> fake) {
        this.fake = fake;
    }
}
