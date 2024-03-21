package com.fsb.networked.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.fontbox.util.BoundingBox;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fsb.networked.utils.JSONParser.*;
import java.io.File;
import java.io.IOException;

public class PDFCreator {
    //color headline : #2C466F = rgb(44, 70, 111)
    //private static String PATH = "C:\\Users\\khadh\\IdeaProjects\\networked\\src\\main\\resources\\com\\fsb\\networked\\PDFFiles\\";
    //private static String NAME = "pdfGenerated.pdf";
    static PDPageContentStream contentStream;
    static float[] rgbValues = new float[] {44f / 255f, 70f / 255f, 111f / 255f};
    // Create a PDColor object with the RGB values
    static PDColor colorHeading = new PDColor(rgbValues, PDDeviceRGB.INSTANCE);
    static PDFont helveticaFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

    public static void createPDF(String PATH,String NAME)
    {

        //create the pdf document
        PDDocument pdfDocument = new PDDocument();
        //create a blank page
        PDPage pdfPage = new PDPage(PDRectangle.A4);

        //create backImg and pfpImg objects and fill them with each image
        PDImageXObject backImg;
        PDImageXObject pfpImg;
        PDImageXObject separatorImg;
        try {
            pfpImg = PDImageXObject.createFromFile(JSONParser.getValueFromJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON","signUpBasic","picture").toString().substring(6),pdfDocument);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            separatorImg = PDImageXObject.createFromFile("src/main/resources/images/pdf_icons/separator.png",pdfDocument);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            backImg = PDImageXObject.createFromFile("src/main/resources/images/pdf_icons/back_one.png",pdfDocument);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //add the page to the document
        pdfDocument.addPage(pdfPage);
        try {
            contentStream = new PDPageContentStream(pdfDocument,pdfPage);
            //draw the back image and the pfp picture
            contentStream.drawImage(backImg,0,0);
            contentStream.drawImage(pfpImg,25,pdfPage.getMediaBox().getHeight() - 170 ,150,150);

            addSingleLine(extractSimpleValue("signUpBasic","firstName"),40,pdfPage.getMediaBox().getHeight() - 210,helveticaFont,16,colorHeading);
            addSingleLine(extractSimpleValue("signUpBasic","lastName"),40,pdfPage.getMediaBox().getHeight() - 230 ,helveticaFont,16,colorHeading);
            contentStream.drawImage(separatorImg,0,pdfPage.getMediaBox().getHeight() - 260 );
            addSingleLine("Last/Current Job: ",30,pdfPage.getMediaBox().getHeight() - 290 ,helveticaFont,17,colorHeading);
            addSingleLine(extractArrayValueByIndex("signUpWork","position",-1) + " at:",20,pdfPage.getMediaBox().getHeight() - 310 ,helveticaFont,14,colorHeading);
            addSingleLine(extractArrayValueByIndex("signUpWork","company",-1),20,pdfPage.getMediaBox().getHeight() - 330 ,helveticaFont,14,colorHeading);
            contentStream.drawImage(separatorImg,0,pdfPage.getMediaBox().getHeight() - 360 );
            addSingleLine("Contact: ",30,pdfPage.getMediaBox().getHeight() - 390 ,helveticaFont,18,colorHeading);
            addSingleLine(extractSimpleValue("signUp","emailAddress") ,20,pdfPage.getMediaBox().getHeight() - 410 ,helveticaFont,14,colorHeading);
            contentStream.drawImage(separatorImg,0,pdfPage.getMediaBox().getHeight() - 440 );

           //skills portion
            addSingleLine("Core Skills: ",30,pdfPage.getMediaBox().getHeight() - 460 ,helveticaFont,18,colorHeading);
            System.out.println((JSONParser.extractArrayLength("signUpSkills")));

            for(int i = 0 ; i < (JSONParser.extractArrayLength("signUpSkills")) ; i++)
            {
                System.out.println("Title : " + extractArrayValueByIndex("signUpSkills","title",i));
                System.out.println("Tech : " + extractArrayValueByIndex("signUpSkills","technology",i));
                //\u2023 triangle obtained from sourceforge in helveticaBold
                addSingleLine("* " + extractArrayValueByIndex("signUpSkills","title",i) ,20,pdfPage.getMediaBox().getHeight() - (480 + i * 45) ,helveticaFont,16,colorHeading);
                addSingleLine(extractArrayValueByIndex("signUpSkills","technology",i) ,20,pdfPage.getMediaBox().getHeight() - (500 + i * 45) ,helveticaFont,14,colorHeading);
            }
           /* //work expreience
            addSingleLine("Carrer Summary: ",pdfPage.getMediaBox().getWidth() - 310,pdfPage.getMediaBox().getHeight() - 50 ,helveticaFont,26,colorHeading);
            System.out.println((JSONParser.extractArrayLength("signUpWork")));

            for(int i = 0 ; i < (JSONParser.extractArrayLength("signUpWork")) ; i++)
            {
                //\u2023 triangle obtained from sourceforge in helveticaBold
                addSingleLine("* " + extractArrayValueByIndex("signUpWork","startDate",i) ,20,pdfPage.getMediaBox().getHeight() - (480 + i * 45) ,helveticaFont,12,colorHeading);
                addSingleLine("* " + extractArrayValueByIndex("signUpWork","endDate",i) ,20,pdfPage.getMediaBox().getHeight() - (480 + i * 45) ,helveticaFont,12,colorHeading);
                addSingleLine(extractArrayValueByIndex("signUpSkills","technology",i) ,20,pdfPage.getMediaBox().getHeight() - (500 + i * 45) ,helveticaFont,14,colorHeading);
            }*/





            //close the contentStream
            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //backimage is 1920 by 200
        //save the pdf file
        try {
            pdfDocument.save(new File(PATH+NAME));
            //close the document
            pdfDocument.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addSingleLine(String line, float xP, float yP, PDFont font, float fontSize, PDColor fontColor) throws IOException
    {
        contentStream.beginText();
        contentStream.setFont(font,fontSize);
        contentStream.setNonStrokingColor(fontColor);
        contentStream.newLineAtOffset(xP,yP);
        contentStream.showText(line);
        contentStream.endText();
        contentStream.moveTo(0,0);
    }
    private static String extractSimpleValue(String parentField, String field)
    {
        String value = JSONParser.getValueFromJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON",parentField,field);
        return value;
    }
    public static String extractArrayValueByIndex(String parentField, String field,int index) {
        String filePath = "src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON";
        String fileContent = "";
        fileContent = JSONParser.getJSONFromFile(filePath);
        JSONObject item = null;
        JSONObject jsonObject = new JSONObject(fileContent);
        if (jsonObject.has(parentField)) {
            JSONArray jsonArray = jsonObject.getJSONArray(parentField);
            if (!jsonArray.isEmpty()) {
                if(index == -1)
                {
                    item = jsonArray.getJSONObject(jsonArray.length() - 1);
                    return item.optString(field, "");
                }
                else{
                    item = jsonArray.getJSONObject(index);
                    return item.optString(field, "");
                }
            }
        }
        return "";
    }
}