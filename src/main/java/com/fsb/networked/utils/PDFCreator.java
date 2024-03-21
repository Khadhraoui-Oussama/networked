package com.fsb.networked.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

public class PDFCreator {

    //private static String PATH = "C:\\Users\\khadh\\IdeaProjects\\networked\\src\\main\\resources\\com\\fsb\\networked\\PDFFiles\\";
    //private static String NAME = "pdfGenerated.pdf";
    public static void createPDF(String PATH,String NAME)
    {
        //create the pdf document
        PDDocument pdfDocument = new PDDocument();
        //create a blank page
        PDPage pdfPage = new PDPage(PDRectangle.A4);
        //add the page to the document
        pdfDocument.addPage(pdfPage);
        //save the pdf file
        try {
            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument,pdfPage);
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD),14);
            contentStream.beginText();
            contentStream.newLineAtOffset(20,750);
            contentStream.showText("Hello World !!");
            contentStream.endText();
            //close the contentStream
            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            pdfDocument.save(new File(PATH+NAME));
            //close the document
            pdfDocument.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
