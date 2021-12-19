public class Address {
    String Street;
    String City;
    String State;

    public Address(String str1, String str2, String str3) {
        Street = str1;
        City = str2;
        State = str3;
    }
    public void setStreet(String str) {
        this.Street = str; 
    }
    public void setCity(String str) {
        this.City = str;
    }
    public void setState(String str) {
        this.State = str;
    }
}
