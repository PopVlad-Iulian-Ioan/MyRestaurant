package Assignment4.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
    private JLabel titleLabel;
    private JButton waiterButton;
    private JButton adminButton;
    private JButton chefButton;
    WaiterUI waiterUI;
    AdminUI adminUI;
    ChefUI chefUI;

    public MainUI(WaiterUI waiterUI1, AdminUI adminUI1,ChefUI chefUI1) {
        this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
        this.setBounds ( 500 , 150 , 500 , 400 );
        this.getContentPane ().setLayout ( null );
        waiterUI=waiterUI1;
        adminUI=adminUI1;
        chefUI=chefUI1;
        titleLabel = new JLabel ( "Restaurant" );
        titleLabel.setFont ( new Font ( "Times New Roman" , Font.PLAIN , 32 ) );
        titleLabel.setBounds ( 170 , 50 , 450 , 50 );
        getContentPane ().add ( titleLabel );

        waiterButton = new JButton ( "Waiter" );
        waiterButton.setBounds ( 100 , 250 , 80 , 50 );
        getContentPane ().add ( waiterButton );

        adminButton = new JButton ( "Admin" );
        adminButton.setBounds ( 200 , 250 , 80 , 50 );
        getContentPane ().add ( adminButton );

        chefButton = new JButton ( "Chef" );
        chefButton.setBounds ( 300 , 250 , 80 , 50 );
        getContentPane ().add ( chefButton );
        this.setVisible ( true );

        waiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waiterUI.setVisible ( true );
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminUI.setVisible ( true );
            }
        });
        chefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefUI.setVisible ( true );
            }
        });
    }

}