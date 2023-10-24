package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public class MeasurementEntryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String symbol;
    private Result result;

    @ManyToOne
    private MeasurementMainDto measurementMain;

    public MeasurementEntryDto() {
    }

    public MeasurementEntryDto(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Result getResult() {
        if (result != null) return result;
        else return Result.NONE;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Object> getEntryResultList() {

        return List.of(this.id, this.symbol, this.result.getName());
    }
}
