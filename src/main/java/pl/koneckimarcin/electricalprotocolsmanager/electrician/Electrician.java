package pl.koneckimarcin.electricalprotocolsmanager.electrician;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Electrician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "This value is mandatory.")
    private String firstName;
    @NotBlank(message = "This value is mandatory.")
    private String lastName;
    @NotBlank(message = "This value is mandatory.")
    private String electricianAddress;
    @NotNull(message = "This value is mandatory.")
    private List<String> permissionList;
    @NotNull(message = "This value is mandatory.")
    private Position position;
    private List<String> signature = new ArrayList<>();

    @ManyToOne
    private PdfTitlePage pdfTitlePage;

    public Electrician() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getElectricianAddress() {
        return electricianAddress;
    }

    public void setElectricianAddress(String electricianAddress) {
        this.electricianAddress = electricianAddress;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<String> getSignature() {
        return signature;
    }

    public List<String> addSignature() {

        signature.add(this.firstName + " " + this.lastName);
        signature.add(TextsPL.electriciansPageText.get(0));
        signature.add(TextsPL.electriciansPageText.get(1));
        String permissions = "";
        for (String permission : this.permissionList) {
            permissions = permissions + permission + " ";
        }
        signature.add(permissions);

        return signature;
    }

    @JsonIgnore
    public List<List<String>> getElectricianDataTextList() {

        return List.of(List.of(this.firstName),
                List.of(this.lastName),
                List.of(this.electricianAddress),
                this.permissionList,
                List.of(this.position.getName()),
                this.signature);
    }

    @Override
    public String toString() {
        return "Electrician{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", electricianAddress='" + electricianAddress + '\'' +
                ", permissionList=" + permissionList +
                ", position=" + position +
                '}';
    }
}
