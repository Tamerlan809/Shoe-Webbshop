public class Order {
    protected int id;
    protected int beställningId;
    protected Skor skor;
    public Order(){}

    public Order(int id, int beställningId, Skor skor) {
        this.id = id;
        this.beställningId = beställningId;
        this.skor = skor;
    }

    public Skor getSkor() {
        return skor;
    }

    public void setSkor(Skor skor) {
        this.skor = skor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeställningId() {
        return beställningId;
    }

    public void setBeställningId(int beställningId) {
        this.beställningId = beställningId;
    }

}
