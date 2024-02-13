import java.util.Date;

//Beställning class represents a beställning table with details.
public class Beställning {
    //details
    protected int id; //id
    protected Date datum; //date of beställning
    protected int totalpris; //total price of the beställning
    protected Kund kund; //Customer who placed the beställning.

    //Default constructor
    public Beställning(){}

    //Constructor to initialize a beställning object with provided values for each field.
    public Beställning(int id, Date datum, int totalpris, Kund kund) {
        this.id = id;
        this.datum = datum;
        this.totalpris = totalpris;
        this.kund = kund;
    }

    //Getters and setters.
    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getTotalpris() {
        return totalpris;
    }

    public void setTotalpris(int totalpris) {
        this.totalpris = totalpris;
    }
}
