/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Leqaa Hani
 */
public class NewLineDialog extends JDialog{
    private JTextField name,count,price;
    private JLabel namelbl,countlbl,pricelbl;
    private JButton ok,cancel;
    
    public NewLineDialog(InvoicesUI frame)
    {
        name=new JTextField(20);
        namelbl=new JLabel("Item Name");
        count=new JTextField(20);
        countlbl=new JLabel("Item Count");
        price=new JTextField(20);
        pricelbl=new JLabel("Item Price");
        
        
        ok=new JButton("OK");
        cancel=new JButton("Cancel");
        ok.setActionCommand("doneLineCreation");
        cancel.setActionCommand("cancelLineCreation");
        ok.addActionListener(frame.getController());
        cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 1));
        add(namelbl);
        add(name);
        add(pricelbl);
        add(price);
        add(countlbl);
        add(count);
        add(ok);
        add(cancel);
        pack();
    }

    public JTextField getItemName(){
        return name;
    }

    public JTextField getCount() {
        return count;
    }

    public JTextField getPrice() {
        return price;
    }
    
    
    
}
