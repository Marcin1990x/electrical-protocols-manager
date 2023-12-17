package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Common;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main.CircuitInsulationResistanceTns;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;

import java.util.List;

@Entity
@DiscriminatorValue("1")
public class CircuitInsulationResistanceTnsEntry extends MeasurementEntry {

    private String circuitName = "";
    private int l1l2;
    private int l2l3;
    private int l3l1;
    private int l1pe;
    private int l2pe;
    private int l3pe;
    private int l1n;
    private int l2n;
    private int l3n;
    @Positive(message = "This value is mandatory.")
    private int npe;
    @Positive(message = "This value is mandatory.")
    private float ra;

    @ManyToOne
    private CircuitInsulationResistanceTns main;

    public CircuitInsulationResistanceTnsEntry() {
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public int getL1l2() {
        return l1l2;
    }

    public void setL1l2(int l1l2) {
        this.l1l2 = l1l2;
    }

    public int getL2l3() {
        return l2l3;
    }

    public void setL2l3(int l2l3) {
        this.l2l3 = l2l3;
    }

    public int getL3l1() {
        return l3l1;
    }

    public void setL3l1(int l3l1) {
        this.l3l1 = l3l1;
    }

    public int getL1pe() {
        return l1pe;
    }

    public void setL1pe(int l1pe) {
        this.l1pe = l1pe;
    }

    public int getL2pe() {
        return l2pe;
    }

    public void setL2pe(int l2pe) {
        this.l2pe = l2pe;
    }

    public int getL3pe() {
        return l3pe;
    }

    public void setL3pe(int l3pe) {
        this.l3pe = l3pe;
    }

    public int getL1n() {
        return l1n;
    }

    public void setL1n(int l1n) {
        this.l1n = l1n;
    }

    public int getL2n() {
        return l2n;
    }

    public void setL2n(int l2n) {
        this.l2n = l2n;
    }

    public int getL3n() {
        return l3n;
    }

    public void setL3n(int l3n) {
        this.l3n = l3n;
    }

    public int getNpe() {
        return npe;
    }

    public void setNpe(int npe) {
        this.npe = npe;
    }

    public float getRa() {
        return ra;
    }

    public void setRa(float ra) {
        this.ra = ra;
    }

    public void setResult() {
        if (isFirstPhase()) {
            handlePhasesResult(List.of(l1pe, l1n, npe));
        } else if (isSecondPhase()) {
            handlePhasesResult(List.of(l2pe, l2n, npe));
        } else if (isThirdPhase()) {
            handlePhasesResult(List.of(l3pe, l3n, npe));
        } else {
            handlePhasesResult(List.of(l1l2, l2l3, l3l1, l1pe, l2pe, l3pe, l1n, l2n, l3n, npe));
        }
    }
    private boolean isFirstPhase() {
        return this.l1pe > 0 && this.l2pe == 0;
    }
    private boolean isSecondPhase() {
        return this.l2pe > 0 && this.l1pe == 0;
    }
    private boolean isThirdPhase() {
        return this.l3pe > 0 && this.l2pe == 0;
    }
    private void handlePhasesResult(List<Integer> valuesToCompare) {
        if (isEachValueEqualOrGreater(valuesToCompare)) {
            super.setResult(Result.POSITIVE);
        } else super.setResult(Result.NEGATIVE);
    }

    private boolean isEachValueEqualOrGreater(List<Integer> valuesToCompare){

        boolean result = true;

        for(Integer value : valuesToCompare){
            if(!(value >= this.ra)) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Object> getEntryResultList(int lp) {
        return List.of(
                lp,
                super.getSymbol(),
                this.circuitName,
                Common.emptyIfZero(this.l1l2),
                Common.emptyIfZero(this.l2l3),
                Common.emptyIfZero(this.l3l1),
                Common.emptyIfZero(this.l1pe),
                Common.emptyIfZero(this.l2pe),
                Common.emptyIfZero(this.l3pe),
                Common.emptyIfZero(this.l1n),
                Common.emptyIfZero(this.l2n),
                Common.emptyIfZero(this.l3n),
                Common.emptyIfZero(this.npe),
                this.ra,
                super.getResult().getName());
    }
}