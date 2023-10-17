package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class SoilResistanceEntry extends MeasurementEntry {

    private String measuringPoint;
    private float l;
    private float d;
    private float p;

    public SoilResistanceEntry(int id, String symbol, String measuringPoint, float l,
                               float d, float p) {
        super(id, symbol, Result.NONE);
        this.measuringPoint = measuringPoint;
        this.l = l;
        this.d = d;
        this.p = p;
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.l, this.d,
                this.p);
    }
}