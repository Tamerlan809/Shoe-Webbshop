import java.util.Date;

public class Beställning {
    protected int id;
    protected Date datum;
    protected int totalpris;
    protected Kund kund;
    public Beställning(){}
    public Beställning(int id, Date datum, int totalpris, Kund kund) {
        this.id = id;
        this.datum = datum;
        this.totalpris = totalpris;
        this.kund = kund;
    }

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
