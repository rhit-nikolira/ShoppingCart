import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartDb implements Serializable {
    HashMap<Integer, Cart> map;   //CustomerID -> cart
    
    public CartDb() throws IOException {
        ItemDb items = new ItemDb();
        map = new HashMap<Integer, Cart>();
        
        Cart c = new Cart(1, new DiscountCode(010100, 0.20, "20% off entire purchase", false));
        c.addItem(items.map.get(1), 2);
        c.addItem(items.map.get(3), 5);
        c.addItem(items.map.get(2), 4);
        map.put(1, c);
        
        c.addItem(items.map.get(5), 1);
        map.put(2, new Cart(2, new DiscountCode(000117, 0.10, "10% off item 5", true)));
        
        try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("cartdatabase.txt"));
			while (true) {
				Cart crt = (Cart) in.readObject();
				this.map.put(crt.customerID, crt);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException | ClassNotFoundException e) {
		}
    }

    public void update(int customerID, Cart c) {
        this.map.put(customerID, c);
        try {
            File f = new File("cartdatabase.txt");
            FileOutputStream file = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (Map.Entry<Integer, Cart> entry : this.map.entrySet()) {
                out.writeObject(entry.getValue());
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public Cart getCart(int customerID) {
		return this.map.get(customerID);
	}
}