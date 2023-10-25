package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntryDto;

import java.util.List;

@Entity
@DiscriminatorValue("0")
public class ProtectionAgainstElectricShockByAutomaticShutdownDto extends MeasurementMainDto {

    private final String measurementName = TextData.measurementsMainNames.get(0);

    private int un;
    private int ui;
    private float ko;
    private float ta;
    private NetworkType networkType;

    @OneToMany
    private List<ProtectionMeasurementEntryDto> entries;

    public ProtectionAgainstElectricShockByAutomaticShutdownDto() {
    }

    public ProtectionAgainstElectricShockByAutomaticShutdownDto(int un, int ui, float ko, float ta, NetworkType networkType) {
        this.un = un;
        this.ui = ui;
        this.ko = ko;
        this.ta = ta;
        this.networkType = networkType;
    }

    public int getUn() {
        return un;
    }

    public void setUn(int un) {
        this.un = un;
    }

    public int getUi() {
        return ui;
    }

    public void setUi(int ui) {
        this.ui = ui;
    }

    public float getKo() {
        return ko;
    }

    public void setKo(float ko) {
        this.ko = ko;
    }

    public float getTa() {
        return ta;
    }

    public void setTa(float ta) {
        this.ta = ta;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public List<ProtectionMeasurementEntryDto> getEntries() {
        return entries;
    }

    @JsonIgnore
    @Override
    public String getPropertiesNamesAndValues() {
        return TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(0) + " = " + this.un + " V " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(1) + " = " + this.ui + " V " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(2) + " = " + this.ko + " " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(3) + " = " + this.ta + "s " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(4) + " = " + this.networkType.name();
    }

    public String getMeasurementName() {
        return measurementName;
    }
}
