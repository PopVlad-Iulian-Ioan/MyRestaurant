package Assignment4.View;

import Assignment4.Model.BaseProduct;
import Assignment4.Model.MenuItem;
import Assignment4.Model.Restaurant;
import Assignment4.Model.RestaurantSerialization;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class AdminUI extends JFrame {
    private JLabel title;
    private JButton closeButton;
    private JButton createButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton showButton;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel priceLabel;
    private JTextField priceField;
    public AdminUI(Restaurant restaurant,RestaurantSerialization restaurantSerialization){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 900, 700);
        this.getContentPane().setLayout(null);

        // use a bigger font
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font buttonsFont = new Font("Times New Roman", Font.PLAIN, 32);

        title = new JLabel("Administrator");
        title.setFont(buttonsFont);
        title.setBounds(300, 50, 450, 50);
        getContentPane().add(title);

        closeButton = new JButton("Close");
        closeButton.setFont(titleFont);
        closeButton.setBounds(750, 550, 100, 50);
        getContentPane().add( closeButton );

        createButton = new JButton("Create item");
        createButton.setFont(titleFont);
        createButton.setBounds(50, 550, 125, 50);
        getContentPane().add(createButton);

        deleteButton = new JButton("Delete item");
        deleteButton.setFont(titleFont);
        deleteButton.setBounds(200, 550, 125, 50);
        getContentPane().add(deleteButton);

        editButton = new JButton("Edit item");
        editButton.setFont(titleFont);
        editButton.setBounds(350, 550, 125, 50);
        getContentPane().add(editButton);

        showButton = new JButton("Show items");
        showButton.setFont(titleFont);
        showButton.setBounds(550, 550, 125, 50);
        getContentPane().add(showButton);

        nameLabel = new JLabel("Name");
        nameLabel.setFont(titleFont);
        nameLabel.setBounds(50, 150, 50, 40);
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(titleFont);
        nameField.setBounds(100, 150, 100, 30);
        getContentPane().add(nameField);

        priceLabel = new JLabel("Price");
        priceLabel.setFont(titleFont);
        priceLabel.setBounds(50, 200, 50, 40);
        getContentPane().add(priceLabel);

        priceField = new JTextField();
        priceField.setFont(titleFont);
        priceField.setBounds(100, 200, 100, 30);
        getContentPane().add(priceField);

        this.setVisible ( false );

        closeButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        createButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nameField.getText ();
                int price=Integer.parseInt(priceField.getText ());
                restaurant.createMenuItem ( new BaseProduct (name,price));
                restaurantSerialization.serialize ( restaurant );
            }

        });
        deleteButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nameField.getText ();
                MenuItem item=restaurant.find(name);
                if(item==null)
                  showError ( "There is no such item in the menu" );
                else
                {
                    restaurant.deleteMenuItem ( item );
                    restaurantSerialization.serialize ( restaurant );
                }

            }
        });
        editButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nameField.getText ();
                int price=Integer.parseInt(priceField.getText ());
                MenuItem item=restaurant.find(name);
                if(item==null)
                    showError ( "There is no such item in the menu" );
                else
                {
                    restaurant.editMenuItem ( item,price);
                    restaurantSerialization.serialize ( restaurant );
                }
            }
        });
        showButton.addActionListener( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String columns[] = {"Name" , "Price"};
                ArrayList<MenuItem> list = restaurant.getMenu ();
                DefaultTableModel model = new DefaultTableModel ();
                model.setColumnIdentifiers ( columns );
                Object[] obj = new Object[2];
                Iterator<MenuItem> it = list.iterator ();
                while (it.hasNext ()) {
                    MenuItem currItem = it.next ();
                    System.out.println ( currItem.getName () + " " + currItem.getPrice () );

                    obj[0] = currItem.getName ();
                    obj[1] = currItem.getPrice ();
                    model.addRow ( obj );
                }

                JTable myTable = new JTable ( model );
                JScrollPane myScrollPane = new JScrollPane ();
                myScrollPane.setBounds ( 250 , 100 , 600 , 400 );
                myScrollPane.setViewportView ( myTable );
                getContentPane ().add ( myScrollPane );
            }
        });
    }
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
