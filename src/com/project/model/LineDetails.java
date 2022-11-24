/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author Leqaa Hani
 */
public class LineDetails {
    private int count;
    private double price;
    private String itemName;
    private InvoiceDetails invoice;

    public LineDetails() {
    }

    public LineDetails(int count, double price, String itemName,InvoiceDetails invoice) {
       
        this.count = count;
        this.price = price;
        this.itemName = itemName;
        this.invoice=invoice;
    }

    public InvoiceDetails getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDetails invoice) {
        this.invoice = invoice;
    }

    public double getTotalForLine()
    {
        return price*count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String saveAsfile()
    {
        return invoice.getId()+","+itemName+","+price+","+count;
    }
    
}
