
//Skor class represents a shoe in the webbshop with details such as id, size, colour, price,brand and inventory count.
public class Skor {
    protected int id; //id of the shoe
    protected String storlek; //size
    protected String färg; //colour
    protected int pris; //price
    protected String märke; //brand
    protected int lager; //inventory count

    //Default constructor
    public Skor(){}

    //Constructor to initialize a shoe object with provided values for each field.
    public Skor(int id, String storlek, String färg, int pris, String märke, int lager) {
        this.id = id;
        this.storlek = storlek;
        this.färg = färg;
        this.pris = pris;
        this.märke = märke;
        this.lager = lager;
    }

    //Getters and setters for each field.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorlek() {
        return storlek;
    }

    public void setStorlek(String storlek) {
        this.storlek = storlek;
    }

    public String getFärg() {
        return färg;
    }

    public void setFärg(String färg) {
        this.färg = färg;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }

    public int getLager() {
        return lager;
    }

    public void setLager(int lager) {
        this.lager = lager;
    }

    //Method to return info about colour, size, price and brand.
    public String getSkorByAllData(){
        return "Skor färg: " + färg + "| Storlek: " + storlek
                + "| Pris: " + pris + "| Märke: " + märke;
    }
}
