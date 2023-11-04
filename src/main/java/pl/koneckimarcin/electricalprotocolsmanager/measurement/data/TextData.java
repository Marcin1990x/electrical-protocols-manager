package pl.koneckimarcin.electricalprotocolsmanager.measurement.data;

import java.util.List;

public class TextData {

    public static List<String> resultEnumText = List.of("Pozytywna", "Negatywna");
    public static List<String> positionEnumText = List.of("Pomiarowiec", "Sprawdzajacy");
    public static List<String> continuityEnumText = List.of("Zachowana", "Niezachowana");

    public static List<String> measurementsMainNames = List.of(
            "(TN-C, TN-S) Badanie ochrony przed porazeniem przez samoczynne wylaczenie",
            "(TN-S) Badanie rezystancji izolacji obwodow",
            "(TN-C) Badanie rezystancji izolacji obwodow",
            "Parametry zabezpieczen roznicowopradowych",
            "Badanie rezystywnosci gruntu",
            "Badanie ciaglosci malych rezystancji"
    );

    public static List<String> circuitInsulationResistanceLabels =
            List.of("Uiso");

    public static List<Object> continuityOfSmallResistanceHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Ciaglosc", "Rs[om]", "Ra[om]", "Ocena");

    public static List<Object> soilResistanceHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "L[m]", "d[m]", "p[OMm]");

    public static List<Object> residualCurrentProtectionHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Wylacznik RCD", "Typ", "In[mA]",
                    "Ia[mA]", "ta[ms]", "t rcd[ms]", "Ub[V]", "Ui[V]", "Ocena");

    public static List<Object> circuitInsulationResistanceTnsHeaders =
            List.of("Lp.", "Symbol", "Nazwa obwodu", "L1-L2 [Mom]", "L2-L3 [Mom]", "L3-L1 [Mom]",
                    "L1-PE [Mom]", "L2-PE [Mom]", "L3-PE [Mom]", "L1-N [Mom]", "L2-N [Mom]", "L3-N [Mom]",
                    "N-PE [Mom]", "Ra", "Ocena");

    public static List<Object> circuitInsulationResistanceTncHeaders =
            List.of("Lp.", "Symbol", "Nazwa obwodu", "L1-L2 [Mom]", "L2-L3 [Mom]", "L3-L1 [Mom]",
                    "L1-PEN [Mom]", "L2-PEN [Mom]", "L3-PEN [Mom]", "Ra", "Ocena");

    public static List<String> continuityOfSmallResistanceLegendText =
            List.of(
                    "Rs [Om] : Wartosc rezystancji przewodu PE",
                    "Ra [Om] : Wartosc rezystancji wymaganej dla przewodu PE",
                    "Ocena : Ocena pomiaru: pozytywna, gdy Rs <= Ra"
            );

    public static List<String> soilResistanceLegendText =
            List.of(
                    "L [m] : Odleglosc miedzy sondami",
                    "d [m] : Glebokosc pomiaru",
                    "p [OMm] : Rezystywnosc gruntu"
            );

    public static List<String> residualCurrentProtectionLegendText =
            List.of(
                    "Wylacznik RCD : Nazwa elementu zabezpieczajacego obwod",
                    "Typ : Charakterystyka bezpiecznika",
                    "In [mA] : Roznicowy prad wylaczajacy",
                    "Ia [mA] : Prad powodujacy wylaczenie RCD",
                    "ta [ms] : Wymagany czas wylaczenia RCD",
                    "trcd [ms] : Zmierzony czas wylaczenia RCD",
                    "Ub [V] : Napiecie dotykowe zmierzone",
                    "Ui [V] : Dopuszczalne napiecie dotykowe bezpieczne",
                    "Ocena : Ocena pomiaru: - pozytywna gdy: Ub <= Ui, tRCD < ta, 1/2In < Ia < In"
            );

    public static List<String> circuitInsulationResistanceTnsLegendText =
            List.of("L1-L2 [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L1 i L2",
                    "L2-L3 [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L2 i L3",
                    "L3-L1 [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L3 i L1",
                    "L1-PE [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L1 i PE",
                    "L2-PE [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L2 i PE",
                    "L3-PE [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L3 i PE",
                    "L1-N [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L1 i N",
                    "L2-N [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L2 i N",
                    "L3-N [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L3 i N",
                    "N-PE [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami N i PE",
                    "Ra [Mom] : Wartosc rezystancji wymaganej",
                    "Ocena : Ocena pomiaru: pozytywna gdy kazda zmierzona rezystancja jest wieksza od Ra");

    public static List<String> circuitInsulationResistanceTncLegendText =
            List.of("L1-L2 [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L1 i L2",
                    "L2-L3 [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L2 i L3",
                    "L3-L1 [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L3 i L1",
                    "L1-PEN [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L1 i PEN",
                    "L2-PEN [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L2 i PEN",
                    "L3-PEN [Mom] : Zmierzona rezystancja izolacji pomiedzy obwodami L3 i PEN",
                    "Ra [Mom] : Wartosc rezystancji wymaganej",
                    "Ocena : Ocena pomiaru: pozytywna gdy kazda zmierzona rezystancja jest wieksza od Ra");

    public static List<String> protectionAgainstElectricShockByAutomaticShutdownLabels =
            List.of("Un", "UI", "ko", "ta", "Typ sieci");

    public static List<Object> protectionAgainstElectricShockByAutomaticShutdownEntryHeaders =
            List.of("Lp.", "Symbol", "Badany punkt", "Wylacznik", "Typ", "In[A]",
                    "Ia[A]", "Zs[om]", "Za[om]", "Ik[A]", "Ocena");

    public static List<String> protectionAgainstElectricShockByAutomaticShutdownLegendText =
            List.of("Wylacznik : Nazwa elementu zabezpieczajacego obwod", "Typ : Charakterystyka bezpiecznika",
                    "In[A] : Prad nominalny bezpiecznika", "Ia[A] : Prad powodujacy wyzwolenie bezpiecznika",
                    "Zs[om] : Zmierzona impedancja petli zwarciowej",
                    "Za[om] : Wartosc wymagana impedancji petli zwarciowej: Za = (Uo/Ia)",
                    "Ik[A] : Prad zwarcia wyliczony: Ik = Uo/Zs",
                    "Ocena: Ocena pomiaru: - pozytywna gdy: Zs<=Za lub Ud<=UI");

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

    public static List<String> commonStatisticText =
            List.of("Punktow pomiarowych: ", "Pozytywnych wynikow: ", "Negatywnych wynikow: ");

    public static List<String> soilResistanceStatisticText =
            List.of("Punktow pomiarowych: ", "Nieustalonych wynikow: ");

    public static List<String> circuitInsulationMeasurementStatisticText =
            List.of("Obwodow 1-fazowych: ", "Obwodow 3-fazowych: ", "Pozytywnych wynikow: ",
                    "Przebadano obiektow/pomieszczen: ");
}
