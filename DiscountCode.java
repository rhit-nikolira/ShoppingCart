public class DiscountCode {
    int ID;
    double percent = 0.0;
    String description;
    boolean isItemDiscount;
    int itemID;

    public DiscountCode(int id, double p, String desc, boolean b) {
        this.ID = id;
        this.percent = p;
        this.description = desc;
        this.isItemDiscount = b;
    }
    public DiscountCode(int id, double p, String desc, boolean b, int itemID) {
        this.ID = id;
        this.percent = p;
        this.description = desc;
        this.isItemDiscount = b;
        this.itemID = itemID;
    }
}
