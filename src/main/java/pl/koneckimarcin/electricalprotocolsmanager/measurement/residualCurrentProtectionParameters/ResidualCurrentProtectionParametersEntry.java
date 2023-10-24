package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class ResidualCurrentProtectionParametersEntry extends MeasurementEntry {

    private String measuringPoint;
    private String circuitBreaker;
    private String type;
    private int in;
    private int ia;
    private int ta;
    private int trcd;
    private int ub;
    private int ui;

    public ResidualCurrentProtectionParametersEntry(int id, String symbol, String measuringPoint,
                                                    String circuitBreaker, String type, int in, int ia, int ta,
                                                    int trcd, int ub, int ui) {
        super(id, symbol);
        this.measuringPoint = measuringPoint;
        this.circuitBreaker = circuitBreaker;
        this.type = type;
        this.in = in;
        this.ia = ia;
        this.ta = ta;
        this.trcd = trcd;
        this.ub = ub;
        this.ui = ui;
        setResult(this.type);
    }

    private void setResult(String type) {

        Result result = Result.NONE;

        switch (type) {
            case "[AC]" -> {
                if (this.ub <= this.ui && this.trcd < this.ta && ((this.ia >= this.in / 2) && (this.ia <= this.in))) {
                    result = Result.POSITIVE;
                } else result = Result.NEGATIVE;
            }
            case "[A]" -> {
                if (this.ub <= this.ui && this.trcd < this.ta &&
                        ((this.ia >= this.in * 0.35) && (this.ia <= this.in * 1.4))) {
                    result = Result.POSITIVE;
                } else result = Result.NEGATIVE;
            }
            case "[B]" -> {
                if (this.ub <= this.ui && this.trcd < this.ta &&
                        ((this.ia >= this.in / 2) && (this.ia <= this.in * 2))) {
                    result = Result.POSITIVE;
                } else result = Result.NEGATIVE;
            }
        }
        setResult(result);
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.circuitBreaker, this.type,
                this.in, this.ia, this.ta, this.trcd, this.ub, this.ui, super.getResult().getName());
    }
}