import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class CartDb implements Serializable {
    HashMap<Integer, Cart> map;   //CustomerID -> cart
    
    public CartDb() throws IOException {
        ItemDb items = new ItemDb();
        map = new HashMap<Integer, Cart>();
        
        HashMap<Item, Integer> totalItems = new HashMap<Item, Integer>();
        totalItems.put(items.map.get(1), items.map.get(1).stock);
        totalItems.put(items.map.get(3), items.map.get(3).stock);
        totalItems.put(items.map.get(2), items.map.get(2).stock);
        map.put(1, new Cart(totalItems, new DiscountCode(010100, 0.20, "20% off entire purchase", false)));
        
        totalItems.remove(items.map.get(2));
        totalItems.put(items.map.get(5), items.map.get(5).stock);
        map.put(2, new Cart(totalItems, new DiscountCode(000117, 0.10, "10% off item 5", true)));



        try {
            File f = new File("discountDataBase.txt");
            FileOutputStream fileOutput = new FileOutputStream(f);
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for(Entry<Customer, Cart> entry : map.entrySet()) {
                objOutput.writeObject(entry.getValue());
            }
            objOutput.flush();
            objOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            @SuppressWarnings("resource")
            ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream("newDiscountData.txt"));
            while(true) {
                Customer c = (Customer) fileInput.readObject();
                map.put(c, c.shoppingCart);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

