package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntryDtoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/3")
public class ResidualCurrentProtectionParametersDtoController
        implements MeasurementMainController<ResidualCurrentProtectionParametersDto> {

    @Autowired
    private ResidualCurrentProtectionParametersDtoRepository mainRepository;
    @Autowired
    private ResidualCurrentProtectionParametersEntryDtoRepository entryRepository;

    @Override
    public List<ResidualCurrentProtectionParametersDto> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ResidualCurrentProtectionParametersDto addMain(ResidualCurrentProtectionParametersDto main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ResidualCurrentProtectionParametersDto addEntryToMain(int mainId, int entryId) {

        Optional<ResidualCurrentProtectionParametersDto> main = mainRepository.findById(mainId);
        Optional<ResidualCurrentProtectionParametersEntryDto> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
