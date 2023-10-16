package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class ResidualCurrentProtectionParametersEntry extends MeasurementEntry {

    private String measurintPoint;
    private String circuitBreaker;
    private String type;
    private int in;
    private int ia;
    private int ta;
    private int trcd;
    private int ub;
    private int ui;

    public ResidualCurrentProtectionParametersEntry(int id, String symbol, Result result, String measurintPoint,
                                                    String circuitBreaker, String type, int in, int ia, int ta,
                                                    int trcd, int ub, int ui) {
        super(id, symbol, result);
        this.measurintPoint = measurintPoint;
        this.circuitBreaker = circuitBreaker;
        this.type = type;
        this.in = in;
        this.ia = ia;
        this.ta = ta;
        this.trcd = trcd;
        this.ub = ub;
        this.ui = ui;
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measurintPoint, this.circuitBreaker, this.type,
                this.in, this.ia, this.ta, this.trcd, this.ub, this.ui, super.getResult().getName());
    }
}