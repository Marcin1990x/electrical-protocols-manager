package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main.ProtectionAgainstElectricShockByAutomaticShutdown;

import java.util.List;

@Entity
@DiscriminatorValue("0")
public class ProtectionMeasurementEntry extends MeasurementEntry {


    private String measuringPoint = "";
    @NotBlank(message = "This value is mandatory.")
    private String cutout;
    @Positive(message = "This value is mandatory.")
    private int uo;
    private char type = 'B'; // add custom validation
    @Positive(message = "This value is mandatory.")
    private float iNom;
    private float ia;
    @Positive(message = "This value is mandatory.")
    private float zs;
    private float za;
    private float ik;

    @ManyToOne
    private ProtectionAgainstElectricShockByAutomaticShutdown main;

    public ProtectionMeasurementEntry() {
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }

    public void setCutout(String cutout) {
        this.cutout = cutout;
    }

    public void setUo(int uo) {
        this.uo = uo;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setiNom(float iNom) {
        this.iNom = iNom;
    }

    public void setZs(float zs) {
        this.zs = zs;
    }

    public void setIa() {
        switch (this.type) {
            case 'B' -> this.ia = this.iNom * 5;
            case 'C' -> this.ia = this.iNom * 10;
            case 'D' -> this.ia = this.iNom * 20;
        }
    }

    public void setZa() {
        this.za = this.uo / this.ia;
    }

    public void setResult() {
        if (this.zs <= this.za) super.setResult(Result.POSITIVE);
        else setResult(Result.NEGATIVE);
    }

    public void setIk() {
        this.ik = this.uo / this.zs;
    }

    public String getMeasuringPoint() {
        return measuringPoint;
    }

    public String getCutout() {
        return cutout;
    }

    public int getUo() {
        return uo;
    }

    public char getType() {
        return type;
    }

    public float getiNom() {
        return iNom;
    }

    public float getIa() {
        return ia;
    }

    public float getZs() {
        return zs;
    }

    public float getZa() {
        return za;
    }

    public float getIk() {
        return ik;
    }

    @Override
    public List<Object> getEntryResultList(int lp) {
        return List.of(lp, super.getSymbol(), this.measuringPoint, this.cutout, this.type, this.iNom,
                this.ia, this.zs, this.za, String.format("%.2f", this.ik), super.getResult().getName());
    }
}
