import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class API implements Serializable {
    static CustomerDb custDatabase;
    static ItemDb itemDatabase;
    static Customer currentCustomer; 

    public static void main(String[] args) throws IOException {
        custDatabase = new CustomerDb();
        itemDatabase = new ItemDb();
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.print("Customer ID: ");
            int currentID = scanner.nextInt(); 
            if(custDatabase.map.containsKey(currentID)) {
                currentCustomer = custDatabase.map.get(currentID);
                break;
            } System.out.println("Error: Cannot choose customer (ID does not exist), try between 1-7\n");
        }
        while(true) {
            System.out.println("(A)dd item to cart | (V)iew shopping cart | (C)hange item quantity | (E)xit");
            String input = scanner.next(); 
            if (input.equals("A") || input.equals("a")) {
                System.out.print("Add Item ID: ");
                int id = scanner.nextInt();
                if(itemDatabase.map.containsKey(id)) {
                    Item i = itemDatabase.map.get(id);
                    System.out.print("Add " + i.getName() + " (Stock: " + i.getStock() + ") quantity: ");
                    int q = scanner.nextInt();
                    handleAddItem(currentCustomer, i, q);
                } else System.out.println("Error: cannot add item (does not exists), try inside 1-7\n");
            } else if (input.equals("V") || input.equals("v")) {
                handleViewCart(currentCustomer);
            } else if (input.equals("C") || input.equals("c")) {
                System.out.print("Adjust Item ID: ");
                int id = scanner.nextInt();
                if(itemDatabase.map.containsKey(id)) {
                    Item i = itemDatabase.map.get(id);
                    System.out.print("Add " + i.getName() + " (Stock: " + i.getStock() + ") quantity: ");
                    int q = scanner.nextInt();
                    handleAdjustQuantity(currentCustomer, i, q);
                } else System.out.println("Error: cannot add item (does not exists), try inside 1-7\n");
            }else if (input.equals("E") || input.equals("e")) {
                scanner.close();
                return;
            }
        }
    }

    public static void handleViewCart(Customer c) {
        c.shoppingCart.viewCart(c.getTaxRate());
    }

    public static void handleAddItem(Customer c, Item item, int quantity) {
        c.shoppingCart.addItem(item, quantity);
    }

    public static void handleAdjustQuantity(Customer c, Item item, int newQuantity) {
        c.shoppingCart.adjustItem(item, newQuantity);
    }
}