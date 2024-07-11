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

public class FXMLNearbyController implements Initializable {

  OpenScene openScene = new OpenScene();

  @FXML
  private BorderPane mainPane;

  @FXML
  private Button buttonOmahKatsu;

  @FXML
  private Button buttonSerabi;

  @FXML
  private Button buttonDimsum;

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
    System.out.println("Omah Katsu Button is clicked!");
  }

  @FXML
  private void handleButtonDimsum(ActionEvent event) {
    Pane page = openScene.getPane("/view/FXMLOrderDimsumBuRudi.fxml");
    mainPane.setCenter(page);
    System.out.println("Omah Katsu Button is clicked!");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
