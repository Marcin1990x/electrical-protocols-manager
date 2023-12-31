package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.BuildingRepository;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.Room;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.RoomRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    @Autowired
    FloorRepository floorRepository;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    RoomRepository roomRepository;

    public List<Floor> getAllFloors() {

        return floorRepository.findAll();
    }

    public void addFloor(Floor floor) {

        floorRepository.save(floor);
    }

    public void deleteFloorById(int floorId, int buildingId) {

        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<Floor> floor = floorRepository.findById(floorId);

        if(building.isPresent() && floor.isPresent()) {
            building.get().removeFloor(floor.get());
            floorRepository.deleteById(floorId);
        } // todo : throw custom exception and test it
    }

    public Optional<Floor> addRoomToFloor(int floorId, int roomId) {

        Optional<Floor> floor = null;
        try {
            floor = addRoomToFloorList(floorId, roomId);
            floorRepository.save(floor.get());
        } catch (InvalidObjectException e) {
            System.out.println("Floor with ID: " + floorId + " or " +
                    "Room with ID: " + roomId + " not found. " + e.getMessage() );
        }
        return floor;
    }
    private Optional<Floor> addRoomToFloorList(int floorId, int roomId) throws InvalidObjectException {

        Optional<Room> room = roomRepository.findById(roomId);
        Optional<Floor> floor =  floorRepository.findById(floorId);

        if(floor.isPresent() && room.isPresent())
            floor.get().addRoom(room.get());
        else {
            throw new InvalidObjectException("");
        }
        return floor;
    }
}
