package controller;

import java.net.URL;
import java.util.ResourceBundle;

import etc.OpenScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class FXMLSettingsController implements Initializable {

  OpenScene openScene = new OpenScene();

  @FXML
  private BorderPane mainPane;

  @FXML
  private Button aboutUsButton;

  @FXML
  private void handleButtonAboutUs(ActionEvent event) {
    Pane page = openScene.getPane("/view/FXMLAboutUs.fxml");
    mainPane.setLeft(page);
    System.out.println("AboutUs Button is clicked!");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
