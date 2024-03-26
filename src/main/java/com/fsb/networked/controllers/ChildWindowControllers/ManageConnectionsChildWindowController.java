package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.dto.ConnectionDTO;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.json.JSONArray;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageConnectionsChildWindowController implements Initializable {
    @FXML
    private Button btnAddConnection;
    @FXML
    TextField connectionEmailField;
    @FXML
    Label statusLabel;
    @FXML
    private Button btnDeleteConnection;
    @FXML
    private ListView<ConnectionDTO> connectionsListView;
    private final JSONArray connectionsArray = new JSONArray();
    @FXML
    private void addConnection()
    {
        //TODO ADD TO DB
        boolean res =  validateConnection();
        System.out.println(res);
        if(res)
        {
            ConnectionDTO connection = new ConnectionDTO();
            connection.setEmailAddress(connectionEmailField.getText());
        }
    }
    @FXML
    private void deleteConnection()
    {
        //TODO MAKE SURE THAT DELETE CONNECTION ALSO DELETES ALL PENDING REQUESTS FOR ADD CONNECTION
        //TODO DELETE FROM DB
        //DONE TODO : when delete skill btn is presses delete the job from the listview of jobs
        ConnectionDTO selectedConnection = connectionsListView.getSelectionModel().getSelectedItem();
        connectionsListView.getItems().remove(selectedConnection);
        connectionsArray.remove(connectionsListView.getItems().indexOf(connectionsListView.getSelectionModel().getSelectedItem()));
    }
     private boolean validateConnection() {
        boolean isValid = true;
        isValid &= (Validator.validateField(connectionEmailField, Regexes.EMAIL_REGEX, Alerts.AlertEmailField()));
        return isValid;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        /*------------*/
        //held together by glue
       connectionsListView.setCellFactory(new Callback<ListView<ConnectionDTO>, ListCell<ConnectionDTO>>() {
            @Override
            public ListCell<ConnectionDTO> call(ListView<ConnectionDTO> param) {
                return new ListCell<ConnectionDTO>() {
                    @Override
                    protected void updateItem(ConnectionDTO connection, boolean empty) {
                        super.updateItem(connection, empty);
                        if (connection == null || empty) {
                            setText(null);
                        } else {
                            setText("Connection Name: " + connection.getFirstName() +", " + connection.getLastName() + "\nConnection email: " + connection.getEmailAddress()+ "\nConnected since: " + connection.getDateOfConnection().toString());
                        }
                    }
                };
            }
        });
        //TODO GET FROM DB
        JSONArray educationArray = JSONParser.getJSONArrayFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpEducation");
            // Iterate over each skill object in the JSON array and extract each field

            /*ConnectionDTO education = new EducationDTO(positionValue,companyValue,descriptionValue,typeValue, Conversions.stringtoLocalDate(startDateValue),Conversions.stringtoLocalDate(endDateValue));
            if(!(education.getDiploma().isBlank() && education.getType().isBlank() && education.getDescription().isBlank() && education.getInstitute().isBlank()))
            {
                connectionsListView.getItems().add(education);
                this.connectionsArray.put(educationObject);
            }*/
    }
}

