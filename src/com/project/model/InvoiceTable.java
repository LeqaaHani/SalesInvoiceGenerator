/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Leqaa Hani
 */
public class InvoiceTable extends AbstractTableModel {
    private ArrayList<InvoiceDetails>i;
    private String[] c={"No.","Date","Customer","Total"};
    public InvoiceTable(ArrayList<InvoiceDetails> i) {
        this.i = i;
    }

    @Override
    public int getRowCount() {
        return i.size();
    }

    @Override
    public int getColumnCount() {
        return c.length;
    }

    @Override
    public String getColumnName(int column) {
        return c[column];
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceDetails invoiceDetails=i.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoiceDetails.getId();
            case 1:
                return invoiceDetails.getDate();
            case 2:
                return invoiceDetails.getCustomername();
            case 3:
                return invoiceDetails.getTotal();
            default:
                return "";
        }
    }
    
    
}
