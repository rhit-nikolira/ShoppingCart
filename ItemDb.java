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
			File f = new File("itemDatabase.txt");
			FileOutputStream file = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (Map.Entry<Integer, Item> entry : this.map.entrySet()) {
				out.writeObject(entry.getValue());
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("inputItemDatabase.txt"));
			while (true) {
				Item i = (Item) in.readObject();
				this.map.put(i.ID, i);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException | ClassNotFoundException e) {
		}

    }
}