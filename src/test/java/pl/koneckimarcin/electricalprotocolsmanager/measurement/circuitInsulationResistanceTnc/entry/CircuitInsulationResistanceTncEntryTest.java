package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class CircuitInsulationResistanceTncEntryTest {

    private CircuitInsulationResistanceTncEntry entry = new CircuitInsulationResistanceTncEntry();

    @BeforeEach
    public void setUp() {
        entry.setL1l2(10);
        entry.setL2l3(10);
        entry.setL3l1(10);
        entry.setL1pen(10);
        entry.setL2pen(10);
        entry.setL3pen(10);
    }

    @Test
    @DisplayName("Should set positive result.")
    public void shouldReturnPositive() {
        //given
        entry.setRa(5);
        //when
        entry.setResult();
        //then
        assertThat(entry.getResult(), equalTo(Result.POSITIVE));
    }

    @Test
    @DisplayName("Should set negative result.")
    public void shouldReturnNegative() {
        //given
        entry.setRa(12);
        //when
        entry.setResult();
        //then
        assertThat(entry.getResult(), equalTo(Result.NEGATIVE));
    }

    @Test
    @DisplayName("Should set negative result when data not complete.")
    public void shouldReturnNegativeWhenDataNotComplete() {
        //given
        entry = new CircuitInsulationResistanceTncEntry();
        entry.setRa(12);
        //when
        entry.setResult();
        //then
        assertThat(entry.getResult(), equalTo(Result.NEGATIVE));
    }
}
