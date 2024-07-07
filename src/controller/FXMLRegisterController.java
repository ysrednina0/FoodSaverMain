package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.UserData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import etc.OpenScene;

public class FXMLRegisterController implements Initializable {
  OpenScene openScene = new OpenScene();
  
  @FXML
  private BorderPane mainPane;
  
  @FXML
  private TextField txtName;
  
  @FXML
  private TextField txtUsername;
  
  @FXML
  private TextField txtEmail;
  
  @FXML
  private PasswordField txtPassword;
  
  @FXML
  private PasswordField txtConfirmPassword;
  
  @FXML
  private RadioButton rbStore;
  
  @FXML
  private RadioButton rbCustomer;
  
  @FXML
  private Button registerButton;

  @FXML
  Hyperlink loginHyperlink;

  UserList userList = new UserList();

  // @FXML
  // void selectRole(ActionEvent event) {
  //   if (rbCostumer.isSelected()) {
      
  //   }
  // }
  
  @FXML
  private void handleRegisterButton(ActionEvent event) {
    String name = txtName.getText();
    String username = txtUsername.getText();
    String email = txtEmail.getText();
    String password = txtPassword.getText();
    String confirmPassword = txtConfirmPassword.getText();
    String role = "";
    
    if (!isValidEmail(email)) {
      showAlert("Registration Failed", "Invalid email format.");
      return;
    }

    if (password.length() < 8) {
      showAlert("Registration Failed", "Password must be at least 8 characters.");
      return;
    }
    
    if (!password.equals(confirmPassword)) {
      showAlert("Registration Failed", "Passwords do not match.");
      return;
    }
    
    if (rbCustomer.isSelected()) {
      role = "customer";
      registerUser(name, username, email, confirmPassword, role);
      directToHomePage(event);
    } else if (rbStore.isSelected()) {
      role = "store";
      registerUser(name, username, email, confirmPassword, role);
      directToStoreDashboard(event);
    } else {
      showAlert("Registration Failed", "Could not register user.");
     }
  }

  // String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


  private boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_]+(?:\\.[a-zA-Z0-9_]+)*@(?:[a-zA-Z0-9]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
  }
  
  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
  
  private void directToHomePage(ActionEvent event) {
    Pane page = openScene.getPane("/view/FXMLHomePage.fxml");
    mainPane.setLeft(page);
    System.out.println("Home Button is clicked!");
  }
  
  private void directToLogin() {
    Pane page = openScene.getPane("/view/FXMLLogin.fxml");
    mainPane.setLeft(page);
    System.out.println("Direct to login!");
  }
  
  private void directToStoreDashboard(ActionEvent event) {
    Pane page = openScene.getPane("/view/FXMLStore.fxml");
    mainPane.setCenter(page);
    System.out.println("Store Button is clicked!");
  }
  
  @FXML
  private void handleLoginHyperlink(ActionEvent event) {
    directToLogin();
  }
  
  
  public boolean registerUser(String name, String username, String email, String password, String role) {
    UserData user = new UserData(name, username, email, password, role);
    ArrayList<UserData> arrayUser = userList.getUserList();

    arrayUser.add(user);
    UserList.setLoginAccount(user);
    XStream xstream = new XStream(new StaxDriver());
    String xml = xstream.toXML(arrayUser);
    FileOutputStream f = null;
    try {
      f = new FileOutputStream("users.xml");
      byte[] bytes = xml.getBytes("UTF-8");
      f.write(bytes);
    } catch (Exception e) {
      System.out.println("Warning:" + e.getMessage());
    } finally {
      if (f != null) {
        try {
          f.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    userList.loadUser();
  }
}