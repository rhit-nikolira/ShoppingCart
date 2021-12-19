public class DiscountCode {
    int ID;
    double percent;
    String description;
    boolean isItemDiscount;

    public DiscountCode(int id, double p, String desc, boolean b) {
        this.ID = id;
        this.percent = p;
        this.description = desc;
        this.isItemDiscount = b;
    }
}
