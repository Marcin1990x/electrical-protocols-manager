package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class CircuitInsulationResistanceTncEntry extends MeasurementEntry {

    private String circuitName;
    private int l1l2;
    private int l2l3;
    private int l3l1;
    private int l1pen;
    private int l2pen;
    private int l3pen;
    private float ra;

    public CircuitInsulationResistanceTncEntry(int id, String symbol, String circuitName,
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

    public int getL1l2() {
        return l1l2;
    }

    public int getL2l3() {
        return l2l3;
    }

    public int getL3l1() {
        return l3l1;
    }

    private void setResult() {
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