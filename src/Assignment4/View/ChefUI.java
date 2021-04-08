package Assignment4.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChefUI extends JFrame {

    private JLabel titleLabel;

    private JButton closeButton;

    public ChefUI() {

        this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
        this.setBounds ( 500 , 150 , 600 , 500 );
        this.getContentPane ().setLayout ( null );

        titleLabel = new JLabel ( "Chef" );
        titleLabel.setFont ( new Font ( "Times New Roman" , Font.PLAIN , 32 ));
        titleLabel.setBounds ( 230 , 50 , 450 , 50 );
        getContentPane ().add ( titleLabel );

        closeButton = new JButton ( "Close" );
        closeButton.setBounds ( 450 , 350 , 100 , 50 );
        closeButton.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible ( false );

            }
        } );
        getContentPane ().add ( closeButton );
    }
}
