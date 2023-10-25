package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main.ResidualCurrentProtectionParametersDto;

import java.util.List;

@Entity
@DiscriminatorValue("3")
public class ResidualCurrentProtectionParametersEntryDto extends MeasurementEntryDto {

    private String measuringPoint;
    private String circuitBreaker;
    private String type;
    private int iNom;
    private int ia;
    private int ta;
    private int trcd;
    private int ub;
    private int ui;

    @ManyToOne
    private ResidualCurrentProtectionParametersDto main;

    public ResidualCurrentProtectionParametersEntryDto() {
    }

    public ResidualCurrentProtectionParametersEntryDto(int id, String symbol, String measuringPoint,
                                                       String circuitBreaker, String type, int iNom, int ia, int ta,
                                                       int trcd, int ub, int ui) {
        super(id, symbol);
        this.measuringPoint = measuringPoint;
        this.circuitBreaker = circuitBreaker;
        this.type = type;
        this.iNom = iNom;
        this.ia = ia;
        this.ta = ta;
        this.trcd = trcd;
        this.ub = ub;
        this.ui = ui;
        setResult(this.type);
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }

    public void setCircuitBreaker(String circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setiNom(int iNom) {
        this.iNom = iNom;
    }

    public void setIa(int ia) {
        this.ia = ia;
    }

    public void setTa(int ta) {
        this.ta = ta;
    }

    public void setTrcd(int trcd) {
        this.trcd = trcd;
    }

    public void setUb(int ub) {
        this.ub = ub;
    }

    public void setUi(int ui) {
        this.ui = ui;
    }

    public String getMeasuringPoint() {
        return measuringPoint;
    }

    public String getCircuitBreaker() {
        return circuitBreaker;
    }

    public String getType() {
        return type;
    }

    public int getiNom() {
        return iNom;
    }

    public int getIa() {
        return ia;
    }

    public int getTa() {
        return ta;
    }

    public int getTrcd() {
        return trcd;
    }

    public int getUb() {
        return ub;
    }

    public int getUi() {
        return ui;
    }

    private void setResult(String type) {

        Result result = Result.NONE;

        switch (type) {
            case "[AC]" -> {
                if (this.ub <= this.ui && this.trcd < this.ta && ((this.ia >= this.iNom / 2) && (this.ia <= this.iNom))) {
                    result = Result.POSITIVE;
                } else result = Result.NEGATIVE;
            }
            case "[A]" -> {
                if (this.ub <= this.ui && this.trcd < this.ta &&
                        ((this.ia >= this.iNom * 0.35) && (this.ia <= this.iNom * 1.4))) {
                    result = Result.POSITIVE;
                } else result = Result.NEGATIVE;
            }
            case "[B]" -> {
                if (this.ub <= this.ui && this.trcd < this.ta &&
                        ((this.ia >= this.iNom / 2) && (this.ia <= this.iNom * 2))) {
                    result = Result.POSITIVE;
                } else result = Result.NEGATIVE;
            }
        }
        setResult(result);
    }

    @JsonIgnore
    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.circuitBreaker, this.type,
                this.iNom, this.ia, this.ta, this.trcd, this.ub, this.ui, super.getResult().getName());
    }
}