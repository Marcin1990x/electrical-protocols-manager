package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.util.Date;
import java.util.List;

public class PdfHeading {

    private String documentNumber;
    private Date measurementDate;
    private List<Electrician> electricians;
    private String measurementsPlace;

    public PdfHeading(String documentNumber, Date measurementDate, List<Electrician> electricians, String measurementsPlace) {
        this.documentNumber = documentNumber;
        this.measurementDate = measurementDate;
        this.electricians = electricians;
        this.measurementsPlace = measurementsPlace;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public List<Electrician> getElectricians() {
        return electricians;
    }

    public void setElectricians(List<Electrician> electricians) {
        this.electricians = electricians;
    }

    public String getMeasurementsPlace() {
        return measurementsPlace;
    }

    public void setMeasurementsPlace(String measurementsPlace) {
        this.measurementsPlace = measurementsPlace;
    }
}
