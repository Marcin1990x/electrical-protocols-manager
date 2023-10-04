package pl.koneckimarcin.electricalprotocolsmanager.measurement.data;

import java.util.List;

public class TextData {

    public static List<String> resultEnumText = List.of("Pozytywny", "Negatywny");

    public static List<String> measurementsMainNames = List.of("(TN-C, TN-S) Badanie ochrony przed porazeniem" +
            " przez samoczynne wylaczenie");

    public static List<String> measurementMainLabels =
            List.of("Un", "UI", "ko", "ta", "Typ sieci");

    public static List<Object> protectionAgainstElectricShockByAutomaticShutdownEntryHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Wylacznik", "Typ", "In[A]",
                    "Ia[A]", "Zs[om]", "Za[om]", "Ik[A]", "Ocena");

    public static List<String> headerText =
            List.of("Data pomiarow: ", "Wykonawca pomiarow: ", "Miejsce przeprowadzenia pomiarow: ");

    public static List<String> titlePageText =
            List.of("Zleceniodawca: ", "Miejsce przeprowadzenia pomiarow: ", "Rodzaj pomiarow: ", "Pogoda: ",
                    "Data pomiarow: ", "Data nastepnych pomiarow: ", "Instalacja: ", "Wykonawcy pomiarow:", "Orzeczenie: "
            , "Protokol z pomiarow ochronnych", "Uwagi do orzeczenia:");

    public static List<String> legendPageText =
            List.of("Legenda:");

    public static List<Object> electricianPdfTableHeaders =
            List.of("Imie", "Nazwisko", "Adres", "Uprawnienia", "Stanowisko", "Podpis");

    public static List<String> electriciansPageText =
            List.of("Prace kontrolno-pomiarowe", "Swiadectwo kwalifikacyjne SEP", "Osoby wykonujace pomiary:");

    public static List<String> typeOfMeasurementText =
            List.of("Badania okresowe", "Nowa instalacja", "Po remoncie");

    public static List<String> typeOfWeatherText =
            List.of("Sloneczna", "Pochmurna", "Deszczowa");

    public static List<String> typeOfInstallationText =
            List.of("Nowa", "Modyfikacja", "Rozbudowa", "Istniejaca");

    public static List<String> statisticPageText =
            List.of("Statystyki");

    public static List<String> protectionMeasurementStatisticText =
            List.of("Punktow pomiarowych: ", "Pozytywnych wynikow: ", "Przebadano obiektow/pomieszczen: ");
}
