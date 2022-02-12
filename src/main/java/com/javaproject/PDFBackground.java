package com.javaproject;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class PDFBackground extends PdfPageEventHelper {

    public PDFBackground(){}

    @Override
    public void onEndPage(PdfWriter writer, Document document){
        try {
            Image background = Image.getInstance("/home/root123/IdeaProjects/JavaProject/src/main/resources/com/javaproject/blue-line-vector-background-vector-id1148344310.jpeg");
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            writer.getDirectContentUnder().addImage(background,width,0,0,height,0,0);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
