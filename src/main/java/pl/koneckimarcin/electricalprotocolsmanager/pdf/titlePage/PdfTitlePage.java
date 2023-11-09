package pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfInstallation;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfMeasurement;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfWeather;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PdfTitlePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Electrician> electricians = new ArrayList<>();
    @NotBlank(message = "This value is mandatory.")
    private String documentNumber;
    @NotBlank(message = "This value is mandatory.")
    private String title;
    @NotBlank(message = "This value is mandatory.")
    private String customerName;
    @NotBlank(message = "This value is mandatory.")
    private String measurementPlace;
    @NotNull(message = "This value is mandatory.")
    private TypeOfMeasurement typeOfMeasurement;
    @NotNull(message = "This value is mandatory.")
    private TypeOfWeather typeOfWeather;
    @NotNull(message = "This value is mandatory.")
    private LocalDate measurementDate;
    @NotNull(message = "This value is mandatory.")
    private LocalDate nextMeasurementDate;
    @NotNull(message = "This value is mandatory.")
    private TypeOfInstallation typeOfInstallation;
    private String decisionDescription;
    private String comments;

    public PdfTitlePage() {
    }

    public int getId() {
        return id;
    }

    public List<Electrician> getElectricians() {
        return electricians;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMeasurementPlace() {
        return measurementPlace;
    }

    public TypeOfMeasurement getTypeOfMeasurement() {
        return typeOfMeasurement;
    }

    public TypeOfWeather getTypeOfWeather() {
        return typeOfWeather;
    }

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public LocalDate getNextMeasurementDate() {
        return nextMeasurementDate;
    }

    public TypeOfInstallation getTypeOfInstallation() {
        return typeOfInstallation;
    }

    public String getDecisionDescription() {
        return decisionDescription;
    }

    public String getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMeasurementPlace(String measurementPlace) {
        this.measurementPlace = measurementPlace;
    }

    public void setTypeOfMeasurement(TypeOfMeasurement typeOfMeasurement) {
        this.typeOfMeasurement = typeOfMeasurement;
    }

    public void setTypeOfWeather(TypeOfWeather typeOfWeather) {
        this.typeOfWeather = typeOfWeather;
    }

    public void setMeasurementDate(LocalDate measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setNextMeasurementDate(LocalDate nextMeasurementDate) {
        this.nextMeasurementDate = nextMeasurementDate;
    }

    public void setTypeOfInstallation(TypeOfInstallation typeOfInstallation) {
        this.typeOfInstallation = typeOfInstallation;
    }

    public void setDecisionDescription(String decisionDescription) {
        this.decisionDescription = decisionDescription;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void addElectrician(Electrician electrician) {
        this.electricians.add(electrician);
    }
    public void removeElectrician(Electrician electrician) {
        this.electricians.remove(electrician);
    }

    public String getTypeOfMeasurementPdf() {

        String result = "";

        switch(this.typeOfMeasurement){
            case PERIODIC -> result = TextData.typeOfMeasurementText.get(0);
            case NEW_INSTALLATION ->  result = TextData.typeOfMeasurementText.get(1);
            case AFTER_RENOVATION -> result = TextData.typeOfMeasurementText.get(2);
        }
        return result;
    }

    public String getTypeOfWeatherPdf() {

        String result = "";

        switch(this.typeOfWeather){
            case SUNNY -> result = TextData.typeOfWeatherText.get(0);
            case CLOUDY ->  result = TextData.typeOfWeatherText.get(1);
            case RAINY -> result = TextData.typeOfWeatherText.get(2);
        }
        return result;
    }
    public String getTypeOfInstallationPdf() {

        String result = "";

        switch(this.typeOfInstallation){
            case NEW -> result = TextData.typeOfInstallationText.get(0);
            case MODIFICATED ->  result = TextData.typeOfInstallationText.get(1);
            case EXPANDED -> result = TextData.typeOfInstallationText.get(2);
            case EXISTING -> result = TextData.typeOfInstallationText.get(3);
        }
        return result;
    }

    @JsonIgnore
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
        measurementTextData.add(TextData.titlePageText.get(2) + this.getTypeOfMeasurementPdf() +
                "                             " + TextData.titlePageText.get(3) + this.getTypeOfWeatherPdf());
        measurementTextData.add(TextData.titlePageText.get(4) + measurementDate.toString() +
                "                   " +
                TextData.titlePageText.get(5) + nextMeasurementDate.toString());
        measurementTextData.add(TextData.titlePageText.get(6));
        measurementTextData.add(this.getTypeOfInstallationPdf());

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
