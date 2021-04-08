package Assignment4.Model;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    public String name;
    public int price;
    public MenuItem(String name,int price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name +" "+price;
    }
    public int computePrice() {
        return price;
    }

}
