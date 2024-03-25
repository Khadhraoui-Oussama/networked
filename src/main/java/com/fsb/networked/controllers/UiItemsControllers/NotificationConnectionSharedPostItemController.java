package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.NotificationConnectionSharedPostDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NotificationConnectionSharedPostItemController implements Initializable {
    @FXML
    ImageView opImgView;
    @FXML
    Label opNameLabel;
    @FXML
    Label sharedPostDateLabel;
    @FXML
    Label sharedPostStartBodyLabel;
    @FXML
    Button seeSharedPostBtn;
    @FXML
    Button removeConnectionBtn;

    public void seeSharedPost()
    {

    }
    public void removeConnection()
    {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(NotificationConnectionSharedPostDTO sharePostNotification)
    {
        //get  image from database
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/anonymous_logo.jpg")));
        opImgView.setImage(img);
        opNameLabel.setText(sharePostNotification.getConnectionName() + " ,just shared a post!");
        sharedPostDateLabel.setText("Post Created at : " + sharePostNotification.getPostShareDate().toString());
        sharedPostStartBodyLabel.setText(sharePostNotification.getPostContentStart());
    }
    @Override
    public String toString() {
        return "SharePostNotificationController{" +
                "opImgView=" + opImgView +
                ", opNameLabel=" + opNameLabel +
                ", sharedPostDateLabel=" + sharedPostDateLabel +
                ", sharedPostBodyLabel=" + sharedPostStartBodyLabel +
                ", seeSharedPostBtn=" + seeSharedPostBtn +
                ", removeConnectionBtn=" + removeConnectionBtn +
                '}';
    }
}
