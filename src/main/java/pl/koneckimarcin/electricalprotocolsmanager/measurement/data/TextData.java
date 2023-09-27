package pl.koneckimarcin.electricalprotocolsmanager.measurement.data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TextData {

    public static List<Object> protectionAgainstElectricShockByAutomaticShutdownEntryHeaders =
            List.of("CommonField1", "CommonField2", "CommonField3", "CommonField4", "Result", "SpecificField1",
                    "SpecificField2", "SpecificField3");

    public static List<String> headerText =
            List.of("Data pomiarow: ", "Wykonawca pomiarow: ", "Miejsce przeprowadzenia pomiarow: ");
}
