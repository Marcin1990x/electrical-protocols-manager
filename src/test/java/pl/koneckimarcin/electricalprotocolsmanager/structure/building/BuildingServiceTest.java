package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.project.Project;
import pl.koneckimarcin.electricalprotocolsmanager.structure.project.ProjectRepository;

import java.io.InvalidObjectException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BuildingServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private BuildingRepository buildingRepository;
    @Mock
    private FloorRepository floorRepository;
    @InjectMocks
    private BuildingService buildingService;

    private Building building;
    private Project project;
    private Floor floor;

    @BeforeEach
    public void setUp() {
        building = new Building();
        project = new Project();
        floor = new Floor();
    }


    @Test
    @DisplayName("Should return null if project has no building")
    public void shouldReturnNull() {

        //given
        project.setBuilding(null);
        given(projectRepository.findByProjectName("test")).willReturn(project);
        //when
        Building building = buildingService.retrieveByProjectName("test");
        //then
        assertThat(building, is(nullValue()));
    }

    @Test
    @DisplayName("Should return building with correct id")
    public void shouldReturnBuilding() {
        //given
        project.setBuilding(building);
        given(projectRepository.findByProjectName("test")).willReturn(project);
        given(buildingRepository.findById(building.getId())).willReturn(Optional.of(building));
        //when
        Building buildingTest = buildingService.retrieveByProjectName("test");
        //then
        assertThat(buildingTest, is(this.building));
    }

    @Test
    @DisplayName("Should return null building in project after delete")
    public void shouldReturnBuildingNull() {
        //given
        project.setBuilding(building);
        given(projectRepository.findByBuildingId(building.getId())).willReturn(project);
        //when
        buildingService.deleteBuildingFromProject(building.getId());
        //then
        assertThat(project.getBuilding(), is(nullValue()));
    }

    @Test
    @DisplayName("Should return building with floor added")
    public void shouldReturnBuildingWithFloor() throws InvalidObjectException {
        //given
        floor.setFloorName("TestFloor");
        given(floorRepository.findById(floor.getId())).willReturn(Optional.of(floor));
        given(buildingRepository.findById(building.getId())).willReturn(Optional.of(building));
        //when
        Optional<Building> building1 = buildingService.addFloorToBuildingLogic(building.getId(), floor.getId());
        //then
        assertThat(building1.get().getFloors(), hasSize(1));
        assertThat(building1.get().getFloors().get(0), is(floor));
    }

    @Test
    @DisplayName("Should throw InvalidObjectException if building or floor not found with correct message and id's")
    public void shouldThrowInvalidObjectException() {
        //given
        given(floorRepository.findById(5)).willReturn(Optional.empty());
        given(buildingRepository.findById(building.getId())).willReturn(Optional.of(building));
        //when then
        Exception exception = assertThrows(InvalidObjectException.class, () -> buildingService
                .addFloorToBuildingLogic(building.getId(), 5));
        assertThat(exception.getMessage(), stringContainsInOrder(String.valueOf(building.getId()), "5"));
    }
}
