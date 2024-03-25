package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.JobOfferDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class JobPostItemController implements Initializable {
    @FXML
    ImageView companyLogoImgView;
    @FXML
    Label companyNameLabel;
    @FXML
    Button followBtn;
    @FXML
    Label dateOfPublicationLabel;
    @FXML
    Label jobDescriptionLabel;
    @FXML
    Label numberOfApplicantsLabel;
    @FXML
    Button likeBtn;
    @FXML
    Button applyBtn;

    public void followCompany()
    {

    }
    public void likeJobPost()
    {

    }
    public void applyForJobPost()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //
    public void setData(JobOfferDTO jobOffer)
    {
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_user.png")));
        companyLogoImgView.setImage(logoImage);
        companyNameLabel.setText(jobOffer.getCompany());
        dateOfPublicationLabel.setText("Offer created at : " + jobOffer.getPublicationDate().toString());
        jobDescriptionLabel.setText(jobOffer.getDescription());
        numberOfApplicantsLabel.setText(jobOffer.getNumberOfApplicants() + " : Applicants");
    }

    @Override
    public String toString() {
        return "JobPostItemController{" +
                "companyLogoImgView=" + companyLogoImgView +
                ", companyNameLabel=" + companyNameLabel +
                ", followBtn=" + followBtn +
                ", dateOfPublicationLabel=" + dateOfPublicationLabel +
                ", jobDescriptionLabel=" + jobDescriptionLabel +
                ", numberOfApplicantsLabel=" + numberOfApplicantsLabel +
                ", likeBtn=" + likeBtn +
                ", applyBtn=" + applyBtn +
                '}';
    }
}
