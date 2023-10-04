package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class ProtectionMeasurementEntry extends MeasurementEntry {

    public ProtectionMeasurementEntry(int id, String symbol, String measuringPoint, String cutout, char type, float in,
                                      float ia, float zs, float za, float ik, Result result) {
        super(id, symbol, measuringPoint, cutout, type, in, ia, zs, za, ik, result);
    }

    @Override
    public List<Object> getEntryResultList() {

        return super.getEntryResultList();
    }
}
