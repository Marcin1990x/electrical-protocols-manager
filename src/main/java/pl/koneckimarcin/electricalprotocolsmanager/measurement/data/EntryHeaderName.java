package pl.koneckimarcin.electricalprotocolsmanager.measurement.data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntryHeaderName {

    private List<String> protectionAgainstElectricShockByAutomaticShutdownEntryHeaders =
            List.of("CommonField1", "CommonField2", "CommonField3", "CommonField4", "Result", "SpecificField1",
                    "SpecificField2", "SpecificField3");

    public List<String> getProtectionAgainstElectricShockByAutomaticShutdownEntryHeaders() {
        return protectionAgainstElectricShockByAutomaticShutdownEntryHeaders;
    }
}
