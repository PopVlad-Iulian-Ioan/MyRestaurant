package Assignment4.Model;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
private ArrayList<MenuItem> menu= new ArrayList<> (  );
    public CompositeProduct(String name,ArrayList<MenuItem> menu){
        super (name,0);
        this.menu=menu;
    }

public void add(MenuItem product){
    menu.add ( product );
    price=computePrice ();
}
public void remove(MenuItem product) {
    menu.remove ( product );
}
    @Override
    public int computePrice() {
    int price=0;
        for (MenuItem product:menu) {
            price=price+product.computePrice ();
        }
        return price;
    }

    @Override
    public String toString() {
    String string="";
        for (MenuItem product:menu) {
            string=string+product.toString ()+"\n";
        }
        return string;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
}
