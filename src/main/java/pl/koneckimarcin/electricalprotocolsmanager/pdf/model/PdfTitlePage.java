package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfInstallation;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfMeasurement;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfWeather;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PdfTitlePage {

    private List<Electrician> electricians;
    private String documentNumber;
    private String title;
    private String customerName;
    private String measurementPlace;
    private TypeOfMeasurement typeOfMeasurement;
    private TypeOfWeather typeOfWeather;
    private LocalDate measurementDate;
    private LocalDate nextMeasurementDate;
    private TypeOfInstallation typeOfInstallation;
    private String decisionDescription;
    private String comments;

    public PdfTitlePage(List<Electrician> electricians, String documentNumber, String title, String customerName,
                        String measurementPlace, TypeOfMeasurement typeOfMeasurement, TypeOfWeather typeOfWeather,
                        LocalDate measurementDate, TypeOfInstallation typeOfInstallation, String decisionDescription,
                        String comments) {
        this.electricians = electricians;
        this.documentNumber = documentNumber;
        this.title = title;
        this.customerName = customerName;
        this.measurementPlace = measurementPlace;
        this.typeOfMeasurement = typeOfMeasurement;
        this.typeOfWeather = typeOfWeather;
        this.measurementDate = measurementDate;
        this.typeOfInstallation = typeOfInstallation;
        this.decisionDescription = decisionDescription;
        this.comments = comments;
        setNextMeasurementDate();
    }

    private void setNextMeasurementDate() {
        this.nextMeasurementDate = this.measurementDate.plusYears(5);
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public List<Electrician> getElectricians() {
        return electricians;
    }

    public String getMeasurementPlace() {
        return measurementPlace;
    }

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDecisionDescription() {
        return decisionDescription;
    }

    public String getComments() {
        return comments;
    }

    public String getTypeOfMeasurement() {

        String result = "";

        switch(this.typeOfMeasurement){
            case PERIODIC -> result = TextData.typeOfMeasurementText.get(0);
            case NEW_INSTALLATION ->  result = TextData.typeOfMeasurementText.get(1);
            case AFTER_RENOVATION -> result = TextData.typeOfMeasurementText.get(2);
        }
        return result;
    }

    public String getTypeOfWeather() {

        String result = "";

        switch(this.typeOfWeather){
            case SUNNY -> result = TextData.typeOfWeatherText.get(0);
            case CLOUDY ->  result = TextData.typeOfWeatherText.get(1);
            case RAINY -> result = TextData.typeOfWeatherText.get(2);
        }
        return result;
    }
    public String getTypeOfInstallation() {

        String result = "";

        switch(this.typeOfInstallation){
            case NEW -> result = TextData.typeOfInstallationText.get(0);
            case MODIFICATED ->  result = TextData.typeOfInstallationText.get(1);
            case EXPANDED -> result = TextData.typeOfInstallationText.get(2);
            case EXISTING -> result = TextData.typeOfInstallationText.get(3);
        }
        return result;
    }

    public List<String> getHeadingTextData() {

        StringBuilder builder = new StringBuilder();

        List<String> headingTextData = new ArrayList<>();
        headingTextData.add(this.documentNumber);
        headingTextData.add(TextData.headerText.get(0) + this.measurementDate.toString());
        for (Electrician electrician : this.electricians) {
            builder.append(electrician.getFirstName()).append(" ").append(electrician.getLastName()).append("; ");
        }
        headingTextData.add(TextData.headerText.get(1) + builder.substring(0, builder.length() - 2));
        headingTextData.add(TextData.headerText.get(2) + this.measurementPlace);

        return headingTextData;
    }

    public List<String> getElectriciansTextData() {

        List<String> electriciansText = new ArrayList<>();

        for (Electrician electrician : getElectricians()) {
            electriciansText.add(electrician.getFirstName() + " " + electrician.getLastName());
        }
        return electriciansText;
    }

    public List<String> getTitlePageMeasurementTextData() {

        List<String> measurementTextData = new ArrayList<>();
        measurementTextData.add(TextData.titlePageText.get(2) + this.getTypeOfMeasurement() +
                "                             " + TextData.titlePageText.get(3) + this.getTypeOfWeather());
        measurementTextData.add(TextData.titlePageText.get(4) + measurementDate.toString() +
                "                   " +
                TextData.titlePageText.get(5) + nextMeasurementDate.toString());
        measurementTextData.add(TextData.titlePageText.get(6));
        measurementTextData.add(this.getTypeOfInstallation());

        return measurementTextData;
    }

    public List<String> getDecisionTextData() {

        List<String> decisionTextData = new ArrayList<>();
        decisionTextData.add(TextData.titlePageText.get(8));
        decisionTextData.add(this.getDecisionDescription());
        decisionTextData.add(""); // empty line
        decisionTextData.add(TextData.titlePageText.get(10));
        decisionTextData.add(this.getComments());

        return decisionTextData;
    }
}
