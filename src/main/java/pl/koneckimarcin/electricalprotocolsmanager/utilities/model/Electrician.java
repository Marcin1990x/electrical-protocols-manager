package pl.koneckimarcin.electricalprotocolsmanager.utilities.model;

public class Electrician {

    private String firstName;
    private String lastName;

    public Electrician(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
