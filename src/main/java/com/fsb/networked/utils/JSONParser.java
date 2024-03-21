package com.fsb.networked.utils;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        signUpBasicObject.put("gender", "Male");

        String imagePath = "/images/male_avatar.png";
        signUpBasicObject.put("picture", FilePaths.getImagePath(imagePath));

        jsonObject.put("signUpBasic", signUpBasicObject);

        JSONArray signUpSkillsArray = new JSONArray();
        JSONArray signUpworkArray = new JSONArray();
        JSONArray signUpProjectArray = new JSONArray();
        JSONArray signUpEducationArray = new JSONArray();

        jsonObject.put("signUpSkills", signUpSkillsArray);

        jsonObject.put("signUpWork", signUpworkArray);

        jsonObject.put("signUpProjects", signUpProjectArray);

        jsonObject.put("signUpEducation", signUpEducationArray);

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
    public static void updateEducationJSONArray(JSONArray educationArray) {
        try {
            // Read the existing JSON content
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")));
            JSONObject jsonObject = new JSONObject(content);

            // Update the skills array in the JSON object
            jsonObject.put("signUpEducation", educationArray);

            // Write the modified JSON back to the file
            try (FileWriter file = new FileWriter("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")) {
                file.write(jsonObject.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateSkillsJSONArray(JSONArray skillsArray) {
        try {
            // Read the existing JSON content
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")));
            JSONObject jsonObject = new JSONObject(content);

            // Update the skills array in the JSON object
            jsonObject.put("signUpSkills", skillsArray);

            // Write the modified JSON back to the file
            try (FileWriter file = new FileWriter("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")) {
                file.write(jsonObject.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateWorkJSONArray(JSONArray workArray) {
        try {
            // Read the existing JSON content
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")));
            JSONObject jsonObject = new JSONObject(content);

            // Update the skills array in the JSON object
            jsonObject.put("signUpWork", workArray);

            // Write the modified JSON back to the file
            try (FileWriter file = new FileWriter("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")) {
                file.write(jsonObject.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateProjectsJSONArray(JSONArray projectsArray) {
        try {
            // Read the existing JSON content
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")));
            JSONObject jsonObject = new JSONObject(content);

            // Update the skills array in the JSON object
            jsonObject.put("signUpProjects", projectsArray);

            // Write the modified JSON back to the file
            try (FileWriter file = new FileWriter("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON")) {
                file.write(jsonObject.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONArray getSkillsJSONArray(String filePath) {
        try {
            // Read the JSON content from the file
            String content = Files.readString(Paths.get(filePath));

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(content);

            // Get the skills JSON array
            return jsonObject.getJSONArray("signUpSkills");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONArray(); // Return an empty array if an error occurs
        }
    }
    public static JSONArray getProjectsJSONArray(String filePath) {
        try {
            // Read the JSON content from the file
            String content = Files.readString(Paths.get(filePath));

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(content);

            // Get the skills JSON array
            return jsonObject.getJSONArray("signUpProjects");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONArray(); // Return an empty array if an error occurs
        }
    }
    public static JSONArray getJobsJSONArray(String filePath) {
        try {
            // Read the JSON content from the file
            String content = Files.readString(Paths.get(filePath));

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(content);

            // Get the skills JSON array
            return jsonObject.getJSONArray("signUpWork");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONArray(); // Return an empty array if an error occurs
        }
    }

    public static JSONArray getEducationsJSONArray(String filePath) {
            try {
                // Read the JSON content from the file
                String content = Files.readString(Paths.get(filePath));

                // Parse the JSON string
                JSONObject jsonObject = new JSONObject(content);

                // Get the skills JSON array
                return jsonObject.getJSONArray("signUpEducation");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return new JSONArray(); // Return an empty array if an error occurs
            }
        }


    public static int extractArrayLength(String parentField) {
        String filePath = "src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON";
        String fileContent = JSONParser.getJSONFromFile(filePath);
        JSONObject jsonObject = new JSONObject(fileContent);

        if (jsonObject.has(parentField)) {
            JSONArray jsonArray = jsonObject.getJSONArray(parentField);
            return jsonArray.length();
        }
        return 0;
    }

        //this is only for debug purposes TODO REMOVEIT
            public static void populateJSONFile(String filePath) {
                JSONObject jsonObject = new JSONObject();

                // signUpVideo
                JSONObject signUpVideoObject = new JSONObject();
                signUpVideoObject.put("videoPath", "/C:/Users/khadh/Videos/Microworks/MicroWorks 2024.01.13 - 15.18.12.60.DVR.mp4");
                jsonObject.put("signUpVideo", signUpVideoObject);

                // signUpBasic
                JSONObject signUpBasicObject = new JSONObject();
                signUpBasicObject.put("firstName", "John");
                signUpBasicObject.put("lastName", "Doe");
                signUpBasicObject.put("country", "Tunisia");
                signUpBasicObject.put("address", "10 Jarzouna");
                signUpBasicObject.put("gender", "Male");
                signUpBasicObject.put("dob", "2003-10-04");
                signUpBasicObject.put("picture", "file:/C:/Users/khadh/Pictures/417177134_7290152844381720_7370021647571908018_n.jpg");
                jsonObject.put("signUpBasic", signUpBasicObject);

                // signUpEducation
                JSONArray signUpEducationArray = new JSONArray();
                JSONObject education1 = new JSONObject();
                education1.put("endDate", "2024-03-31");
                education1.put("description", "First of my class");
                education1.put("institute", "FSB");
                education1.put("diploma", "MBA");
                education1.put("type", "Finance");
                education1.put("startDate", "2024-02-27");
                signUpEducationArray.put(education1);

                JSONObject education2 = new JSONObject();
                education2.put("endDate", "2024-03-30");
                education2.put("description", "Barely made it");
                education2.put("institute", "FSS");
                education2.put("diploma", "Masters");
                education2.put("type", "Itt");
                education2.put("startDate", "2021-03-10");
                signUpEducationArray.put(education2);

                jsonObject.put("signUpEducation", signUpEducationArray);

                // signUpProjects
                JSONArray signUpProjectsArray = new JSONArray();
                JSONObject project1 = new JSONObject();
                project1.put("link", "https://alpha1.project");
                project1.put("description", "ProjectAlpha is for military purposes cant disclose any more info");
                project1.put("technology", "Web dev");
                project1.put("title", "ProjectAlpha");
                signUpProjectsArray.put(project1);

                JSONObject project2 = new JSONObject();
                project2.put("link", "https://redacted.military");
                project2.put("description", "https://redacted.military [REDACTED]");
                project2.put("technology", "Radar Technology");
                project2.put("title", "ProjectGamma");
                signUpProjectsArray.put(project2);

                jsonObject.put("signUpProjects", signUpProjectsArray);

                // signUpWork
                JSONArray signUpWorkArray = new JSONArray();
                JSONObject work1 = new JSONObject();
                work1.put("endDate", "2023-03-23");
                work1.put("description", "Quality insurance");
                work1.put("company", "Boga Cidre");
                work1.put("position", "Manager");
                work1.put("type", "Manager");
                work1.put("startDate", "2018-03-07");
                signUpWorkArray.put(work1);

                JSONObject work2 = new JSONObject();
                work2.put("endDate", "2024-03-14");
                work2.put("description", "Got Fired for testing the product");
                work2.put("company", "FlameThrower Inc");
                work2.put("position", "President");
                work2.put("type", "Managerial");
                work2.put("startDate", "2024-03-14");
                signUpWorkArray.put(work2);

                jsonObject.put("signUpWork", signUpWorkArray);

                // signUp
                JSONObject signUpObject = new JSONObject();
                signUpObject.put("emailAddress", "example@xyz.com");
                signUpObject.put("password", "Azerty123!");
                jsonObject.put("signUp", signUpObject);

                // signUpSkills
                JSONArray signUpSkillsArray = new JSONArray();
                JSONObject skill1 = new JSONObject();
                skill1.put("level", "Advanced");
                skill1.put("description", "React hooks mastery");
                skill1.put("technology", "Web dev");
                skill1.put("title", "React");
                signUpSkillsArray.put(skill1);

                JSONObject skill2 = new JSONObject();
                skill2.put("level", "Intermediate");
                skill2.put("description", "JS mastery");
                skill2.put("technology", "Web dev");
                skill2.put("title", "JavaScript");
                signUpSkillsArray.put(skill2);

                jsonObject.put("signUpSkills", signUpSkillsArray);

                try (FileWriter writer = new FileWriter(filePath,false)) {
                    writer.write(jsonObject.toString(4)); // Indent with 4 spaces for better readability
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
}