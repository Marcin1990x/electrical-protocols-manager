package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class ProtectionMeasurementEntry extends MeasurementEntry {

    private String measuringPoint;
    private String cutout;
    private int uo;
    private char type;
    private float in;
    private float ia;
    private float zs;
    private float za;
    private float ik;

    public ProtectionMeasurementEntry(int id, String symbol, int uo, String measuringPoint,
                                      String cutout, char type, float in, float zs) {
        super(id, symbol);
        this.uo = uo;
        this.measuringPoint = measuringPoint;
        this.cutout = cutout;
        this.type = type;
        this.in = in;
        this.zs = zs;
        setIa(type);
        setZa();
        setResult();
        setIk();
    }

    private void setIa(char type) {
        switch (type) {
            case 'B' -> this.ia = this.in * 5;
            case 'C' -> this.ia = this.in * 10;
            case 'D' -> this.ia = this.in * 20;
        }
    }

    private void setZa() {
        this.za = this.uo / this.ia;
    }

    private void setResult() {
        if (this.zs <= this.za) super.setResult(Result.POSITIVE);
        else setResult(Result.NEGATIVE);
    }

    private void setIk() {
        this.ik = this.uo / this.zs;
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.cutout, this.type, this.in,
                this.ia, this.zs, this.za, String.format("%.2f", this.ik), super.getResult().getName());
    }
}
