package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Common;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;

import java.util.List;

@Entity
@DiscriminatorValue("2")
public class CircuitInsulationResistanceTncEntry extends MeasurementEntry {


    private String circuitName = "";
    private int l1l2;
    private int l2l3;
    private int l3l1;
    private int l1pen;
    private int l2pen;
    private int l3pen;
    @Positive(message = "This value is mandatory.")
    private float ra;

    @ManyToOne
    private CircuitInsulationResistanceTnc main;

    public CircuitInsulationResistanceTncEntry() {
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

        if(isFirstPhase()) {
            handleOnePhaseResult(this.l1pen);
        } else if (isSecondPhase()) {
            handleOnePhaseResult(this.l2pen);
        } else if(isThirdPhase()) {
            handleOnePhaseResult(this.l3pen);
        } else {
            handleAllPhasesResult();
        }
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
                Common.emptyIfZero(this.l1pen),
                Common.emptyIfZero(this.l2pen),
                Common.emptyIfZero(this.l3pen),
                this.ra,
                super.getResult().getName());
    }
    private void handleOnePhaseResult(float phaseValue) {
        if (isEqualOrGreater(phaseValue)) super.setResult(Result.POSITIVE);
        else super.setResult(Result.NEGATIVE);
    }
    private void handleAllPhasesResult(){
        if (isEachValueEqualOrGreater()) super.setResult(Result.POSITIVE);
        else super.setResult(Result.NEGATIVE);
    }

    private boolean isEachValueEqualOrGreater(){

        boolean result = true;

        List<Integer> values = List.of(this.l1l2, this.l2l3, this.l3l1, this.l1pen, this.l2pen, this.l3pen);
        for(Integer value : values){
            if(!(value >= this.ra)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isFirstPhase() {
        return this.l1pen > 0 && this.l2pen == 0;
    }
    private boolean isSecondPhase() {
        return this.l2pen > 0 && this.l1pen == 0;
    }
    private boolean isThirdPhase() {
        return this.l3pen > 0 && this.l2pen == 0;
    }
    private boolean isEqualOrGreater(float value) {
        return value >= this.ra;
    }
}