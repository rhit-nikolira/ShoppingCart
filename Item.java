import java.io.Serializable;

public class Item implements Serializable {
    int ID; 
    String name; 
    double price; 
    int stock; 

    public Item(int id, String n, double p, int q) {
        this.ID = id;
        this.name = n;
        this.price = p;
        this.stock = q;
    }   
    
    public boolean checkQuantity(int quantity) {
        if(this.stock >= quantity)
            return true; 
        return false;
    }
    public void setQuantity(int newQuantity) {
        this.stock = newQuantity;
    }
    public int getID() {
        return this.ID;
    }
    public double getPrice() {
        return this.price;
    }
    public int getStock() {
        return this.stock;
    }
    public String getName() {
        return this.name;
    }
}
