package com.fsb.networked.utils;

import com.fsb.networked.dto.EducationDTO;
import com.fsb.networked.dto.ProjectDTO;
import com.fsb.networked.dto.SkillDTO;
import com.fsb.networked.dto.WorkDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONParser {
    public static String getJSONFromFile(String filename) {
        String jsonText = "";
        StringBuilder stringBuilder = new StringBuilder(jsonText);
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(filename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
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
        signUpBasicObject.put("picture", FileLoader.getImagePath(imagePath));

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

        String filePath = ImportantFileReferences.INDIVIDUALJSON;
        try (FileWriter writer = new FileWriter(filePath,false)) {
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void resetEntrepriseJSONFile() {
        //create each object and nest inside it any other objects if neccesary
        JSONObject jsonObject = new JSONObject();
        JSONObject signUpEntrepriseObject = new JSONObject();
        signUpEntrepriseObject.put("nameEntreprise","");
        signUpEntrepriseObject.put("logoImage","");
        signUpEntrepriseObject.put("sector","");
        signUpEntrepriseObject.put("companySize","");
        signUpEntrepriseObject.put("foundingYear","");
        signUpEntrepriseObject.put("founder","");
        signUpEntrepriseObject.put("description","");
        signUpEntrepriseObject.put("localisation","");
        signUpEntrepriseObject.put("webSite","");
        jsonObject.put("signUpEntreprise",signUpEntrepriseObject);

        JSONObject signUpBasicObject = new JSONObject();
        signUpBasicObject.put("email","");
        signUpBasicObject.put("password","");
        jsonObject.put("signUp",signUpBasicObject);
        String filePath = ImportantFileReferences.ENTREPRISEJSON;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateEducationJSONArray(JSONArray educationArray) {
        try {
            // Read the existing JSON content
            String content = new String(Files.readAllBytes(Paths.get(ImportantFileReferences.INDIVIDUALJSON)));
            JSONObject jsonObject = new JSONObject(content);

            // Update the skills array in the JSON object
            jsonObject.put("signUpEducation", educationArray);

            // Write the modified JSON back to the file
            try (FileWriter file = new FileWriter(ImportantFileReferences.INDIVIDUALJSON)) {
                file.write(jsonObject.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO REFACTOR CODE
    public static void updateJSONArray(JSONArray jsonArray,String parentField){
        try {
            // Read the existing JSON content
            String content = new String(Files.readAllBytes(Paths.get(ImportantFileReferences.INDIVIDUALJSON)));
            JSONObject jsonObject = new JSONObject(content);

            // Update the skills array in the JSON object
            jsonObject.put(parentField, jsonArray);

            // Write the modified JSON back to the file
            try (FileWriter file = new FileWriter(ImportantFileReferences.INDIVIDUALJSON)) {
                file.write(jsonObject.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getJSONArrayFromJSONFile(String filePath,String parentKey) {
        try {
            // Read the JSON content from the file
            String content = Files.readString(Paths.get(filePath));

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(content);

            // Get the skills JSON array
            return jsonObject.getJSONArray(parentKey);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONArray(); // Return an empty array if an error occurs
        }
    }
    public static int extractArrayLength(String parentField) {
        String filePath = ImportantFileReferences.INDIVIDUALJSON;
        String fileContent = JSONParser.getJSONFromFile(filePath);
        JSONObject jsonObject = new JSONObject(fileContent);

        if (jsonObject.has(parentField)) {
            JSONArray jsonArray = jsonObject.getJSONArray(parentField);
            return jsonArray.length();
        }
        return 0;
    }
    //this is only for debug purposes TODO REMOVE IT when ready to push to prod
    public static void populateJSONFile(String filePath) {
            JSONObject jsonObject = new JSONObject();

            // signUpVideo
            JSONObject signUpVideoObject = new JSONObject();
            signUpVideoObject.put("videoPath", "file:/C:/Users/khadh/Videos/Microworks/MicroWorks 2024.01.13 - 15.18.12.60.DVR.mp4");
            jsonObject.put("signUpVideo", signUpVideoObject);

            // signUpBasic
            JSONObject signUpBasicObject = new JSONObject();
            signUpBasicObject.put("firstName", "John");
            signUpBasicObject.put("lastName", "Doe");
            signUpBasicObject.put("country", "Tunisia");
            signUpBasicObject.put("address", "10 Jarzouna");
            signUpBasicObject.put("gender", "Male");
            signUpBasicObject.put("dob", "2003-10-04");
            String picture = FileLoader.getImagePath("/images/male_avatar.png");
            signUpBasicObject.put("picture", "file:/C:/Users/khadh/IdeaProjects/networked/src/main/resources/images/male_avatar.png");
            jsonObject.put("signUpBasic", signUpBasicObject);

            // signUpEducation
            JSONArray signUpEducationArray = new JSONArray();
            for (int i = 1; i <= 20; i++) {
                JSONObject education = new JSONObject();
                education.put("endDate", "2024-03-31");
                education.put("description", "Example " + i);
                education.put("institute", "Institute " + i);
                education.put("diploma", "Diploma " + i);
                education.put("type", "Type " + i);
                education.put("startDate", "2024-02-27");
                signUpEducationArray.put(education);
            }
            jsonObject.put("signUpEducation", signUpEducationArray);

            // signUpProjects
            JSONArray signUpProjectsArray = new JSONArray();
            for (int i = 1; i <= 20; i++) {
                JSONObject project = new JSONObject();
                project.put("link", "https://project" + i + ".example");
                project.put("description", "Project " + i + " description");
                project.put("technology", "Technology " + i);
                project.put("title", "Project " + i);
                signUpProjectsArray.put(project);
            }
            jsonObject.put("signUpProjects", signUpProjectsArray);

            // signUpWork
            JSONArray signUpWorkArray = new JSONArray();
            for (int i = 1; i <= 20; i++) {
                JSONObject work = new JSONObject();
                work.put("endDate", "2023-03-23");
                work.put("description", "Work " + i + " description");
                work.put("company", "Company " + i);
                work.put("position", "Position " + i);
                work.put("type", "Type " + i);
                work.put("startDate", "2018-03-07");
                signUpWorkArray.put(work);
            }
            jsonObject.put("signUpWork", signUpWorkArray);

            // signUp
            JSONObject signUpObject = new JSONObject();
            signUpObject.put("emailAddress", "example@xyz.com");
            signUpObject.put("password", "Azerty123!");
            jsonObject.put("signUp", signUpObject);

            // signUpSkills
            JSONArray signUpSkillsArray = new JSONArray();
            for (int i = 1; i <= 20; i++) {
                JSONObject skill = new JSONObject();
                skill.put("level", "Level " + i);
                skill.put("description", "Skill " + i + " description");
                skill.put("technology", "Technology " + i);
                skill.put("title", "Skill " + i);
                signUpSkillsArray.put(skill);
            }
            jsonObject.put("signUpSkills", signUpSkillsArray);

            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(jsonObject.toString(4)); // Indent with 4 spaces for better readability
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static EducationDTO JSONObjectToEducationDTO(JSONObject jsonObject)
    {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setDiploma((String) jsonObject.get("diploma"));
        educationDTO.setInstitute((String) jsonObject.get("institute"));
        educationDTO.setType((String) jsonObject.get("type"));
        educationDTO.setDescription((String) jsonObject.get("description"));
        educationDTO.setStartDate(Conversions.stringtoLocalDate((String) jsonObject.get("startDate")));
        educationDTO.setEndDate(Conversions.stringtoLocalDate((String) jsonObject.get("endDate")));
        return educationDTO;
    }
    public static WorkDTO JSONObjectToWorkDTO(JSONObject jsonObject)
    {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setPosition((String) jsonObject.get("position"));
        workDTO.setCompany((String) jsonObject.get("company"));
        workDTO.setType((String) jsonObject.get("type"));
        workDTO.setDescription((String) jsonObject.get("description"));
        workDTO.setStartDate(Conversions.stringtoLocalDate((String) jsonObject.get("startDate")));
        workDTO.setEndDate(Conversions.stringtoLocalDate((String) jsonObject.get("endDate")));
        return workDTO;
    }
    public static SkillDTO JSONObjectToSkillDTO(JSONObject jsonObject)
    {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setTitle((String) jsonObject.get("title"));
        skillDTO.setTechnology((String) jsonObject.get("technology"));
        skillDTO.setLevel((String) jsonObject.get("level"));
        skillDTO.setDescription((String) jsonObject.get("description"));
        return skillDTO;
    }
    public static ProjectDTO JSONObjectToProjectDTO(JSONObject jsonObject) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTitle((String) jsonObject.get("title"));
        projectDTO.setTechnology((String) jsonObject.get("technology"));
        projectDTO.setLink((String) jsonObject.get("link"));
        projectDTO.setDescription((String) jsonObject.get("description"));
        return projectDTO;
    }
    public static void populateRealDataJSONFile(String filePath) {
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
        signUpBasicObject.put("picture", "");
        jsonObject.put("signUpBasic", signUpBasicObject);

        // signUpEducation
        JSONArray signUpEducationArray = new JSONArray();

        // Education Item 1
        JSONObject education1 = new JSONObject();
        education1.put("endDate", "2024-03-31");
        education1.put("description", "Bachelor's in Computer Science");
        education1.put("institute", "University of Tunis");
        education1.put("diploma", "Bachelor of Science");
        education1.put("type", "Undergraduate");
        education1.put("startDate", "2020-09-01");
        signUpEducationArray.put(education1);

        // Education Item 2
        JSONObject education2 = new JSONObject();
        education2.put("endDate", "2022-06-30");
        education2.put("description", "Master's in Data Science");
        education2.put("institute", "Stanford University");
        education2.put("diploma", "Master of Science");
        education2.put("type", "Graduate");
        education2.put("startDate", "2020-09-01");
        signUpEducationArray.put(education2);

        // Education Item 3
        JSONObject education3 = new JSONObject();
        education3.put("endDate", "2019-06-30");
        education3.put("description", "High School Diploma");
        education3.put("institute", "International School of Tunis");
        education3.put("diploma", "High School Diploma");
        education3.put("type", "High School");
        education3.put("startDate", "2015-09-01");
        signUpEducationArray.put(education3);

        // Education Item 4
        JSONObject education4 = new JSONObject();
        education4.put("endDate", "2015-06-30");
        education4.put("description", "Middle School Certificate");
        education4.put("institute", "Private Middle School");
        education4.put("diploma", "Middle School Certificate");
        education4.put("type", "Middle School");
        education4.put("startDate", "2012-09-01");
        signUpEducationArray.put(education4);

        // Education Item 5
        JSONObject education5 = new JSONObject();
        education5.put("endDate", "2012-06-30");
        education5.put("description", "Primary School Certificate");
        education5.put("institute", "Public Primary School");
        education5.put("diploma", "Primary School Certificate");
        education5.put("type", "Primary School");
        education5.put("startDate", "2006-09-01");
        signUpEducationArray.put(education5);

        jsonObject.put("signUpEducation", signUpEducationArray);

        // signUpProjects
        JSONArray signUpProjectsArray = new JSONArray();

        // Project 1
        JSONObject project1 = new JSONObject();
        project1.put("link", "https://github.com/user/project1");
        project1.put("description", "Developed a web-based chat application using Node.js and WebSocket.");
        project1.put("technology", "Node.js, WebSocket");
        project1.put("title", "Real-time Chat Application");
        signUpProjectsArray.put(project1);

        // Project 2
        JSONObject project2 = new JSONObject();
        project2.put("link", "https://github.com/user/project2");
        project2.put("description", "Built a mobile app for task management using React Native.");
        project2.put("technology", "React Native");
        project2.put("title", "Task Manager App");
        signUpProjectsArray.put(project2);

        // Project 3
        JSONObject project3 = new JSONObject();
        project3.put("link", "https://github.com/user/project3");
        project3.put("description", "Created a website for a local business using HTML, CSS, and JavaScript.");
        project3.put("technology", "HTML, CSS, JavaScript");
        project3.put("title", "Local Business Website");
        signUpProjectsArray.put(project3);

        // Project 4
        JSONObject project4 = new JSONObject();
        project4.put("link", "https://github.com/user/project4");
        project4.put("description", "Developed a Python script for data analysis using pandas and matplotlib.");
        project4.put("technology", "Python, pandas, matplotlib");
        project4.put("title", "Data Analysis Script");
        signUpProjectsArray.put(project4);

        // Project 5
        JSONObject project5 = new JSONObject();
        project5.put("link", "https://github.com/user/project5");
        project5.put("description", "Implemented a RESTful API backend using Express.js and MongoDB.");
        project5.put("technology", "Express.js, MongoDB");
        project5.put("title", "RESTful API Backend");
        signUpProjectsArray.put(project5);

        jsonObject.put("signUpProjects", signUpProjectsArray);

        // signUpWork
        JSONArray signUpWorkArray = new JSONArray();

        // Work Experience 1
        JSONObject work1 = new JSONObject();
        work1.put("endDate", "2025-08-10");
        work1.put("description", "Developed and maintained web applications using Java and Spring framework.");
        work1.put("company", "Tech Solutions Inc.");
        work1.put("position", "Software Engineer");
        work1.put("type", "Full-time");
        work1.put("startDate", "2020-07-01");
        signUpWorkArray.put(work1);

        // Work Experience 2
        JSONObject work2 = new JSONObject();
        work2.put("endDate", "2020-06-30");
        work2.put("description", "Designed and implemented database schemas for various projects.");
        work2.put("company", "DataCorp");
        work2.put("position", "Database Administrator");
        work2.put("type", "Internship");
        work2.put("startDate", "2019-07-01");
        signUpWorkArray.put(work2);

        // Work Experience 3
        JSONObject work3 = new JSONObject();
        work3.put("endDate", "2019-06-30");
        work3.put("description", "Provided technical support to customers and resolved software issues.");
        work3.put("company", "SupportTech");
        work3.put("position", "Technical Support Specialist");
        work3.put("type", "Part-time");
        work3.put("startDate", "2018-09-01");
        signUpWorkArray.put(work3);

        // Work Experience 4
        JSONObject work4 = new JSONObject();
        work4.put("endDate", "2018-08-31");
        work4.put("description", "Assisted in software testing and quality assurance procedures.");
        work4.put("company", "QualityAssure Inc.");
        work4.put("position", "QA Tester");
        work4.put("type", "Summer Job");
        work4.put("startDate", "2018-06-01");
        signUpWorkArray.put(work4);

        // Work Experience 5
        JSONObject work5 = new JSONObject();
        work5.put("endDate", "2017-12-31");
        work5.put("description", "Managed inventory and customer orders in a retail environment.");
        work5.put("company", "RetailMart");
        work5.put("position", "Sales Associate");
        work5.put("type", "Part-time");
        work5.put("startDate", "2016-06-01");
        signUpWorkArray.put(work5);

        jsonObject.put("signUpWork", signUpWorkArray);

        // signUp
        JSONObject signUpObject = new JSONObject();
        signUpObject.put("emailAddress", "example@xyz.com");
        signUpObject.put("password", "Azerty123!");
        jsonObject.put("signUp", signUpObject);

        // signUpSkills
        JSONArray signUpSkillsArray = new JSONArray();

        // Skill 1
        JSONObject skill1 = new JSONObject();
        skill1.put("level", "Intermediate");
        skill1.put("description", "Proficient in Java programming language.");
        skill1.put("technology", "Java");
        skill1.put("title", "Java Programming");
        signUpSkillsArray.put(skill1);

        // Skill 2
        JSONObject skill2 = new JSONObject();
        skill2.put("level", "Intermediate");
        skill2.put("description", "Familiar with HTML, CSS, and JavaScript for web development.");
        skill2.put("technology", "HTML, CSS, JavaScript");
        skill2.put("title", "Web Development");
        signUpSkillsArray.put(skill2);

        // Skill 3
        JSONObject skill3 = new JSONObject();
        skill3.put("level", "Beginner");
        skill3.put("description", "Learning Python programming language for data analysis.");
        skill3.put("technology", "Python");
        skill3.put("title", "Python Programming");
        signUpSkillsArray.put(skill3);

        // Skill 4
        JSONObject skill4 = new JSONObject();
        skill4.put("level", "Advanced");
        skill4.put("description", "Experienced in SQL database management.");
        skill4.put("technology", "SQL");
        skill4.put("title", "Database Management");
        signUpSkillsArray.put(skill4);

        // Skill 5
        JSONObject skill5 = new JSONObject();
        skill5.put("level", "Intermediate");
        skill5.put("description", "Skilled in using Git for version control.");
        skill5.put("technology", "Git");
        skill5.put("title", "Version Control (Git)");
        signUpSkillsArray.put(skill5);

        jsonObject.put("signUpSkills", signUpSkillsArray);

        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(jsonObject.toString(4)); // Indent with 4 spaces for better readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}