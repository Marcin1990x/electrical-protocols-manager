package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;

import java.util.List;

public class SoilResistanceEntry extends MeasurementEntry {

    private String measuringPoint;
    private float l;
    private float d;
    private float p;

    public SoilResistanceEntry(int id, String symbol, String measuringPoint, float l, float p) {
        super(id, symbol);
        this.measuringPoint = measuringPoint;
        this.l = l;
        this.d = this.l * 0.7f;
        this.p = p;
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.l,
                String.format("%.2f", this.d), this.p);
    }
}