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

public class ItemDb implements Serializable{
    HashMap<Integer, Item> map;
    
    public ItemDb() throws IOException {
        map = new HashMap<Integer, Item>();
        map.put(1, new Item(1, "gum", 2.99, 30)); //ID, name, price, quantity
        map.put(2, new Item(2, "chips", 3.50, 150));
        map.put(3, new Item(3, "poster", 11.99, 2));
        map.put(4, new Item(4, "t-shirt", 8.99, 48));
        map.put(5, new Item(5, "shoes", 29.99, 10));
        map.put(6, new Item(6, "desk", 148.99, 5));
        map.put(7, new Item(7, "plastic-cups", 0.99, 3000));

        try {
            File f = new File("itemDataBase.txt");
            FileOutputStream fileOutput = new FileOutputStream(f);
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for(Map.Entry<Integer, Item> entry : map.entrySet()) {
                objOutput.writeObject(entry.getValue());
            }
            objOutput.flush();
            objOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            @SuppressWarnings("resource")
            ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream("newItemData.txt"));
            while(true) {
                Item i = (Item) fileInput.readObject();
                map.put(i.ID, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
