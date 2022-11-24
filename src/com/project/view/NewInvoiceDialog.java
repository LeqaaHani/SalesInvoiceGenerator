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
public class NewInvoiceDialog extends JDialog{
    private JTextField customernmae,date;
    private JLabel customernamelbl,datelbl;
    private JButton ok,cancel;

    public NewInvoiceDialog(InvoicesUI frame) {
        customernamelbl=new JLabel("Customer name");
        customernmae=new JTextField(20);
        datelbl=new JLabel("Invoice Date");
        date=new JTextField(20);
        ok=new JButton("OK");
        cancel=new JButton("Cancel");
        ok.setActionCommand("doneCreation");
        cancel.setActionCommand("cancelCreation");
        ok.addActionListener(frame.getController());
        cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 1));
        add(customernamelbl);
        add(customernmae);
        add(datelbl);
        add(date);
        add(ok);
        add(cancel);
        pack();
    }

    public JTextField getCustomernmae() {
        return customernmae;
    }

    public JTextField getDate() {
        return date;
    }
    
    
    
    
}
