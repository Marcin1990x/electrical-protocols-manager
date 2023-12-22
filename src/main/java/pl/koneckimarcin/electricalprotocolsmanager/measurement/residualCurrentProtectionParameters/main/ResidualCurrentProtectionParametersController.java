package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;

@RestController
@RequestMapping("/3")
public class ResidualCurrentProtectionParametersController
        implements MeasurementMainController<ResidualCurrentProtectionParameters> {

    @Autowired
    private ResidualCurrentProtectionParametersService service;

    @Override
    public List<ResidualCurrentProtectionParameters> getMains() {

        return service.getMains();
    }

    @Override
    public ResidualCurrentProtectionParameters addMain(ResidualCurrentProtectionParameters main) {

        return service.addMain(main);
    }

    @Override
    public ResidualCurrentProtectionParameters addEntryToMain(int mainId, int entryId) {

        return service.addEntryToMain(mainId, entryId);
    }
}
