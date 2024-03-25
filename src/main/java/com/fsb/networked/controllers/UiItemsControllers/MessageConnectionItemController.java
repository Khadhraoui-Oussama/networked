package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.MessageConnectionDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessageConnectionItemController implements Initializable {
    @FXML
    ImageView opImgView;
    @FXML
    Label opNameLabel;
    @FXML
    Label lastMessageDateLabel;
    @FXML
    Label lastMessageTimeLabel;
    @FXML
    Label lastMessageBodyLabel;
    @FXML
    Button messageConnectionBtn;
    @FXML
    Button removeConnectionBtn;

    public void messageConnection()
    {

    }
    public void removeConnection()
    {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(MessageConnectionDTO messageConnection)
    {
        //get  image from database
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/anonymous_logo.jpg")));
        opImgView.setImage(img);
        opNameLabel.setText(messageConnection.getConnectionName());
        lastMessageDateLabel.setText("Sent at : " + messageConnection.getLastMessageDate().toString());
        lastMessageBodyLabel.setText(messageConnection.getLastMessageBody());
        lastMessageTimeLabel.setText("Sent at : " + messageConnection.getLastMessageTime());
    }

    @Override
    public String toString() {
        return "MessageConnectionItemController{" +
                "opImgView=" + opImgView +
                ", opNameLabel=" + opNameLabel +
                ", lastMessageDateLabel=" + lastMessageDateLabel +
                ", lastMessageTimeLabel=" + lastMessageTimeLabel +
                ", lastMessageBodyLabel=" + lastMessageBodyLabel +
                ", messageConnectionBtn=" + messageConnectionBtn +
                ", removeConnectionBtn=" + removeConnectionBtn +
                '}';
    }
}
