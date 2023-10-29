package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Continuity;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;

import java.util.List;

@Entity
@DiscriminatorValue("5")
public class ContinuityOfSmallResistanceEntry extends MeasurementEntry {


    private String measuringPoint = "";
    @NotNull(message = "This value is mandatory.")
    private Continuity continuity;
    @Positive(message = "This value is mandatory.")
    private float rs;
    @Positive(message = "This value is mandatory.")
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