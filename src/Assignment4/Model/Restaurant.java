package Assignment4.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

public class Restaurant implements IRestaurantProcessing, Serializable {
    Map<Order, ArrayList<MenuItem>> table=new Hashtable<Order, ArrayList<MenuItem>>();
    ArrayList<MenuItem> menu=new ArrayList<MenuItem> (  );
    ArrayList<Order> order=new ArrayList<Order> (  );

    @Override
    public void createMenuItem(MenuItem item) {
        menu.add ( item );
    }

    @Override
    public void deleteMenuItem(MenuItem item) {
        menu.remove ( item );
    }

    @Override
    public void editMenuItem(String name,String newName) {
        assert name != null;
        assert newName != null;
        Iterator<MenuItem> iterator = menu.iterator ();
        MenuItem item=find ( name );
        item.setName(newName);
    }
    @Override
    public void editMenuItem(MenuItem item,int price) {
        assert item != null;
        assert price >0;
        int prePrice=0;
       prePrice=item.getPrice ();
       item.setPrice ( price );
        assert prePrice!=price;
    }

    @Override
    public void createOrder(Order order , ArrayList<MenuItem> menuItem) {
        assert order != null;
        assert menuItem != null;
        this.order.add ( order );
        table.put ( order,menuItem );
            }

    @Override
    public int computeOrderPrice(Order order) {
        assert order != null;
        assert table.containsKey(order)==true;
        int price=0;
        ArrayList<MenuItem> item=table.get(order);
        Iterator<MenuItem> iterator = item.iterator ();
        while (iterator.hasNext ()){
            MenuItem currItem = iterator.next ();
            price=price+currItem.getPrice ();
        }
        return  price;
    }

    @Override
    public void generateBill(Order order) {
        assert order!= null;
        File out=new File("bill.txt");
        try {
            PrintWriter bill=new PrintWriter ( out);
            bill.println ( "Bill:" );
            bill.println ( "ID: " +order.orderID);
            bill.println ( "Date: "+order.date.toString () );
            bill.println ( "Table: "+order.table );
            ArrayList<MenuItem> item=table.get(order);
            bill.println ( "Items:" );
            Iterator<MenuItem> iterator = item.iterator ();
            while (iterator.hasNext ()){
                MenuItem currItem = iterator.next ();
                bill.println ( currItem.toString () );
            }
            bill.println ( "Total: "+computeOrderPrice ( order ));
            bill.close ();
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }
    public MenuItem find(String name){
        Iterator<MenuItem> iterator = menu.iterator ();
        while (iterator.hasNext ()) {
            MenuItem currItem = iterator.next ();
            if (currItem.getName ().equals ( name )) {
               return currItem;
            }
        }
        return null;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
    public CompositeProduct getMenuOrder(ArrayList<String> items){
        CompositeProduct toReturn=new CompositeProduct ( "Order",new ArrayList<MenuItem> (  ) );
        Iterator<String> iterator=items.iterator ();
        while (iterator.hasNext ()){
            String curr=iterator.next ();
            toReturn.add ( find(curr) );
        }
        return toReturn;
    }
    public Order find(int id){
        Iterator<Order> iterator = this.order.iterator ();
        while (iterator.hasNext ()) {
            Order currItem = iterator.next ();
            if (currItem.orderID==id) {
                return currItem;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public Map<Order, ArrayList<MenuItem>> getTable() {
        return table;
    }
}
