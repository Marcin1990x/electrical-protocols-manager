package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "test", discriminatorType = DiscriminatorType.INTEGER)
public class MeasurementEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String symbol = "";
    private Result result;

    @ManyToOne
    private MeasurementMain measurementMain;

    public MeasurementEntry() {
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Object> getEntryResultList() {

        return List.of(this.id, this.symbol, this.result.getName());
    }
}
