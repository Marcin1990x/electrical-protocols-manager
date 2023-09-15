package pl.koneckimarcin.electricalprotocolsmanager.measurement;

public abstract class MeasurementEntry {

    private int commonField1;
    private int commonField2;
    private int commonField3;

    private String commonField4;

    private Result result;

    public MeasurementEntry(int commonField1, int commonField2, int commonField3, String commonField4, Result result) {
        this.commonField1 = commonField1;
        this.commonField2 = commonField2;
        this.commonField3 = commonField3;
        this.commonField4 = commonField4;
        this.result = result;
    }

    public int getCommonField1() {
        return commonField1;
    }

    public void setCommonField1(int commonField1) {
        this.commonField1 = commonField1;
    }

    public int getCommonField2() {
        return commonField2;
    }

    public void setCommonField2(int commonField2) {
        this.commonField2 = commonField2;
    }

    public int getCommonField3() {
        return commonField3;
    }

    public void setCommonField3(int commonField3) {
        this.commonField3 = commonField3;
    }

    public String getCommonField4() {
        return commonField4;
    }

    public void setCommonField4(String commonField4) {
        this.commonField4 = commonField4;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MeasurementEntry{" +
                "commonField1=" + commonField1 +
                ", commonField2=" + commonField2 +
                ", commonField3=" + commonField3 +
                ", commonField4='" + commonField4 + '\'' +
                ", result=" + result +
                '}';
    }
}
