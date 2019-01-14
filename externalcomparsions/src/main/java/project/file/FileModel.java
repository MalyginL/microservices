package project.file;

import java.math.BigDecimal;
import java.util.Objects;

public class FileModel {

    public FileModel(int sat, String sttime, BigDecimal refsv,String frc) {
        this.frc= frc;
        this.sat = sat;
        this.sttime = sttime;
        this.refsv = refsv;
    }

    private int sat;
    private String sttime;
    private String frc;
    private BigDecimal refsv;

    public String getFrc() {
        return frc;
    }

    public void setFrc(String frc) {
        this.frc = frc;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public String getSttime() {
        return sttime;
    }

    public void setSttime(String sttime) {
        this.sttime = sttime;
    }

    public BigDecimal getRefsv() {
        return refsv;
    }

    public void setRefsv(BigDecimal refsv) {
        this.refsv = refsv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileModel fileModel = (FileModel) o;
        return sat == fileModel.sat &&
                Objects.equals(sttime, fileModel.sttime) &&
                Objects.equals(frc, fileModel.frc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sat, sttime, frc);
    }
}
