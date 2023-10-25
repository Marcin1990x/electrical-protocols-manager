package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistanceDto;

import java.util.List;

@Entity
@DiscriminatorValue("4")
public class SoilResistanceEntryDto extends MeasurementEntryDto {

    private String measuringPoint;
    private float l;
    private float d;
    private float p;

    @ManyToOne
    private SoilResistanceDto main;

    public SoilResistanceEntryDto() {
    }

    public SoilResistanceEntryDto(int id, String symbol, String measuringPoint, float l, float p) {
        super(id, symbol);
        this.measuringPoint = measuringPoint;
        this.l = l;
        this.d = this.l * 0.7f;
        this.p = p;
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

    @JsonIgnore
    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.l,
                String.format("%.2f", this.d), this.p);
    }
}