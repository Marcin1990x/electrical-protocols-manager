package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;

@RestController
@RequestMapping("/0")
public class ProtectionAgainstElectricShockByAutomaticShutdownController
        implements MeasurementMainController<ProtectionAgainstElectricShockByAutomaticShutdown> {

    @Autowired
    private ProtectionAgainstElectricShockByAutomaticShutdownService mainService;

    @Override
    public List<ProtectionAgainstElectricShockByAutomaticShutdown> getMains() {

        return mainService.getMains();
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdown
    addMain(ProtectionAgainstElectricShockByAutomaticShutdown main) {

        return mainService.addMain(main);
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdown addEntryToMain(int mainId, int entryId) {

        return mainService.addEntryToMain(mainId, entryId);
    }

    @PutMapping("/mains/edit={mainId}")
    public ProtectionAgainstElectricShockByAutomaticShutdown updateMain(
            @PathVariable int mainId, @RequestBody ProtectionAgainstElectricShockByAutomaticShutdown main) {

        return mainService.updateMain(mainId, main);
    }
}
