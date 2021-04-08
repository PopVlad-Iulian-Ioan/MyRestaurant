package Assignment4.Model;

import java.util.ArrayList;

public interface IRestaurantProcessing {
    /**
     * @pre item != null
     */
    void createMenuItem(MenuItem item);

    /**
     * @param item
     *	@pre item!=null
     */
    void deleteMenuItem(MenuItem item);

    /**
     * @pre newName!=null
     * @pre name!=null
     * @param newName
     * @param name
     */
    void editMenuItem(String name,String newName);
    /**
     * @pre item!=null
     * @pre price>0
     * @param item
     * @param price
     * @post prePrice!=price
     */
    void editMenuItem(MenuItem item,int price);


    /**
     *
     * @param order
     * @param menuItem
     *
     * @pre order != null
     * @pre menuItem != null
     */
    void createOrder(Order order, ArrayList<MenuItem> menuItem);


    /**
     * @pre order!=null
     * @pre table.containsKey(order)==true
     */
    int computeOrderPrice(Order order);


    /**
     *
     * @param order
     * @pre order != null
     */
    void generateBill(Order order);
}


