package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Floor;

@Service
public class PdfService {

    public int calculateNumberOfPages(Building building){

        int count = 0;

        for(Floor floor : building.getFloors()) {
            count += floor.getNecessaryPagesCount();
        }
        return count;
    }
}
