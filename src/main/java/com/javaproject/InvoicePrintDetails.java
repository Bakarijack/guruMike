package com.javaproject;

import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InvoicePrintDetails extends InvoiceDetails{
    private String senderEmail;
    private String receiverEmail;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String location;
    private String email;
    private ArrayList<String> list = new ArrayList<>();

    public InvoicePrintDetails(){}

    public InvoicePrintDetails(String orderId){
        setOrderCode(orderId);
    }

    public InvoicePrintDetails(String firstName,String secondName,String phoneNumber,String location,String email){
        this.firstName=firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.email = email;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public PdfPCell getCell(String text, int alignment, Font font){
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(),font));

        BaseColor myColor = WebColors.getRGBColor("#87cefa");
       //set the cell alignment
        cell.setHorizontalAlignment(alignment);
        cell.setBackgroundColor(myColor);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingLeft(0);
        cell.setPaddingRight(0);
        cell.setBorder(PdfPCell.NO_BORDER);
        //cell.setBorderColor(BaseColor.WHITE);
        return cell;
    }
    public PdfPCell getCellContent(String text, int alignment, Font font){
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(),font));

        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingLeft(0);
        cell.setPaddingRight(0);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public void printPDF(){
        Document document = new Document();

        try{
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("Invoice.pdf"));
            writer.setPageEvent(new PDFBackground());
            document.open();

            //for Heading
            Font font1 = new Font(Font.FontFamily.COURIER,14f,Font.BOLD);
            font1.setColor(BaseColor.BLUE);
            Paragraph heading1 = new Paragraph("MBR Investment LTD",font1);
            document.add(heading1);
            
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN,11f,Font.ITALIC);
            font1.setColor(BaseColor.BLACK);
            Paragraph heading2 = new Paragraph("123-456 Harvest Line",font2);
            document.add(heading2);
            Paragraph heading3 = new Paragraph("Kisii - Town",font2);
            heading3.setSpacingAfter(60);
            document.add(heading3);

            //Subheading info
            Chunk glue = new Chunk(new VerticalPositionMark());
            Font font3 = new Font(Font.FontFamily.UNDEFINED,12f,Font.NORMAL);
            font3.setColor(BaseColor.BLUE);
            Paragraph subheading1 = new Paragraph("Bill To",font3);
            subheading1.setSpacingAfter(5);
            subheading1.add(new Chunk(glue));
            subheading1.add("Invoice #  ");
            subheading1.add(new Chunk(glue));
            subheading1.add("Invoice Date ");
            subheading1.add(new Chunk(glue));
            subheading1.add("Due Date ");
            document.add(subheading1);

            //subheading content
            Paragraph p1 =new Paragraph("Bakari Kilu\n"+"0797544060\n"+"Mombasa",font2);
            p1.setSpacingAfter(5);
            p1.add(new Chunk(glue));
            p1.add(" 2 ");
            p1.add(new Chunk(glue));
            p1.add("  2022/02/1 ");
            p1.add(new Chunk(glue));
            p1.add("  2022/02/12 ");
            p1.setSpacingAfter(50);
            document.add(p1);


            Font font5 = new Font(Font.FontFamily.TIMES_ROMAN,8f,Font.BOLD);
            font1.setColor(BaseColor.WHITE);
            //table for invoice details
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(90);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(getCell("Tray\n"+"Qt",PdfPCell.ALIGN_RIGHT,font5));
            table.addCell(getCell("Eggs\n"+"Qt",PdfPCell.ALIGN_LEFT,font5));
            table.addCell(getCell("Product",PdfPCell.ALIGN_LEFT,font5));
            table.addCell(getCell("Tray\n"+"Unit Price",PdfPCell.ALIGN_LEFT,font5));
            table.addCell(getCell("Eggs\n"+"Unit Price",PdfPCell.ALIGN_LEFT,font5));
            table.addCell(getCell("Amount",PdfPCell.ALIGN_RIGHT,font5));

            //adding cell content
            //ArrayList<String> list = new ArrayList<>();
            for (int i = 0 ;i< 6;i++){

            }
            document.add(table);

            //set Attribute
            document.addAuthor("Bakari");
            document.addCreationDate();
            document.addCreator("Kilu");
            document.addTitle("My first Pdf");
            document.addSubject("I love Java");

            document.close();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //get the order details


}
