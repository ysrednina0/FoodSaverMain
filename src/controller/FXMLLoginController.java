package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.UserData;

import java.net.URL;
import java.util.ResourceBundle;

import etc.OpenScene;

/**
 * FXMLLoginController
 */
public class FXMLLoginController implements Initializable {

  OpenScene openScene = new OpenScene();

  @FXML
  private BorderPane mainPane;

  @FXML
  private TextField txtUsername;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private Button loginButton;

  @FXML
  private Hyperlink registerHyperlink;

  // private LoginData userModel = new LoginData();

  private UserList userList = new UserList();
  // private ArrayList<User> userList = new ArrayList<>();

  @FXML
  private void handleLoginButton(ActionEvent event) {

    String username = txtUsername.getText();
    String password = txtPassword.getText();

    if (isValidLogin(username, password)) {
      UserData loginAccount = UserList.getLoginAccount();
      if (loginAccount.getRole().equals("customer")) {
        directToHomePage();
      } else if (loginAccount.getRole().equals("store")) {
        directToStoreDashboard();
      }
      showAlert(AlertType.INFORMATION, "Login Success", "Welcome " + username + "!");
    } else {
      showAlert(AlertType.ERROR, "Login Failed", "Username and password not registered.");
    }
  }

  @FXML
  private void handleRegisterHyperlink(ActionEvent event) {
    directToRegistrationPage();
  }

  private void directToHomePage() {
    Pane page = openScene.getPane("/view/FXMLHomePage.fxml");
    mainPane.setLeft(page);
    System.out.println("Home Button is clicked!");
  }

  private void directToRegistrationPage() {
    Pane page = openScene.getPane("/view/FXMLRegister.fxml");
    mainPane.setCenter(page);
    System.out.println("Create account button is clicked!");
  }

  private void directToStoreDashboard() {
    Pane page = openScene.getPane("/view/FXMLStore.fxml");
    mainPane.setLeft(page);
    System.out.println("Store Button is clicked!");
  }

  private void showAlert(AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO
    // loadUserData();
    userList.loadUser();
  }

  private boolean isValidLogin(String username, String password) {
    // System.out.println(username); 
    // System.out.println(password);
    // System.out.println(userModel.getUsers());
    // System.out.println(userList.getUserList().get(0).getUsername());

    if (userList != null) {
      for (int i = 0; i < userList.getUserList().size(); i++) {
        if (username.equals(userList.getUserList().get(i).getUsername())
            && password.equals(userList.getUserList().get(i).getPassword())) {
              UserList.setLoginAccount(userList.getUserList().get(i));
              // if (UserList.getLoginAccount().getRole().equals("user")) {
              //   directToHomePage();
              // } else {
              //   directToStoreDashboard();
              // }
          return true;
        }
      }
    }
    return false;
  }
}
