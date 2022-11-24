/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

import java.util.ArrayList;

/**
 *
 * @author Leqaa Hani
 */
public class InvoiceDetails {
    private int id;
    private String customername,date;
    private ArrayList<LineDetails>line;

    public ArrayList<LineDetails> getLine() {
        if (line==null)
        {
         line=new ArrayList<>(); 
        }
        return line;
    }
    public double getTotal()
    {
        double totalResult=0.0;
        if(getLine().size()>0){
        for(LineDetails l :line)
        {
            totalResult+=l.getTotalForLine();
        }
        }
        return totalResult;
    }

    public void setLine(ArrayList<LineDetails> line) {
        this.line = line;
    }
    public InvoiceDetails()
    {
        
    }
    public InvoiceDetails(int id,String customername,String date)
    {
        this.id=id;
        this.customername=customername;
        this.date=date;
                
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String saveAsfile()
    {
        return id+","+date+","+customername;
    }
}
