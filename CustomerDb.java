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

public class CustomerDb implements Serializable{
    HashMap<Integer, Customer> map;  
    
    public CustomerDb() throws IOException {
        map = new HashMap<Integer, Customer>();
        map.put(1, new Customer(1, "John", new Address("7616 Doyle Loop", "Edenshire", "Nebraska"))); //ID, name, Addr[Street, City, State], cart
        map.put(2, new Customer(2, "Lukas", new Address("822 Lelia Green", "Lake Tremayneberg", "Arkansas"))); 
        map.put(3, new Customer(3, "Jake", new Address("383 Gaylord Pines", "Lelahview", "New York"))); 
        map.put(4, new Customer(4, "Ryan", new Address("11030 Woodson Rd.", "Oswego", "Kansas"))); 
        map.put(5, new Customer(5, "Sam", new Address("1319 Fayette  St.", "Hinton", "West Virginia"))); 
        map.put(6, new Customer(6, "Ron", new Address("5 Cogswell Rd.", "Gilmanton Iron Works", "New Hampshire"))); 
        map.put(7, new Customer(7, "Jared", new Address("280 Parts Ln.", " Dry Ridge", "Kentucky"))); 

        try {
            File f = new File("CustomerDataBase.txt");
            FileOutputStream fileOutput = new FileOutputStream(f);
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for(Map.Entry<Integer, Customer> entry : map.entrySet()) {
                objOutput.writeObject(entry.getValue());
            }
            objOutput.flush();
            objOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            @SuppressWarnings("resource")
            ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream("newCustomerData.txt"));
            while(true) {
                Customer c = (Customer) fileInput.readObject();
                map.put(c.ID, c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
