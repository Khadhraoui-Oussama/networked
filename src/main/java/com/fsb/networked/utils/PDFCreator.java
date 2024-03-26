package com.fsb.networked.utils;

import org.apache.commons.lang3.text.WordUtils;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class PDFCreator {
    static PDPageContentStream contentStream;
    static float[] rgbValues1 = new float[] {44f / 255f, 70f / 255f, 111f / 255f};
    static float[] rgbValues2 = new float[] {0f / 255f, 88f / 255f, 66f / 255f};
    // Create a PDColor object with the RGB values
    static PDColor colorHeading = new PDColor(rgbValues1, PDDeviceRGB.INSTANCE);
    static PDColor colorSecondary = new PDColor(rgbValues2, PDDeviceRGB.INSTANCE);
    static PDFont helveticaFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
    static PDFont italicFont = new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD_ITALIC);
    private static final int currentPageNumber = 1; // global variable to keep track of the current page number
    private static int yOffset = 750; // initial yoOffset
    static PDImageXObject backImg;
    //create a blank page
    static PDPage pdfPage = new PDPage(PDRectangle.A4);
    public static File createPDF(String PATH,String NAME)
    {
        //create the pdf document
        PDDocument pdfDocument = new PDDocument();
        //create backImg and pfpImg objects and fill them with each image
        PDImageXObject pfpImg;
        PDImageXObject separatorImg;
        try {
            pfpImg = PDImageXObject.createFromFile(JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","picture").toString().substring(6),pdfDocument);
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

            //SOME HARCODED TODO DATA REFACTOR IF TIME ALLOWS
            //draw the back image and the pfp picture
            contentStream.drawImage(backImg,0,0);
            contentStream.drawImage(pfpImg,25,pdfPage.getMediaBox().getHeight() - 170 ,150,150);

            addSingleLine(extractSimpleValue("signUpBasic","firstName"),40,pdfPage.getMediaBox().getHeight() - 210,helveticaFont,16,colorHeading);
            addSingleLine(extractSimpleValue("signUpBasic","lastName"),40,pdfPage.getMediaBox().getHeight() - 230 ,helveticaFont,16,colorHeading);
            contentStream.drawImage(separatorImg,0,pdfPage.getMediaBox().getHeight() - 260 );
            addSingleLine("Current/Last Job: ",30,pdfPage.getMediaBox().getHeight() - 290 ,helveticaFont,17,colorSecondary);
            addSingleLine(extractArrayValueByIndex("signUpWork","position",-1) + " at:",20,pdfPage.getMediaBox().getHeight() - 310 ,helveticaFont,14,colorHeading);
            addSingleLine(extractArrayValueByIndex("signUpWork","company",-1),20,pdfPage.getMediaBox().getHeight() - 330 ,helveticaFont,14,colorHeading);
            contentStream.drawImage(separatorImg,0,pdfPage.getMediaBox().getHeight() - 360 );
            addSingleLine("Contact: ",30,pdfPage.getMediaBox().getHeight() - 390 ,helveticaFont,18,colorSecondary);
            addSingleLine(extractSimpleValue("signUp","emailAddress") ,20,pdfPage.getMediaBox().getHeight() - 410 ,helveticaFont,14,colorHeading);
            contentStream.drawImage(separatorImg,0,pdfPage.getMediaBox().getHeight() - 440 );
            //END HARDCODED

            addSkillsContentToPDF(new JSONObject(JSONParser.getJSONFromFile(ImportantFileReferences.INDIVIDUALJSON)), pdfDocument, pdfPage);
            addWorkContentToPDF(new JSONObject(JSONParser.getJSONFromFile(ImportantFileReferences.INDIVIDUALJSON)), pdfDocument, pdfPage);
            addEducationContentToPDF(new JSONObject(JSONParser.getJSONFromFile(ImportantFileReferences.INDIVIDUALJSON)), pdfDocument, pdfPage);
            addProjectContentToPDF(new JSONObject(JSONParser.getJSONFromFile(ImportantFileReferences.INDIVIDUALJSON)), pdfDocument, pdfPage);

            //close the contentStream
            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //save the pdf file
        try {
            File pdfFile = new File(PATH+NAME);
            pdfDocument.save(pdfFile);
            //close the document
            pdfDocument.close();
            //return the pdfFile
            return pdfFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String extractSimpleValue(String parentField, String field) {
        String value = JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,parentField,field);
        return value;
    }
    public static String extractArrayValueByIndex(String parentField, String field,int index) {
        String filePath = ImportantFileReferences.INDIVIDUALJSON;
        String fileContent;
        fileContent = JSONParser.getJSONFromFile(filePath);
        JSONObject item;
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

    private static void addEducationContentToPDF(JSONObject jsonObject,PDDocument pdfDocument ,PDPage page) throws IOException {
        yOffset -= 30;
        // Iterate over the "signUpEducation" array and add content to the PDF
        JSONArray signUpEducationArray = jsonObject.getJSONArray("signUpEducation");
        addSingleLine("Education : ",pdfPage.getMediaBox().getWidth() - 360,yOffset ,helveticaFont,26,colorSecondary);
        yOffset -= 30;
        contentStream.setNonStrokingColor(colorHeading);

        for (int i = 0; i < signUpEducationArray.length(); i++) {
            JSONObject educationObject = signUpEducationArray.getJSONObject(i);
            String diploma = educationObject.getString("diploma");
            String institute = educationObject.getString("institute");
            // Set font for diploma
            contentStream.setFont(italicFont, 14);

            // Calculate width of the diploma text
            float diplomaWidth = italicFont.getStringWidth(diploma) / 1000f * 14f;

            // Add diploma name
            contentStream.beginText();
            contentStream.newLineAtOffset(page.getMediaBox().getWidth() - 360, yOffset);
            contentStream.showText(diploma);
            contentStream.endText();

            // Set font for institute
            contentStream.setFont(helveticaFont, 12);

            // Calculate starting position for institute text
            float startX = page.getMediaBox().getWidth() - 360 + diplomaWidth; // Add some padding

            // Add institute name
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, yOffset);
            contentStream.showText(" || " + institute);
            contentStream.endText();

            yOffset -= 20;
            // Check if content exceeds page height, add new page if needed
            if (yOffset < 80) {
                page = new PDPage(PDRectangle.A4);
                pdfDocument.addPage(page);
                contentStream.close();
                contentStream = new PDPageContentStream(pdfDocument, page);
                contentStream.drawImage(backImg,0,0);
                yOffset = 750;
            }
        }
    }
    private static void addWorkContentToPDF(JSONObject jsonObject,PDDocument pdfDocument,PDPage page) throws IOException {
        addSingleLine("Carrer Summary: ",pdfPage.getMediaBox().getWidth() - 360,pdfPage.getMediaBox().getHeight() - 50 ,helveticaFont,26,colorSecondary);

        // Iterate over the "signUpEducation" array and add content to the PDF
        JSONArray signUpWorkArray = jsonObject.getJSONArray("signUpWork");
        for (int i = 0; i < signUpWorkArray.length(); i++) {
            JSONObject workObject = signUpWorkArray.getJSONObject(i);
            addSingleLine(workObject.getString("startDate") + " - " + workObject.getString("endDate"), page.getMediaBox().getWidth() - 360, yOffset, italicFont, 10, colorHeading);
            yOffset -= 20;
            addSingleLine(workObject.getString("company"), page.getMediaBox().getWidth() - 360, yOffset, helveticaFont, 16, colorHeading);
            yOffset -= 20;
            addSingleLine(workObject.getString("description"), page.getMediaBox().getWidth() - 360, yOffset, helveticaFont, 14, colorHeading);
            yOffset -= 40;
            // Check if content exceeds page height, add new page if needed
            if (yOffset < 80) {
                page = new PDPage(PDRectangle.A4);
                pdfDocument.addPage(page);
                contentStream.close();
                contentStream = new PDPageContentStream(pdfDocument, page);
                contentStream.drawImage(backImg,0,0);
                yOffset = 750;
            }
        }
    }
    private static void addProjectContentToPDF(JSONObject jsonObject,PDDocument pdfDocument,PDPage page) throws IOException {
        yOffset -= 30;
        addSingleLine("Personnal Projects: ",pdfPage.getMediaBox().getWidth() - 360,yOffset,helveticaFont,26,colorSecondary);
        yOffset -= 30;
        // Iterate over the "signUpEducation" array and add content to the PDF
        JSONArray signUpProjectsArray = jsonObject.getJSONArray("signUpProjects");
        for (int i = 0; i < signUpProjectsArray.length(); i++) {
            JSONObject projectObject = signUpProjectsArray.getJSONObject(i);
            addSingleLine(projectObject.getString("title"), page.getMediaBox().getWidth() - 360, yOffset, helveticaFont, 16, colorHeading);
            yOffset -= 20;
            addSingleLine(projectObject.getString("technology"), page.getMediaBox().getWidth() - 360, yOffset, helveticaFont, 12, colorHeading);
            yOffset -= 20;
            addSingleLine(projectObject.getString("description"), page.getMediaBox().getWidth() - 360, yOffset, helveticaFont, 12, colorHeading);
            yOffset -= 40;
            // Check if content exceeds page height, add new page if needed
            if (yOffset < 80) {
                page = new PDPage(PDRectangle.A4);
                pdfDocument.addPage(page);
                contentStream.close();
                contentStream = new PDPageContentStream(pdfDocument, page);
                contentStream.drawImage(backImg,0,0);
                yOffset = 750;
            }
        }
    }
    private static void addSkillsContentToPDF(JSONObject jsonObject,PDDocument pdfDocument,PDPage page) throws IOException {
        addSingleLine("Core Skills: ",30,pdfPage.getMediaBox().getHeight() - 460 ,helveticaFont,20,colorSecondary);
        contentStream.setNonStrokingColor(colorHeading);
        // Iterate over the "signUpEducation" array and add content to the PDF
        JSONArray signUpSkillsArray = jsonObject.getJSONArray("signUpSkills");
        yOffset = (int) (pdfPage.getMediaBox().getHeight() - 490 );
        //max of 4 skills so the layout doenst break, i know its a hacky solution but iris whatiris
        int max = signUpSkillsArray.length() < 4 ? signUpSkillsArray.length() : 4;
        for (int i = 0; i < max; i++) {
            JSONObject workObject = signUpSkillsArray.getJSONObject(i);
            addSingleLine("* " + workObject.getString("title") , 20, yOffset, helveticaFont, 14, colorHeading);
            yOffset -= 20;
            addSingleLine(workObject.getString("technology"), 20, yOffset, helveticaFont, 12, colorHeading);
            yOffset -= 20;
            addSingleLine(workObject.getString("level"), 20, yOffset, helveticaFont, 12, colorHeading);
            yOffset -= 20;
        }
        yOffset = 750;
    }
    private static void addSingleLine(String line, float xP, float yP, PDFont font, float fontSize, PDColor fontColor) throws IOException {
        wrapText(line,contentStream,xP,yP,font,fontSize,fontColor);
        contentStream.moveTo(0,0);
    }
    static String[] storageOfWrappedText = null;
    //this wraps the text to wraplength of under 45 characters and then splits them on a newline character
    private static void wrapText(String textToWrap, PDPageContentStream contentStream, float xOffset, float yOffset, PDFont font, float fontSize, PDColor fontColor) throws IOException {
        String text;
        storageOfWrappedText = WordUtils.wrap(textToWrap, 45).split("\\r?\\n");
        contentStream.setFont(font,fontSize);
        contentStream.setNonStrokingColor(fontColor);
        for (int i=0; i< storageOfWrappedText.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(xOffset,yOffset - i*15);
            text = storageOfWrappedText[i];
            contentStream.showText(text);
            contentStream.endText();
        }
        //reset the storage
        storageOfWrappedText = null;
    }
}