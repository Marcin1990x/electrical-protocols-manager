package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public class CircuitInsulationResistanceTnsEntry extends MeasurementEntry {

    private String circuitName;
    private int l1l2;
    private int l2l3;
    private int l3l1;
    private int l1pe;
    private int l2pe;
    private int l3pe;
    private int l1n;
    private int l2n;
    private int l3n;
    private int npe;
    private float ra;

    public CircuitInsulationResistanceTnsEntry(int id, String symbol, String circuitName,
                                               int l1l2, int l2l3, int l3l1, int l1pe, int l2pe, int l3pe,
                                               int l1n, int l2n, int l3n, int npe, float ra) {
        super(id, symbol);
        this.circuitName = circuitName;
        this.l1l2 = l1l2;
        this.l2l3 = l2l3;
        this.l3l1 = l3l1;
        this.l1pe = l1pe;
        this.l2pe = l2pe;
        this.l3pe = l3pe;
        this.l1n = l1n;
        this.l2n = l2n;
        this.l3n = l3n;
        this.npe = npe;
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
        if (this.l1l2 >= this.ra && this.l2l3 >= this.ra && this.l3l1 >= this.ra && this.l1pe >= this.ra
                && this.l2pe >= this.ra && this.l3pe >= this.ra && this.l1n >= this.ra && this.l2n >= this.ra
                && this.l3n >= this.ra && this.npe >= this.ra) {
            super.setResult(Result.POSITIVE);
        } else {
            super.setResult(Result.NEGATIVE);
        }
    }


    @Override
    public List<Object> getEntryResultList() {
        return List.of(super.getId(), super.getSymbol(), this.circuitName, this.l1l2, this.l2l3, this.l3l1,
                this.l1pe, this.l2pe, this.l3pe, this.l1n, this.l2n, this.l3n, this.npe, this.ra,
                super.getResult().getName());
    }
}