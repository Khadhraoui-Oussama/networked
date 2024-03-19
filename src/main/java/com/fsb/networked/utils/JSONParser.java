package com.fsb.networked.utils;

import org.json.JSONObject;

import java.io.*;

public class JSONParser {
    public static String getJSONFromFile(String filename) {
        String jsonText = "";
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(filename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    public static <T> T getValueFromJSONFile(String JSONFilePath, String parentField, String nestedField) {
        T value = null;
        try (RandomAccessFile file = new RandomAccessFile(JSONFilePath, "r")) {
            // Read the JSON content
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = file.readLine()) != null) {
                content.append(line);
            }
            // Parse the JSON string
            JSONObject obj = new JSONObject(content.toString());

            // Get the parent object
            JSONObject parentObject = obj.getJSONObject(parentField);

            // Get the value associated with the nested field
            value = (T) parentObject.get(nestedField);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


    //use generic type T because this function will allow any type in the valueToWrite
    // (mostly to accomodate the LocalDate type)
    public static <T> void writeToJSONFile(String JSONFilePath,String fieldParentObject, String fieldToChange, T valueToWrite)
    {
        //modify not rewrite
        //use RandomAccessFile to have a random access(not sequential) to the JSON file so that we can modify what we want without erasing the other data
        try (RandomAccessFile file = new RandomAccessFile(JSONFilePath, "rw")) {
            // Read the JSON content
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = file.readLine()) != null) {
                content.append(line);
            }
            // Parse the JSON string
            JSONObject obj = new JSONObject(content.toString());

            // Access the parent object in the JSON FILE
            JSONObject parentObject = obj.getJSONObject(fieldParentObject);

            //
            parentObject.put(fieldToChange,valueToWrite);
            // Write the modified JSON back to the file
            file.setLength(0);
            file.writeBytes(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void resetIndividualJSONFile()
    {
        //create each object and nest inside it any other objects if neccesary
        JSONObject jsonObject = new JSONObject();

        JSONObject signUpObject = new JSONObject();
        signUpObject.put("emailAddress", "");
        signUpObject.put("password", "");
        jsonObject.put("signUp", signUpObject);

        JSONObject signUpBasicObject = new JSONObject();
        signUpBasicObject.put("firstName", "");
        signUpBasicObject.put("lastName", "");
        signUpBasicObject.put("dob", "");
        signUpBasicObject.put("country", "");
        signUpBasicObject.put("address", "");
        signUpBasicObject.put("gender", "");
        signUpBasicObject.put("picture", "");
        jsonObject.put("signUpBasic", signUpBasicObject);

        JSONObject signUpSkillsObject = new JSONObject();
        jsonObject.put("signUpSkills", signUpSkillsObject);

        JSONObject signUpWorkObject = new JSONObject();
        jsonObject.put("signUpWork", signUpWorkObject);

        JSONObject signUpProjectsObject = new JSONObject();
        jsonObject.put("signUpProjects", signUpProjectsObject);

        JSONObject signUpVideoObject = new JSONObject();
        signUpVideoObject.put("videoPath", "");
        jsonObject.put("signUpVideo", signUpVideoObject);

        String filePath = "src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON";
        try (FileWriter writer = new FileWriter(filePath,false)) {
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void resetEntrepriseJSONFile() {
        //create each object and nest inside it any other objects if neccesary
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("nameEntreprise","");
        jsonObject.put("logoImage","");
        jsonObject.put("sector","");
        jsonObject.put("companySize","");
        jsonObject.put("foundingYear","");
        jsonObject.put("founder","");
        jsonObject.put("description","");
        jsonObject.put("localisation","");
        jsonObject.put("webSite","");

        String filePath = "src/main/resources/com/fsb/networked/JSON_files/Entreprise.JSON";
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}