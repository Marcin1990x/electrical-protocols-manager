package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;

@RestController
@RequestMapping("/4")
public class SoilResistanceController
        implements MeasurementMainController<SoilResistance> {

    @Autowired
    private SoilResistanceService mainService;

    @Override
    public List<SoilResistance> getMains() {

        return mainService.getMains();
    }

    @Override
    public SoilResistance addMain(SoilResistance main) {

        return mainService.addMain(main);
    }

    @Override
    public SoilResistance addEntryToMain(int mainId, int entryId) {

        return mainService.addEntryToMain(mainId, entryId);
    }
}
