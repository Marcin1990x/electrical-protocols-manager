package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import java.util.List;

public abstract class MeasurementEntry {

    private int id;
    private String symbol;
    private String measuringPoint;
    private String cutout;
    private char type;
    private float in;
    private float ia;
    private float zs;
    private float za;
    private float ik;

    private Result result;

    public MeasurementEntry(int id, String symbol, String measuringPoint, String cutout, char type, float in,
                            float ia, float zs, float za, float ik, Result result) {
        this.id = id;
        this.symbol = symbol;
        this.measuringPoint = measuringPoint;
        this.cutout = cutout;
        this.type = type;
        this.in = in;
        this.ia = ia;
        this.zs = zs;
        this.za = za;
        this.ik = ik;
        this.result = result;
    }

    public List<Object> getEntryResultList() {

        return List.of(this.id, this.symbol, this.measuringPoint, this.cutout, this.type, this.in, this.ia, this.zs,
                this.za, this.ik, this.result.getName());
    }

    public Result getResult() {
        return result;
    }
}
