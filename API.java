import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class API {
    static CustomerDb custDatabase;
    static ItemDb itemDatabase;
    static DiscountDb discDatabase; 
    static Customer currentCustomer; 

    public static void main(String[] args) throws IOException {
        custDatabase = new CustomerDb();
        itemDatabase = new ItemDb();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Customer ID: ");
            int currentID = scanner.nextInt(); 
            if(custDatabase.map.containsKey(currentID)) {
                currentCustomer = custDatabase.map.get(currentID);
                break;
            } System.out.println("Error: Cannot choose customer (ID does not exist), try between 1-7\n");
        }
        while(true) {
            System.out.println("(A)dd item | (V)iew shopping cart | (C)hange item quantity | (E)xit\n");
            String input = scanner.next(); 
            if (input.equals("A") || input.equals("a")) {
                System.out.println("Add Item ID: ");
                int id = scanner.nextInt();
                if(itemDatabase.map.containsKey(id)) {
                    Item i = itemDatabase.map.get(id);
                    System.out.println("Add " + i.getName() + " (Stock: " + i.getStock() + ") quantity: ");
                    int q = scanner.nextInt();
                    handleAddItem(currentCustomer, i, q);
                } else System.out.println("Error: cannot add item (does not exists), try inside 1-7\n");
            } else if (input.equals("V") || input.equals("v")) {
                handleViewCart(currentCustomer);
            } else if (input.equals("C") || input.equals("c")) {
            
            } else if (input.equals("E") || input.equals("e")) {
                scanner.close();
                return;
            }
        }
    }

    public static void handleViewCart(Customer c) {
        c.shoppingCart.viewCart();
    }

    public static void handleAddItem(Customer cust, Item item, int quantity) {
        int checkQuantity = item.checkQuantity(quantity);
        if(checkQuantity >= 0) {
            item.setQuantity(checkQuantity);
            return;
        } else 
            System.out.println("Error: Insufficient " + item.name + " in stock (Stock: " + item.stock + ")");        
    }

    public void handleAdjustQuantity(Customer c, Item i, int newQuantity) {
        
    }
}