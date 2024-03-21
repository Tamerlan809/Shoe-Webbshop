
//Customer class represents kund table with details.
public class Kund {
    protected  int id; //unique id
    protected String personnummer; //social security number of customer
    protected String namn; //name of the customer
    protected String ort; //county that customer is placing beställning from.
    protected String lösenord; //password of the customer.

    //default constructor.
    public Kund(){}

    //Constructor to initialize a Customer object with values for each field.
    public Kund(int id, String personnummer, String namn, String ort, String lösenord) {
        this.id = id;
        this.personnummer = personnummer;
        this.namn = namn;
        this.ort = ort;
        this.lösenord = lösenord;
    }

    //getters and setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLösenord() {
        return lösenord;
    }

    public void setLösenord(String lösenord) {
        this.lösenord = lösenord;
    }


    //Method to return info about customer's name and county.
    public String getKundDataByNameAndOrt(){
        return "Kundens namn: " + namn + " * "
                + "Ort namn: " + ort;
    }
}
