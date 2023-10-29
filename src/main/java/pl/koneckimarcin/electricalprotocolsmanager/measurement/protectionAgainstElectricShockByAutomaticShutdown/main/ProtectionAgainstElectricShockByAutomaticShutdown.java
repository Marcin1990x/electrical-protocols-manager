package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntry;

import java.util.List;

@Entity
@DiscriminatorValue("0")
public class ProtectionAgainstElectricShockByAutomaticShutdown extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(0);

    @Positive(message = "This value is mandatory.")
    private int un;
    @Positive(message = "This value is mandatory.")
    private int ui;
    @Positive(message = "This value is mandatory.")
    private float ko;
    @Positive(message = "This value is mandatory.")
    private float ta;
    @NotNull(message = "This value is mandatory.")
    private NetworkType networkType;

    @OneToMany
    private List<ProtectionMeasurementEntry> entries;

    public ProtectionAgainstElectricShockByAutomaticShutdown() {
    }

    public ProtectionAgainstElectricShockByAutomaticShutdown(int un, int ui, float ko, float ta, NetworkType networkType) {
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

    public List<ProtectionMeasurementEntry> getEntries() {
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
