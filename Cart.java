import java.util.HashMap;

class Cart {
    HashMap<Item, Integer> allItems; //Item -> quantity in cart
    DiscountCode currentDiscount; 
    double unadjustedPrice;
    double totalTax;
    double totalDiscount;
    double totalPrice;

    public Cart(HashMap<Item, Integer> totalItems, DiscountCode discountCode) {
        this.allItems = totalItems;
        this.currentDiscount = discountCode;
    }

    public void viewCart() {
        System.out.println("Items:\n");
        for(Item elem : allItems.keySet()) {
            System.out.println(elem.name + " $" + elem.price + " x" + allItems.get(elem));
        }
    }
}