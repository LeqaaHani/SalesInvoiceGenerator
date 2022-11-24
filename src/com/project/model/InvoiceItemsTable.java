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
public class InvoiceItemsTable extends AbstractTableModel{
    private ArrayList<LineDetails> lineDetails;
    private String[]c={"No.","Item Name","Item Price","Item Count","Item Total"};

    public InvoiceItemsTable(ArrayList<LineDetails> lineDetails) {
        this.lineDetails = lineDetails;
    }

    public ArrayList<LineDetails> getLineDetails() {
        return lineDetails;
    }

    public void setLineDetails(ArrayList<LineDetails> lineDetails) {
        this.lineDetails = lineDetails;
    }
    

    @Override
    public int getRowCount() {
        return lineDetails.size();
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
        LineDetails l=lineDetails.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getInvoice().getId();
            case 1:
                return l.getItemName();
            case 2:
                return l.getPrice();
            case 3:
                return l.getCount();
            case 4:
                return l.getTotalForLine();
            default:
                return "";
        }
    }
    
}
