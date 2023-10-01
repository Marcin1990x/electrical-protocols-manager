package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PdfHeading {

    private String documentNumber;
    private LocalDate measurementDate;
    private List<Electrician> electricians;
    private String measurementsPlace;

    public PdfHeading(String documentNumber, LocalDate measurementDate, List<Electrician> electricians, String measurementsPlace) {
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

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(LocalDate measurementDate) {
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

    public List<String> getHeadingTextData() {

        StringBuilder builder = new StringBuilder();

        List<String> headingTextData = new ArrayList<>();
        headingTextData.add(documentNumber);
        headingTextData.add(TextData.headerText.get(0) + measurementDate.toString());
        for (Electrician electrician : electricians) {
            builder.append(electrician.getFirstName()).append(" ").append(electrician.getLastName()).append("; ");
        }
        headingTextData.add(TextData.headerText.get(1) + builder.substring(0, builder.length() - 2));
        headingTextData.add(TextData.headerText.get(2) + measurementsPlace);

        return headingTextData;
    }
}
