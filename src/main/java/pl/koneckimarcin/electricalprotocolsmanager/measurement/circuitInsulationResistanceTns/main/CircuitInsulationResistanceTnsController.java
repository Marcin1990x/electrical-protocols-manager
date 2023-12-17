package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsController
        implements MeasurementMainController<CircuitInsulationResistanceTns> {

    @Autowired
    private CircuitInsulationResistanceTnsService mainService;

    @Override
    public List<CircuitInsulationResistanceTns> getMains() {

        return mainService.getMains();
    }

    @Override
    public CircuitInsulationResistanceTns addMain(CircuitInsulationResistanceTns main) {

        return mainService.addMain(main);
    }

    @Override
    public CircuitInsulationResistanceTns addEntryToMain(int mainId, int entryId) {

        return mainService.addEntryToMain(mainId, entryId);
    }
}
