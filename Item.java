public class Item {
    int ID; 
    String name; 
    double price; 
    int stock; 
    DiscountCode discount;

    public Item(int id, String n, double p, int q) {
        this.ID = id;
        this.name = n;
        this.price = p;
        this.stock = q;
    }   
    public int checkDiscount() {
        return 0;
    }
    public int checkQuantity(int quantity) {
        if(this.stock >= quantity) 
            return this.stock - quantity;
        System.out.println("Error: Insufficient Item stock");
        return -1;  
    }
    public void setQuantity(int newQuantity) {
        this.stock = newQuantity;
    }
    public void setItemDiscount(DiscountCode c) {
        this.discount = c;
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
    public DiscountCode getItemDiscount() {
        return this.discount;
    }
    public String getName() {
        return this.name;
    }
}
