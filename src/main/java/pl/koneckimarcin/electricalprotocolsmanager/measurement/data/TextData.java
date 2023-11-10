package pl.koneckimarcin.electricalprotocolsmanager.measurement.data;

import java.util.List;

public class TextData {

    public static List<String> resultEnumText = List.of("Pozytywna", "Negatywna");
    public static List<String> positionEnumText = List.of("Pomiarowiec", "Sprawdzający");
    public static List<String> continuityEnumText = List.of("Zachowana", "Niezachowana");

    public static List<String> measurementsMainNames = List.of(
            "(TN-C, TN-S) Badanie ochrony przed porażeniem przez samoczynne wyłączenie",
            "(TN-S) Badanie rezystancji izolacji obwodów",
            "(TN-C) Badanie rezystancji izolacji obwodów",
            "Parametry zabezpieczeń róźnicowoprądowych",
            "Badanie rezystywności gruntu",
            "Badanie ciągłości małych rezystancji"
    );

    public static List<String> circuitInsulationResistanceLabels =
            List.of("Uiso");

    public static List<Object> continuityOfSmallResistanceHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Ciągłość", "Rs[Ω]", "Ra[Ω]", "Ocena");

    public static List<Object> soilResistanceHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "L[m]", "d[m]", "p[Ωm]");

    public static List<Object> residualCurrentProtectionHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Wyłącznik RCD", "Typ", "In[mA]",
                    "Ia[mA]", "ta[ms]", "t rcd[ms]", "Ub[V]", "Ui[V]", "Ocena");

    public static List<Object> circuitInsulationResistanceTnsHeaders =
            List.of("Lp.", "Symbol", "Nazwa obwodu", "L1-L2 [MΩ]", "L2-L3 [MΩ]", "L3-L1 [MΩ]",
                    "L1-PE [MΩ]", "L2-PE [MΩ]", "L3-PE [MΩ]", "L1-N [MΩ]", "L2-N [MΩ]", "L3-N [MΩ]",
                    "N-PE [MΩ]", "Ra", "Ocena");

    public static List<Object> circuitInsulationResistanceTncHeaders =
            List.of("Lp.", "Symbol", "Nazwa obwodu", "L1-L2 [MΩ]", "L2-L3 [MΩ]", "L3-L1 [MΩ]",
                    "L1-PEN [MΩ]", "L2-PEN [MΩ]", "L3-PEN [MΩ]", "Ra", "Ocena");

    public static List<String> continuityOfSmallResistanceLegendText =
            List.of(
                    "Rs [Ω] : Wartość rezystancji przewodu PE",
                    "Ra [Ω] : Wartość rezystancji wymaganej dla przewodu PE",
                    "Ocena : Ocena pomiaru: pozytywna, gdy Rs <= Ra"
            );

    public static List<String> soilResistanceLegendText =
            List.of(
                    "L [m] : Odleglość między sondami",
                    "d [m] : Głębokość pomiaru",
                    "p [Ωm] : Rezystywność gruntu"
            );

    public static List<String> residualCurrentProtectionLegendText =
            List.of(
                    "Wyłącznik RCD : Nazwa elementu zabezpieczającego obwód",
                    "Typ : Charakterystyka bezpiecznika",
                    "In [mA] : Różnicowy prąd wyłączający",
                    "Ia [mA] : Prąd powodujący wyłączenie RCD",
                    "ta [ms] : Wymagany czas wyłączenia RCD",
                    "trcd [ms] : Zmierzony czas wyłączenia RCD",
                    "Ub [V] : Napięcie dotykowe zmierzone",
                    "Ui [V] : Dopuszczalne napięcie dotykowe bezpieczne",
                    "Ocena : Ocena pomiaru: - pozytywna gdy: Ub <= Ui, tRCD < ta, 1/2In < Ia < In"
            );

    public static List<String> circuitInsulationResistanceTnsLegendText =
            List.of("L1-L2 [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L1 i L2",
                    "L2-L3 [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L2 i L3",
                    "L3-L1 [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L3 i L1",
                    "L1-PE [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L1 i PE",
                    "L2-PE [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L2 i PE",
                    "L3-PE [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L3 i PE",
                    "L1-N [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L1 i N",
                    "L2-N [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L2 i N",
                    "L3-N [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L3 i N",
                    "N-PE [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami N i PE",
                    "Ra [MΩ] : Wartość rezystancji wymaganej",
                    "Ocena : Ocena pomiaru: pozytywna gdy każda zmierzona rezystancja jest większa od Ra");

    public static List<String> circuitInsulationResistanceTncLegendText =
            List.of("L1-L2 [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L1 i L2",
                    "L2-L3 [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L2 i L3",
                    "L3-L1 [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L3 i L1",
                    "L1-PEN [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L1 i PEN",
                    "L2-PEN [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L2 i PEN",
                    "L3-PEN [MΩ] : Zmierzona rezystancja izolacji pomiędzy obwodami L3 i PEN",
                    "Ra [MΩ] : Wartość rezystancji wymaganej",
                    "Ocena : Ocena pomiaru: pozytywna gdy każda zmierzona rezystancja jest większa od Ra");

    public static List<String> protectionAgainstElectricShockByAutomaticShutdownLabels =
            List.of("Un", "UI", "ko", "ta", "Typ sieci");

    public static List<Object> protectionAgainstElectricShockByAutomaticShutdownEntryHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Wyłącznik", "Typ", "In[A]",
                    "Ia[A]", "Zs[Ω]", "Za[Ω]", "Ik[A]", "Ocena");

    public static List<String> protectionAgainstElectricShockByAutomaticShutdownLegendText =
            List.of("Wyłącznik : Nazwa elementu zabezpieczającego obwód", "Typ : Charakterystyka bezpiecznika",
                    "In[A] : Prąd nominalny bezpiecznika", "Ia[A] : Prąd powodujący wyzwolenie bezpiecznika",
                    "Zs[Ω] : Zmierzona impedancja pętli zwarciowej",
                    "Za[Ω] : Wartość wymagana impedancji pętli zwarciowej: Za = (Uo/Ia)",
                    "Ik[A] : Prąd zwarcia wyliczony: Ik = Uo/Zs",
                    "Ocena: Ocena pomiaru: - pozytywna gdy: Zs<=Za lub Ud<=UI");

    public static List<String> headerText =
            List.of("Data pomiarów: ", "Wykonawca pomiarów: ", "Miejsce przeprowadzenia pomiarów: ");

    public static List<String> titlePageText =
            List.of("Zleceniodawca: ", "Miejsce przeprowadzenia pomiarów: ", "Rodzaj pomiarów: ", "Pogoda: ",
                    "Data pomiarów: ", "Data następnych pomiarów: ", "Instalacja: ", "Wykonawcy pomiarów:", "Orzeczenie: "
                    , "Protokół z pomiarów ochronnych", "Uwagi do orzeczenia:");

    public static List<String> legendPageText =
            List.of("Legenda:");

    public static List<Object> electricianPdfTableHeaders =
            List.of("Imię", "Nazwisko", "Adres", "Uprawnienia", "Stanowisko", "Podpis");

    public static List<String> electriciansPageText =
            List.of("Prace kontrolno-pomiarowe", "Świadectwo kwalifikacyjne SEP", "Osoby wykonujące pomiary:");

    public static List<String> typeOfMeasurementText =
            List.of("Badania okresowe", "Nowa instalacja", "Po remoncie");

    public static List<String> typeOfWeatherText =
            List.of("Słoneczna", "Pochmurna", "Deszczowa");

    public static List<String> typeOfInstallationText =
            List.of("Nowa", "Modyfikacja", "Rozbudowa", "Istniejąca");

    public static List<String> statisticPageText =
            List.of("Statystyki");

    public static List<String> protectionMeasurementStatisticText =
            List.of("Punktów pomiarowych: ", "Pozytywnych wyników: ", "Przebadano obiektów/pomieszczeń: ");

    public static List<String> commonStatisticText =
            List.of("Punktów pomiarowych: ", "Pozytywnych wyników: ", "Negatywnych wyników: ");

    public static List<String> soilResistanceStatisticText =
            List.of("Punktów pomiarowych: ", "Nieustalonych wyników: ");

    public static List<String> circuitInsulationMeasurementStatisticText =
            List.of("Obwodów 1-fazowych: ", "Obwodów 3-fazowych: ", "Pozytywnych wyników: ",
                    "Przebadano obiektów/pomieszczeń: ");
}
