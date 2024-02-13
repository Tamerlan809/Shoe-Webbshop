
public class Skor {
    protected int id;
    protected String storlek;
    protected String färg;
    protected int pris;
    protected String märke;
    protected int lager;

    public Skor(){}

    public Skor(int id, String storlek, String färg, int pris, String märke, int lager) {
        this.id = id;
        this.storlek = storlek;
        this.färg = färg;
        this.pris = pris;
        this.märke = märke;
        this.lager = lager;
    }

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

    public String getSkorByAllData(){
        return "Skor färg: " + färg + "| Storlek: " + storlek
                + "| Pris: " + pris + "| Märke: " + märke;
    }
}
