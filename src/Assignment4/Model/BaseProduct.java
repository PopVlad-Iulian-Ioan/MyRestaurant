package Assignment4.Model;

public class BaseProduct extends MenuItem {

    public BaseProduct(String name,int price){
        super (name,price);
    }

    public int computePrice() {
        return price;
    }

}
