package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTncDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryDto;

import java.util.List;

@Entity
@DiscriminatorValue("2")
public class CircuitInsulationResistanceTncEntryDto extends MeasurementEntryDto {

    private String circuitName;
    private int l1l2;
    private int l2l3;
    private int l3l1;
    private int l1pen;
    private int l2pen;
    private int l3pen;
    private float ra;

    @ManyToOne
    private CircuitInsulationResistanceTncDto main;

    public CircuitInsulationResistanceTncEntryDto() {
    }

    public CircuitInsulationResistanceTncEntryDto(int id, String symbol, String circuitName,
                                                  int l1l2, int l2l3, int l3l1, int l1pen, int l2pen, int l3pen,
                                                  float ra) {
        super(id, symbol);
        this.circuitName = circuitName;
        this.l1l2 = l1l2;
        this.l2l3 = l2l3;
        this.l3l1 = l3l1;
        this.l1pen = l1pen;
        this.l2pen = l2pen;
        this.l3pen = l3pen;
        this.ra = ra;
        setResult();
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public void setL1l2(int l1l2) {
        this.l1l2 = l1l2;
    }

    public void setL2l3(int l2l3) {
        this.l2l3 = l2l3;
    }

    public void setL3l1(int l3l1) {
        this.l3l1 = l3l1;
    }

    public void setL1pen(int l1pen) {
        this.l1pen = l1pen;
    }

    public void setL2pen(int l2pen) {
        this.l2pen = l2pen;
    }

    public void setL3pen(int l3pen) {
        this.l3pen = l3pen;
    }

    public void setRa(float ra) {
        this.ra = ra;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public int getL1l2() {
        return l1l2;
    }

    public int getL2l3() {
        return l2l3;
    }

    public int getL3l1() {
        return l3l1;
    }

    public int getL1pen() {
        return l1pen;
    }

    public int getL2pen() {
        return l2pen;
    }

    public int getL3pen() {
        return l3pen;
    }

    public float getRa() {
        return ra;
    }

    public void setResult() {
        if (this.l1l2 >= this.ra && this.l2l3 >= this.ra && this.l3l1 >= this.ra && this.l1pen >= this.ra
                && this.l2pen >= this.ra && this.l3pen >= this.ra) {
            super.setResult(Result.POSITIVE);
        } else {
            super.setResult(Result.NEGATIVE);
        }
    }
    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.circuitName, this.l1l2, this.l2l3, this.l3l1,
                this.l1pen, this.l2pen, this.l3pen, this.ra, super.getResult().getName());
    }
}