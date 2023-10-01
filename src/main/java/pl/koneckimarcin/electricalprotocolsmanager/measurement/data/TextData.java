package pl.koneckimarcin.electricalprotocolsmanager.measurement.data;

import java.util.List;

public class TextData {

    public static List<Object> protectionAgainstElectricShockByAutomaticShutdownEntryHeaders =
            List.of("CommonField1", "CommonField2", "CommonField3", "CommonField4", "Result", "SpecificField1",
                    "SpecificField2", "SpecificField3");

    public static List<String> headerText =
            List.of("Data pomiarow: ", "Wykonawca pomiarow: ", "Miejsce przeprowadzenia pomiarow: ");

    public static List<String> titlePageText =
            List.of("Zleceniodawca: ", "Miejsce przeprowadzenia pomiarow: ", "Rodzaj pomiarow: ", "Pogoda: ",
                    "Data pomiarow: ", "Data nastepnych pomiarow: ", "Instalacja: ", "Wykonawcy pomiarow:", "Orzeczenie: "
            , "Protokol z pomiarow ochronnych");

    public static List<String> legendPageText =
            List.of("Legenda:");

    public static List<Object> electricianPdfTableHeaders =
            List.of("Imie", "Nazwisko", "Adres", "Uprawnienia", "Stanowisko", "Podpis");

    public static List<String> electriciansPageText =
            List.of("Prace kontrolno-pomiarowe", "Swiadectwo kwalifikacyjne SEP", "Osoby wykonujace pomiary:");
}
