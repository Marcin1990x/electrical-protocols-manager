package pl.koneckimarcin.electricalprotocolsmanager.measurement;

public class Common {

    public static Object emptyIfZero(int valueToCheck) {
        if(valueToCheck == 0) {
            return "";
        } else {
            return valueToCheck;
        }
    }
}
