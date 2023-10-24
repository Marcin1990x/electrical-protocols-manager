package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Continuity;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistanceDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryDto;

import java.util.List;

@Entity
@DiscriminatorValue("5")
public class ContinuityOfSmallResistanceEntryDto extends MeasurementEntryDto {

    private String measuringPoint;
    private Continuity continuity;
    private float rs;
    private float ra;

    @ManyToOne
    private ContinuityOfSmallResistanceDto main;

    public ContinuityOfSmallResistanceEntryDto() {
    }

    public ContinuityOfSmallResistanceEntryDto(int id, String symbol, String measuringPoint,
                                               Continuity continuity, float rs, float ra) {
        super(id, symbol);
        this.measuringPoint = measuringPoint;
        this.continuity = continuity;
        this.rs = rs;
        this.ra = ra;
        setResult();
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

    private void setResult() {
        if (this.rs <= this.ra) {
            super.setResult(Result.POSITIVE);
        } else {
            super.setResult(Result.NEGATIVE);
        }
    }

    @JsonIgnore // check why ?
    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.continuity.getName(),
                this.rs, this.ra, super.getResult().getName());
    }
}