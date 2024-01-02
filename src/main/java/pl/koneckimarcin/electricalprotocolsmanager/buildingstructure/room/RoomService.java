package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private MeasurementMainRepository mainRepository;


    public List<Room> getRooms() {

        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(int id) {

        return roomRepository.findById(id);
    }


    public Room addRoom(Room room) {

        roomRepository.save(room);

        return room;
    }

    public void deleteById(int id, int floorId) {

        Optional<Floor> floor = floorRepository.findById(floorId);
        Optional<Room> room = roomRepository.findById(id);

        if(floor.isPresent() && room.isPresent()) {
            floor.get().removeRoom(room.get());
            roomRepository.deleteById(id);
        }
    }

    public Optional<Room> addMainToRoom(int roomId, int mainId) throws InvalidObjectException {

        Optional<Room> room = addMainToRoomLogic(roomId, mainId);
        room.ifPresent(value -> roomRepository.save(value));

        return room;
    }
    private Optional<Room> addMainToRoomLogic(int roomId, int mainId) throws InvalidObjectException {

        Optional<MeasurementMain> main = mainRepository.findById(mainId);
        Optional<Room> room = roomRepository.findById(roomId);

        if (main.isPresent() && room.isPresent()) {
            // todo error if not added because duplicated
            boolean done = room.get().addMeasurementMain(main.get());
        } else {
            throw new InvalidObjectException("Measurement with ID: " + mainId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return room;
    }
}
