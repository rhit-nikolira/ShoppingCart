import java.io.IOException;

public class Customer {
    int ID;
    String name;
    Address addr;
    Cart shoppingCart;
    CartDb cartDataBase; 

    public Customer(int id, String name, Address address) throws IOException {
        this.ID = id;
        this.name = name;
        this.addr = address;
        cartDataBase = new CartDb();
        this.shoppingCart = cartDataBase.map.get(ID);
    }
}
