package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceController
        implements MeasurementMainController<ContinuityOfSmallResistance> {

    @Autowired
    private ContinuityOfSmallResistanceService mainService;

    @Override
    public List<ContinuityOfSmallResistance> getMains() {

        return mainService.getMains();
    }

    @Override
    public ContinuityOfSmallResistance addMain(ContinuityOfSmallResistance main) {

        return mainService.addMain(main);
    }

    @Override
    public ContinuityOfSmallResistance addEntryToMain(int mainId, int entryId) {

        return mainService.addEntryToMain(mainId, entryId);
    }
}
