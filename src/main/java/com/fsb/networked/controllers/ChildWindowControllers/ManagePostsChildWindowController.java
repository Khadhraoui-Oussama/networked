package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.dto.ImagePostDTO;
import com.fsb.networked.dto.TextPostDTO;
import com.fsb.networked.dto.VideoPostDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.json.JSONArray;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagePostsChildWindowController implements Initializable {

    @FXML
    Label statusLabel;
    @FXML
    private Button btnDeletePost;
    @FXML
    private ListView<TextPostDTO> postsListView;
    private final JSONArray postsArray = new JSONArray();

    @FXML
    private void deletePost()
    {
        //TODO MAKE SURE THAT DELETE CONNECTION ALSO DELETES ALL PENDING REQUESTS FOR ADD CONNECTION
        //TODO DELETE FROM DB
        //DONE TODO : when delete skill btn is presses delete the job from the listview of jobs
        TextPostDTO selectedPost = postsListView.getSelectionModel().getSelectedItem();
        postsListView.getItems().remove(selectedPost);
        postsArray.remove(postsListView.getItems().indexOf(postsListView.getSelectionModel().getSelectedItem()));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        /*------------*/
        //held together by glue
       postsListView.setCellFactory(new Callback<ListView<TextPostDTO>, ListCell<TextPostDTO>>() {
            @Override
            public ListCell<TextPostDTO> call(ListView<TextPostDTO> param) {
                return new ListCell<TextPostDTO>() {
                    @Override
                    protected void updateItem(TextPostDTO post, boolean empty) {
                        super.updateItem(post, empty);
                        if (post == null || empty) {
                            setText(null);
                        } else {
                            setText("Content: " + post.getPostText() +"\nPosted at: " + post.getPublicationDateTime()  +  "\nPost has image: " + (post instanceof ImagePostDTO) + "\nPost has video: " + (post instanceof VideoPostDTO));
                        }
                    }
                };
            }
        });
        //TODO GET FROM DB
        //JSONArray postArray = JSONParser.getEducationsJSONArray(ImportantFileReferences.INDIVIDUALJSON);
            // Iterate over each skill object in the JSON array and extract each field

            /*ConnectionDTO education = new EducationDTO(positionValue,companyValue,descriptionValue,typeValue, Conversions.stringtoLocalDate(startDateValue),Conversions.stringtoLocalDate(endDateValue));
            if(!(education.getDiploma().isBlank() && education.getType().isBlank() && education.getDescription().isBlank() && education.getInstitute().isBlank()))
            {
                connectionsListView.getItems().add(education);
                this.connectionsArray.put(educationObject);
            }*/
    }
}

