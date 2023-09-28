package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfInstallation;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfMeasurement;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfWeather;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PdfTitlePage {

    private List<Electrician> electricians;
    private String documentNumber;
    private String title;
    private String customerName;
    private String measurementPlace;
    private TypeOfMeasurement typeOfMeasurement;
    private TypeOfWeather typeOfWeather;
    private Date measurementDate;
    private Date nextMeasurementDate;
    private TypeOfInstallation typeOfInstallation;
    private String decisionDescription;

    public PdfTitlePage(List<Electrician> electricians, String documentNumber, String title, String customerName,
                        String measurementPlace, TypeOfMeasurement typeOfMeasurement, TypeOfWeather typeOfWeather,
                        Date measurementDate, TypeOfInstallation typeOfInstallation, String decisionDescription) {
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
        setNextMeasurementDate();
    }

    private void setNextMeasurementDate() {
        this.nextMeasurementDate = this.measurementDate; // add 5 years - to be done
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

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDecisionDescription() {
        return decisionDescription;
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
        measurementTextData.add(TextData.titlePageText.get(2) + TypeOfMeasurement.PERIODIC +
                "                                 " + TextData.titlePageText.get(3) + TypeOfWeather.CLOUDY);
        measurementTextData.add(TextData.titlePageText.get(4) + measurementDate.toString() +
                TextData.titlePageText.get(5) + nextMeasurementDate.toString());
        measurementTextData.add(TextData.titlePageText.get(6));
        measurementTextData.add(TypeOfInstallation.EXISTING.toString());

        return measurementTextData;
    }
}
