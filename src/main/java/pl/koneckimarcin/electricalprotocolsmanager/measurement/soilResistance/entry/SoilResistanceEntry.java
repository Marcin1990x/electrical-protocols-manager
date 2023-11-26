package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistance;

import java.util.List;

@Entity
@DiscriminatorValue("4")
public class SoilResistanceEntry extends MeasurementEntry {

    private String measuringPoint = "";
    @Positive(message = "This value is mandatory.")
    private float l;
    @Positive(message = "This value is mandatory.")
    private float d;
    @Positive(message = "This value is mandatory.")
    private float p;

    @ManyToOne
    private SoilResistance main;

    public SoilResistanceEntry() {
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }

    public void setL(float l) {
        this.l = l;
    }

    public void setD(float d) {
        this.d = d;
    }

    public void setP(float p) {
        this.p = p;
    }

    public String getMeasuringPoint() {
        return measuringPoint;
    }

    public float getL() {
        return l;
    }

    public float getD() {
        return d;
    }

    public float getP() {
        return p;
    }

    @Override
    public List<Object> getEntryResultList(int lp) {
        return List.of(lp, super.getSymbol(), this.measuringPoint, this.l,
                String.format("%.2f", this.d), this.p);
    }
}