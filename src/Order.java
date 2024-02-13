
//Order class represents each specific order that contains only one shoe
// and taken from order table with details
public class Order {
    protected int id; //id
    protected int beställningId; //beställning Id
    protected Skor skor; //Shoe that is in the order.

    //Default constructor.
    public Order(){}

    //COnstructor to initialize a Order object with values for each field.
    public Order(int id, int beställningId, Skor skor) {
        this.id = id;
        this.beställningId = beställningId;
        this.skor = skor;
    }

    //Getters and setters
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
