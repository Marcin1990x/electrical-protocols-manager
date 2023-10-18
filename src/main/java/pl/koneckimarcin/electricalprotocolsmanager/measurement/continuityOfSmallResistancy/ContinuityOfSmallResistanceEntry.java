package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.Continuity;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class ContinuityOfSmallResistanceEntry extends MeasurementEntry {

    private String measuringPoint;
    private Continuity continuity;
    private float rs;
    private float ra;

    public ContinuityOfSmallResistanceEntry(int id, String symbol, Result result, String measuringPoint,
                                            Continuity continuity, float rs, float ra) {
        super(id, symbol, result);
        this.measuringPoint = measuringPoint;
        this.continuity = continuity;
        this.rs = rs;
        this.ra = ra;
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.continuity.getName(),
                this.rs, this.ra, super.getResult().getName());
    }
}