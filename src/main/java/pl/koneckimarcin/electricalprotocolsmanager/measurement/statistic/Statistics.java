package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

public class Statistics {

    public int totalMeasuringPoints;
    public int totalPositiveResults;
    public int totalNegativeResults;
    public int measuredObjects;
    public int totalOnePhaseCircuits;
    public int totalThreePhaseCircuits;
    public int totalNoneResults;

    public Statistics() {
    }

    public void countNegativeResults(){
        this.totalNegativeResults = this.totalMeasuringPoints - this.totalPositiveResults;
    }


}
