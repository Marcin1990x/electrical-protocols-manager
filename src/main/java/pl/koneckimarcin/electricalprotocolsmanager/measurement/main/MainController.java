package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private MeasurementMainRepository repository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/types")
    public List<String> retrieveMeasurementMainTypes() {
        return TextData.measurementsMainNames;
    }

    @GetMapping("/measurements/{id}")
    public MeasurementMain getMeasurementMainById(@PathVariable int id) {
        Optional<MeasurementMain> main = repository.findById(id);
        return main.get();
    }

    @DeleteMapping("mains/{id}")
    public void deleteMeasurementMainById(@PathVariable int id, @RequestParam int roomId) {

        Optional<Room> room = roomRepository.findById(roomId);
        Optional<MeasurementMain> main = repository.findById(id);
        room.get().removeMeasurementMain(main.get());

        repository.deleteById(id);
    }
}
