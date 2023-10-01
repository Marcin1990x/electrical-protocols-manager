package pl.koneckimarcin.electricalprotocolsmanager.utilities.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.util.ArrayList;
import java.util.List;

public class Electrician {

    private String firstName;
    private String lastName;
    private String electricianAddress;
    private List<String> permissionList;
    private Position position;
    private List<String> signature;

    public Electrician(String firstName, String lastName, String electricianAddress, List<String> permissionList, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.electricianAddress = electricianAddress;
        this.permissionList = permissionList;
        this.position = position;
        this.signature = addSignature();
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

    public List<String> getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    private List<String> addSignature() {

        List<String> signature = new ArrayList<>();
        signature.add(this.firstName + " " + this.lastName);
        signature.add(TextData.electriciansPageText.get(0));
        signature.add(TextData.electriciansPageText.get(1));
        String permissions = "";
        for(String permission : this.permissionList) {
            permissions = permissions + permission + " ";
        }
        signature.add(permissions);

        return signature;
    }
    public List<List<String>> getElectricianDataTextList() {

        return List.of(List.of(this.firstName),
                List.of(this.lastName),
                List.of(this.electricianAddress),
                this.permissionList,
                List.of(this.position.toString()),
                this.signature);
    }
}
