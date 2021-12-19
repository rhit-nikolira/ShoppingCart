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
        this.cartDataBase = new CartDb();
        this.shoppingCart = cartDataBase.map.get(ID);
    }

    public double getTaxRate() {
        switch(addr.State) {
            case "New York":
                return 0.04; 
            case "Nebraska":
                return 0.055;
            case "Arkansas":
                return 0.065;
            case "Kansas":
                return 0.065;
            case "West Virginia":
                return 0.06;
            case "New Hampshire":
                return 0.0;
            case "Kentucky":
                return 0.06;
        }
        return 0.0;
    }
}
