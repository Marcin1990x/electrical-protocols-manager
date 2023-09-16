package pl.koneckimarcin.electricalprotocolsmanager;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.PdfData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.PdfGenerator;
import pl.koneckimarcin.electricalprotocolsmanager.structure.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.Room;

import java.io.IOException;
import java.util.List;

public class Run {

    public static void run() throws IOException {

        //////////////////////////////

        ProtectionMeasurementEntry measurement1 =
                new ProtectionMeasurementEntry(0, 0, 0, "Test",
                        Result.POSITIVE, 10, 10, 'D');
        ProtectionMeasurementEntry measurement2 = measurement1;

        ProtectionAgainstElectricShockByAutomaticShutdown protection =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 1,
                10, 20, NetworkType.TNS, 0, 0);

        Room kitchen = new Room(List.of(protection), "Kitchen");
        Room livingRoom = new Room(List.of(protection), "Living Room");
        Room bedroom = new Room(List.of(protection), "Bedroom");
        Room bathroom = new Room(List.of(protection), "Bathroom");

        Floor groundFloor = new Floor(List.of(kitchen, livingRoom), "Ground floor");
        Floor firstFloor = new Floor(List.of(bedroom, bathroom), "First floor");

        Building building = new Building(List.of(groundFloor, firstFloor), "Dom");

        ///////////////////////////////

        List<String> textToPrint = PdfData.getData(building);

        PdfGenerator.createPdfDocument("F:\\Programista\\Pomiary elektryczne\\new_pdf.pdf", textToPrint);

    }
}
