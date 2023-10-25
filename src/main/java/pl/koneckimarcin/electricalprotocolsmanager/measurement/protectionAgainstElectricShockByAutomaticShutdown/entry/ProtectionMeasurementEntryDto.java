package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main.ProtectionAgainstElectricShockByAutomaticShutdownDto;

import java.util.List;

@Entity
@DiscriminatorValue("0")
public class ProtectionMeasurementEntryDto extends MeasurementEntryDto {

    private String measuringPoint;
    private String cutout;
    private int uo;
    private char type;
    private float iNom;
    private float ia;
    private float zs;
    private float za;
    private float ik;

    @ManyToOne
    private ProtectionAgainstElectricShockByAutomaticShutdownDto main;

    public ProtectionMeasurementEntryDto() {
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
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.cutout, this.type, this.iNom,
                this.ia, this.zs, this.za, String.format("%.2f", this.ik), super.getResult().getName());
    }
}
