import java.io.Serializable;
import java.util.HashMap;
import java.util.zip.CheckedInputStream;

public class Cart implements Serializable {
    HashMap<Item, Integer> allItems; //Item -> quantity in cart
    int customerID;
    Address addr;
    DiscountCode currentDiscount; 
    double unadjustedPrice;
    double totalTax;
    double totalDiscount;
    double totalPrice;

    public Cart(int id, DiscountCode discountCode) {
        this.customerID = id;
        this.currentDiscount = discountCode;
        this.allItems = new HashMap<Item, Integer>();
    }

    public void addItem(Item item, int quantity) {
        if(!item.checkQuantity(quantity)){
            System.out.println("Error: Insufficient Item stock [stock: "+ item.stock +"] [request: " + quantity +"]");
            return;
        }  
        if(allItems.containsKey(item)) {
            int saveQuantity = allItems.get(item);
            allItems.remove(item);
            allItems.put(item, (saveQuantity + quantity));
        } else {
        allItems.put(item, quantity);
        }
        item.setQuantity(item.stock-quantity);
    }

    public void viewCart(double tax) {
        System.out.println("Items:");
        for(Item elem : allItems.keySet()) {
            System.out.println(elem.name + " $" + elem.price + " x" + allItems.get(elem));
        }
        this.unadjustedPrice = calculateTotal();
        this.totalDiscount = calculateDiscount();
        this.totalTax = this.unadjustedPrice * tax;
        this.totalPrice = this.unadjustedPrice + this.totalTax;
        System.out.println("Total: $" + this.totalPrice);
        System.out.println("Tax: $" + this.totalTax);
        System.out.println("Discount: $" + this.totalDiscount);
    }

    private double calculateDiscount() {
        if(!this.currentDiscount.isItemDiscount)
            return (double)this.unadjustedPrice*(double)this.currentDiscount.percent;
        return 0.0;
    }

    private double calculateTotal() {
        int count = 0;
        for(Item elem : allItems.keySet()) {
            if(this.currentDiscount.isItemDiscount && this.currentDiscount.itemID == elem.ID)
                count += (elem.price - (elem.price * currentDiscount.percent)) * allItems.get(elem);
            count += elem.price * allItems.get(elem);
        } 
        return count;
    }

    public void adjustItem(Item item, int newQuantity) {   
        if(!item.checkQuantity(newQuantity)){
            System.out.println("Error: Insufficient Item stock [stock: "+ item.stock +"] [request: " + newQuantity +"]");
            return;   
        }
        if(!allItems.containsKey(item)) {
            System.out.println("Error: Item not in cart [requested ID: " + item.ID +"]");
            return;
        }
        if(allItems.get(item) - newQuantity < 0) {
            System.out.println("Error: Cannot remove Item quantity [cart currently: " + allItems.get(item) +"] [removal request: " + newQuantity +"]");
            return;
        }
        int saveQuantity = allItems.get(item);
        allItems.remove(item); 
        item.setQuantity(item.stock + saveQuantity);
        allItems.put(item, newQuantity);

    }
}