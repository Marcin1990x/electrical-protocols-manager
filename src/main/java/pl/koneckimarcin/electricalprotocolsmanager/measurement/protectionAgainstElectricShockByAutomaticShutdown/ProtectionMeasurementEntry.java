package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class ProtectionMeasurementEntry extends MeasurementEntry {

    private String measuringPoint;
    private String cutout;
    private char type;
    private float in;
    private float ia;
    private float zs;
    private float za;
    private float ik;

    public ProtectionMeasurementEntry(int id, String symbol, Result result, String measuringPoint,
                                      String cutout, char type, float in, float ia, float zs,
                                      float za, float ik) {
        super(id, symbol, result);
        this.measuringPoint = measuringPoint;
        this.cutout = cutout;
        this.type = type;
        this.in = in;
        this.ia = ia;
        this.zs = zs;
        this.za = za;
        this.ik = ik;
    }

    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.measuringPoint, this.cutout, this.type, this.in,
                this.ia, this.zs, this.za, this.ik, super.getResult().getName());
    }
}
