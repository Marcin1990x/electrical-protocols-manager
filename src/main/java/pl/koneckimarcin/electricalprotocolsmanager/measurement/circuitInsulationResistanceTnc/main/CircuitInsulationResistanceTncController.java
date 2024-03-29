package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.RoomRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main.ProtectionAgainstElectricShockByAutomaticShutdown;

import java.util.List;

@RestController
@RequestMapping("/2")
public class CircuitInsulationResistanceTncController
        implements MeasurementMainController<CircuitInsulationResistanceTnc> {

    @Autowired
    private CircuitInsulationResistanceTncRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTncEntryRepository entryRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CircuitInsulationResistanceTncService service;

    @Override

    public List<CircuitInsulationResistanceTnc> getMains() {

        return service.getMains();
    }

    @Override
    public CircuitInsulationResistanceTnc addMain(CircuitInsulationResistanceTnc main) {

        return service.addMain(main);
    }

    @Override
    public CircuitInsulationResistanceTnc addEntryToMain(int mainId, int entryId) {

        return service.addEntryToMain(mainId, entryId);
    }
    @PutMapping("/mains/edit={mainId}")
    public CircuitInsulationResistanceTnc updateMain(
            @PathVariable int mainId, @RequestBody CircuitInsulationResistanceTnc main) {

        return service.updateMain(mainId, main);
    }
}
