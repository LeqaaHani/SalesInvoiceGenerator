/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.controller;

import com.project.model.InvoiceDetails;
import com.project.model.InvoiceItemsTable;
import com.project.model.InvoiceTable;
import com.project.model.LineDetails;
import com.project.view.InvoicesUI;
import com.project.view.NewInvoiceDialog;
import com.project.view.NewLineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Leqaa Hani
 */
public class MyController implements ActionListener,ListSelectionListener{
    private InvoicesUI frame;
    NewInvoiceDialog newInvoiceDialog;
    NewLineDialog newLineDialog;
    public MyController(InvoicesUI frame)
    {
     this.frame=frame;
     
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index=frame.getInvoicesTable().getSelectedRow();
        if (index !=-1) {
            
        
        InvoiceDetails current=frame.getInvoice().get(index);
        frame.getInvoicenumlbl().setText(""+current.getId());
        frame.getInvoicedatelbl().setText(current.getDate());
        frame.getInvoicetotallbl().setText(""+current.getTotal());
        frame.getCustomernamelbl().setText(current.getCustomername());
        InvoiceItemsTable lineDetails=new InvoiceItemsTable(current.getLine());
        frame.getInvoiceItemstable().setModel(lineDetails);
        lineDetails.fireTableDataChanged();
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String action=e.getActionCommand();
        switch(action)
        {
            case "createlinebtn":
                createNewLine();
                break;
            case "deletelinebtn":
                deleteLine();
                break;
            case "createbtn":
                createInvoice();
                break;
            case "deletebtn":
                deleteInvoice();
                break;
            case "loadmenuitem":
                    loadFile();
                break;
            case "savemenuitem":
                saveFile();
                break;
            case "doneCreation":
                done();
                break;
            case "cancelCreation":
                cancelCreation();
                break;
            case "doneLineCreation":
                doneLineCreation();
                break;
            case "cancelLineCreation":
                cancelLineCreation();
                break;
                        
        }

    }

    private void createInvoice() {
        newInvoiceDialog=new NewInvoiceDialog(frame);
        newInvoiceDialog.setVisible(true);
    }

    private void deleteInvoice() {
       int row= frame.getInvoicesTable().getSelectedRow();
        if (row !=-1) {
            frame.getInvoice().remove(row);
            frame.getInvoiceTable().fireTableDataChanged();
            
        }
    }

    private void createNewLine() {
        newLineDialog=new NewLineDialog(frame);
        newLineDialog.setVisible(true);
        
    }

    private void deleteLine() {
        int index=frame.getInvoiceItemstable().getSelectedRow();
        if (index!=-1) {
            InvoiceItemsTable invoiceItemsTable=(InvoiceItemsTable)frame.getInvoiceItemstable().getModel();
            invoiceItemsTable.getLineDetails().remove(index);
            invoiceItemsTable.fireTableDataChanged();
            frame.getInvoiceTable().fireTableDataChanged();

        }
    }
    
    private void loadFile()  {
        JFileChooser f=new JFileChooser();
        InvoiceDetails invoice;
         ArrayList<InvoiceDetails>invoices = new ArrayList<>();
        try{
      int returnvalue=f.showOpenDialog(frame);
        if (returnvalue ==JFileChooser.APPROVE_OPTION) {
            File file=f.getSelectedFile();
            Path filePath=Paths.get(file.getAbsolutePath());
            List<String>fileContent=Files.readAllLines(filePath);
           
            for(String content:fileContent)
            {
              String[] contentParts=content.split(",");
              int id=Integer.parseInt(contentParts[0]);
              String date=contentParts[1];
              String customerName=contentParts[2];
              invoice=new InvoiceDetails(id,customerName,date);
              invoices.add(invoice);
            }
            
            returnvalue=f.showOpenDialog(frame);
            if (returnvalue==JFileChooser.APPROVE_OPTION) 
            {
                File line =f.getSelectedFile();
                Path path=Paths.get(line.getAbsolutePath());
                List<String>lineContent=Files.readAllLines(path);
                for (String l:lineContent) 
                {
                    String [] lineParts=l.split(",");
                    int id=Integer.parseInt(lineParts[0]);
                    String name=lineParts[1];
                    double price=Double.parseDouble(lineParts[2]);
                    int count=Integer.parseInt(lineParts[3]);
                    InvoiceDetails i=null;
                    for(InvoiceDetails in :invoices)
                    {
                        if(in.getId()==id)
                        {
                         i=in;
                         break;
                        }
                    }
                    LineDetails linedetails=new LineDetails(count,price,name,i);
                    i.getLine().add(linedetails);
                      
                    
                }
                
                
            }
        }
        frame.setInvoice(invoices);
        InvoiceTable invoicetable=new InvoiceTable(invoices);
        frame.setInvoiceTable(invoicetable);
        frame.getInvoicesTable().setModel(invoicetable);
        frame.getInvoiceTable().fireTableDataChanged();
        
        }
        catch(IOException e)
                {
                    System.out.println("Cannot Read File");                
                }
    }

    private void saveFile() {
        ArrayList<InvoiceDetails>invoiceDetails=frame.getInvoice();
        String invoicesheader="";
        String lines="";
        for(InvoiceDetails invoice:invoiceDetails)
        {
            String asFile=invoice.saveAsfile();
            invoicesheader+=asFile;
            invoicesheader+="\n";
            
            for (LineDetails lineDetails:invoice.getLine()) {
                String line=lineDetails.saveAsfile();
                lines+=line;
                lines+="\n";
            }
        }
        try {
        JFileChooser file=new JFileChooser();
        int i=file.showSaveDialog(frame);
        if (i==JFileChooser.APPROVE_OPTION) {
            File invoicesFile=file.getSelectedFile();
            FileWriter fileWriterInvoices = new FileWriter(invoicesFile);
                fileWriterInvoices.write(invoicesheader);
                fileWriterInvoices.flush();
                fileWriterInvoices.close();
        i=file.showSaveDialog(frame);
        if (i==JFileChooser.APPROVE_OPTION) {
            File linesFile=file.getSelectedFile();
            FileWriter fileWriterLines = new FileWriter(linesFile);
                fileWriterLines.write(lines);
                fileWriterLines.flush();
                fileWriterLines.close();
        }
        }
        } catch (Exception e) {
            System.out.println("Cannot Save File");
        }
    }

    private void done() {
        String date=newInvoiceDialog.getDate().getText();
        String customername=newInvoiceDialog.getCustomernmae().getText();
       int id=frame.getNextID();
        InvoiceDetails invoiceDetails=new InvoiceDetails(id,customername,date);
         ArrayList<InvoiceDetails> i=frame.getInvoice();
        i.add(invoiceDetails);
        frame.getInvoiceTable().fireTableDataChanged();
         newInvoiceDialog.setVisible(false);
         newInvoiceDialog.dispose();
         newInvoiceDialog=null;
    }

    private void cancelCreation() {
        newInvoiceDialog.setVisible(false);
        newInvoiceDialog.dispose();
        newInvoiceDialog=null;
    }

    private void doneLineCreation() {
        String name=newLineDialog.getItemName().getText();
        String itemcount=newLineDialog.getCount().getText();
        String itemprice=newLineDialog.getPrice().getText();
        int count=Integer.parseInt(itemcount);
        double price=Double.parseDouble(itemprice);
        int index=frame.getInvoicesTable().getSelectedRow();
        if(index!=-1)
        {
        InvoiceDetails i=frame.getInvoice().get(index);
        LineDetails lineDetails=new LineDetails(count,price,name,i);
        i.getLine().add(lineDetails);
        InvoiceItemsTable invoiceItemsTable=(InvoiceItemsTable )frame.getInvoiceItemstable().getModel();
        invoiceItemsTable.fireTableDataChanged();
        frame.getInvoiceTable().fireTableDataChanged();
        }
        newLineDialog.setVisible(false);
        newLineDialog.dispose();
        newLineDialog=null;
    }

    private void cancelLineCreation() {
        newLineDialog.setVisible(false);
        newLineDialog.dispose();
        newLineDialog=null;
    }
    
}
