package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Continuity;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;

import java.util.List;

@Entity
@DiscriminatorValue("5")
public class ContinuityOfSmallResistanceEntry extends MeasurementEntry {

    private String measuringPoint;
    private Continuity continuity;
    private float rs;
    private float ra;

    @ManyToOne
    private ContinuityOfSmallResistance main;

    private ContinuityOfSmallResistanceEntry() {
    }

    public String getMeasuringPoint() {
        return measuringPoint;
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }

    public Continuity getContinuity() {
        return continuity;
    }

    public void setContinuity(Continuity continuity) {
        this.continuity = continuity;
    }

    public float getRs() {
        return rs;
    }

    public void setRs(float rs) {
        this.rs = rs;
    }

    public float getRa() {
        return ra;
    }

    public void setRa(float ra) {
        this.ra = ra;
    }

    public void setResult() {
        if (this.rs <= this.ra) {
            super.setResult(Result.POSITIVE);
        } else {
            super.setResult(Result.NEGATIVE);
        }
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.continuity.getName(),
                this.rs, this.ra, super.getResult().getName());
    }
}