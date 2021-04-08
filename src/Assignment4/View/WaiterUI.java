package Assignment4.View;

import Assignment4.Model.*;
import Assignment4.Model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class WaiterUI extends JFrame {
    private JLabel titleLabel;

    private JButton closeButton;
    private JButton createOrderButton;
    private JButton addMenuItemButton;
    private JButton showMenuButton;

    private JButton showOrdersButton;
    private JButton generateBillButton;
    private JLabel orderIDLabel;
    private JTextField orderIDField;
    private JLabel orderTableLabel;
    private JTextField orderTableField;

    private JLabel chosenItemsLabel;
    private JTextArea chosenItems;

    private JComboBox<MenuItem> menu;

    public WaiterUI(Restaurant restaurant, RestaurantSerialization restaurantSerialization){
        ArrayList<String>items=new ArrayList<String> (  );
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 1200, 700);
        this.getContentPane().setLayout(null);

        // use a bigger font
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 32);

        titleLabel = new JLabel("Waiter");
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(450, 50, 500, 50);
        getContentPane().add(titleLabel);

        orderIDLabel = new JLabel("OrderID:");
        orderIDLabel.setBounds(50, 110, 80, 30);
        getContentPane().add(orderIDLabel);

        orderIDField = new JTextField();
        orderIDField.setBounds(120, 110, 50, 30);
        getContentPane().add(orderIDField);

        orderTableLabel = new JLabel("Table:");
        orderTableLabel.setBounds(50, 150, 50, 30);
        getContentPane().add(orderTableLabel);

        orderTableField = new JTextField();
        orderTableField.setBounds(100, 150, 50, 30);
        getContentPane().add(orderTableField);

        menu = new JComboBox<MenuItem>();
        menu.setBounds(50, 250, 150, 50);
        getContentPane().add(menu);

        chosenItemsLabel = new JLabel("Chosen items");
        chosenItemsLabel.setBounds(230, 130, 100, 20);
        getContentPane().add(chosenItemsLabel);

        chosenItems = new JTextArea();
        chosenItems.setBounds(230, 150, 100, 200);
        getContentPane().add(chosenItems);

        closeButton = new JButton("Close");
        closeButton.setBounds(750, 550, 100, 50);
        getContentPane().add( closeButton );

        showMenuButton = new JButton("Show menu");
        showMenuButton.setBounds(20, 320, 110, 30);
        getContentPane().add( showMenuButton );

        addMenuItemButton = new JButton("Add");
        addMenuItemButton.setBounds(140, 320, 80, 30);
        getContentPane().add(addMenuItemButton);

        createOrderButton = new JButton("Create Order");
        createOrderButton.setBounds(50, 550, 150, 50);
        getContentPane().add(createOrderButton);

        showOrdersButton = new JButton("Show Orders");
        showOrdersButton.setBounds(550, 550, 150, 50);
        getContentPane().add(showOrdersButton);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(300, 550, 200, 50);
        getContentPane().add(generateBillButton);

        closeButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible ( false );
            }
        });
        showMenuButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.removeAllItems();
                ArrayList<MenuItem> list = restaurant.getMenu();
                Iterator<MenuItem> iterator = list.iterator();
                while (iterator.hasNext()) {
                    MenuItem currItem = iterator.next();
                    menu.addItem(currItem);
                }
            }
        });
        addMenuItemButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem) menu.getSelectedItem();
                items.add ( item.getName () );
                chosenItems.append(item.getName ());
                chosenItems.append("\n");
            }
        });
        createOrderButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderID = Integer.parseInt ( orderIDField.getText () );
                int table = Integer.parseInt ( orderTableField.getText () );
                if (restaurant.find ( orderID ) == null) {
                    Order order = new Order ( orderID , table );
                    CompositeProduct compositeProduct = restaurant.getMenuOrder ( items );
                    restaurant.createOrder ( order , compositeProduct.getMenu () );
                    Iterator<String> iterator = items.iterator ();
                    int i = 0,n=items.size () ;
                    items.clear ();
                    chosenItems.setText ( "" );
                }
                else
                    showError ( "This id already exists" );
            }
        });
        generateBillButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(orderIDField.getText ());
                Order order =restaurant.find ( id );
                if(order!=null)
                restaurant.generateBill(order);
                else{
                    showError ( "This order does not exist" );
                }
            }
        });
        showOrdersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Order> orders = restaurant.getOrder ();
                Map<Order, ArrayList<MenuItem>> table=restaurant.getTable ();
                String columns[] = { "OrderID", "Table", "OrderedItems" };

                DefaultTableModel myModel = new DefaultTableModel();
                myModel.setColumnIdentifiers(columns);

                Object[] obj = new Object[3];
                Iterator<Order> iterator = orders.iterator();
                while (iterator.hasNext()) {
                    Order currOrder = iterator.next();
                    obj[0] = currOrder.orderID;
                    obj[1] = currOrder.table;
                    Order order= restaurant.find ( currOrder.orderID );
                    ArrayList<MenuItem> menuItem=table.get ( order );
                    StringBuilder sb = new StringBuilder();
                    Iterator<MenuItem> iterator1 = menuItem.iterator();
                    while (iterator1.hasNext()) {
                        sb.append(iterator1.next().toString());
                        if (iterator1.hasNext())
                            sb.append(" , ");
                    }
                    obj[2] = sb.toString();
                    myModel.addRow(obj);
                }
                JTable myTable = new JTable(myModel);
                JScrollPane myScrollPane = new JScrollPane();
                myScrollPane.setBounds(350, 100, 800, 400);
                myScrollPane.setViewportView(myTable);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                getContentPane().add(myScrollPane);

            }
        });
    }

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
