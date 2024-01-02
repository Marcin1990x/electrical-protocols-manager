package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.Room;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    @Autowired
    private MeasurementMainRepository mainRepository;
    @Autowired
    private RoomRepository roomRepository;

    public List<String> getMeasurementMainTypes() {

        return TextsPL.measurementsMainNames;
    }

    public MeasurementMain getMeasurementMainById(int mainId) {

        Optional<MeasurementMain> main = mainRepository.findById(mainId);
        return main.get();
    }

    public void deleteMeasurementMainById(int mainId, int roomId) {

        Optional<Room> room = roomRepository.findById(roomId);
        Optional<MeasurementMain> main = mainRepository.findById(mainId);
        room.get().removeMeasurementMain(main.get());

        mainRepository.deleteById(mainId);
    }
}
