package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import etc.OpenScene;

public class FXMLHomePageController implements Initializable {
    OpenScene openScene = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField searchFoodField;

    @FXML
    private Button buttonOmahKatsu;

    @FXML
    private Button buttonSerabi;

    @FXML
    private Button buttonDimsum;

    @FXML
    private void handleButtonHome(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLHomePage.fxml");
        mainPane.setLeft(page);
        System.out.println("Home Button is clicked!");
    }

    @FXML
    private void handleButtonSettings(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLSettings.fxml");
        mainPane.setCenter(page);
        System.out.println("Setting Button is clicked!");
    }

    @FXML
    private void logoutButton() {
        Pane page = openScene.getPane("/view/FXMLLogin.fxml");
        mainPane.setLeft(page);
        System.out.println("Logout Button is clicked!");
    }

    @FXML
    private void handleButtonHistory(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLHistory.fxml");
        mainPane.setCenter(page);
        System.out.println("History Button is clicked!");
    }

    @FXML
    private void handleButtonNearby(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLNearby.fxml");
        mainPane.setCenter(page);
        System.out.println("Nearby Button is clicked!");
    }

    @FXML
    private void handleButtonOmahKatsu(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLOrderOmahKatsu.fxml");
        mainPane.setCenter(page);
        System.out.println("Omah Katsu Button is clicked!");
    }

    @FXML
    private void handleButtonSerabiNotosuman(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLOrderSerabiNotosuman.fxml");
        mainPane.setCenter(page);
        System.out.println("Serabi Button is clicked!");
    }

    @FXML
    private void handleButtonDimsum(ActionEvent event) {
        Pane page = openScene.getPane("/view/FXMLOrderDimsumBuRudi.fxml");
        mainPane.setCenter(page);
        System.out.println("Serabi Button is clicked!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
