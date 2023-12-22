package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main.ResidualCurrentProtectionParameters;

import java.util.List;

@Entity
@DiscriminatorValue("3")
public class ResidualCurrentProtectionParametersEntry extends MeasurementEntry {

    private String measuringPoint = "";
    @NotBlank(message = "This value is mandatory.")
    private String circuitBreaker;
    @NotBlank(message = "This value is mandatory.")
    private String rcdType;
    @Positive(message = "This value is mandatory.")
    private int iNom;
    @Positive(message = "This value is mandatory.")
    private int ia;
    @Positive(message = "This value is mandatory.")
    private int ta;
    @Positive(message = "This value is mandatory.")
    private int trcd;
    @Positive(message = "This value is mandatory.")
    private int ub;
    @Positive(message = "This value is mandatory.")
    private int ui;

    @ManyToOne
    private ResidualCurrentProtectionParameters main;

    public ResidualCurrentProtectionParametersEntry() {
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }

    public void setCircuitBreaker(String circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public void setRcdType(String rcdType) {
        this.rcdType = rcdType;
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

    public String getRcdType() {
        return rcdType;
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

    public void setResult() {

        Result result = Result.NEGATIVE;

        switch (this.rcdType) {
            case "[AC]" -> {
                if (isPositiveTypeAC())
                    result = Result.POSITIVE;
            }
            case "[A]" -> {
                if (isPositiveTypeA())
                    result = Result.POSITIVE;
            }
            case "[B]" -> {
                if (isPositiveTypeB())
                    result = Result.POSITIVE;
            }
        }
        setResult(result);
    }

    private boolean isPositiveTypeA(){
        return isPositiveCommon() && ((this.ia >= this.iNom * 0.35) && (this.ia <= this.iNom * 1.4));
    }
    private boolean isPositiveTypeAC(){
        return isPositiveCommon() && ((this.ia >= this.iNom / 2) && (this.ia <= this.iNom));
    }
    private boolean isPositiveTypeB() {
        return isPositiveCommon() && ((this.ia >= this.iNom / 2) && (this.ia <= this.iNom * 2));
    }
    private boolean isPositiveCommon() {
        return this.ub <= this.ui && this.trcd < this.ta;
    }

    @Override
    public List<Object> getEntryResultList(int lp) {
        return List.of(lp, super.getSymbol(), this.measuringPoint, this.circuitBreaker, this.rcdType,
                this.iNom, this.ia, this.ta, this.trcd, this.ub, this.ui, super.getResult().getName());
    }
}