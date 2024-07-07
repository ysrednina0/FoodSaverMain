package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.UserData;
import controller.FXMLRegisterController;

public class FXMLCustProfileController implements Initializable {

  private List<UserData> userData = new ArrayList<>();
  private XStream xstream = new XStream(new StaxDriver());

  @FXML
  private TextField emailField;

  @FXML
  private TextField nameField;

  @FXML
  private TextField usernameField;

  @FXML
  private TextField phoneField;
  
  @FXML
  private TextField addresField;


  private String currentUsername;

  public void setCurrentUsername(String username) {
    this.currentUsername = username;
    loadUserProfile();
  }

  @FXML
  private void handleEditProfileButton(ActionEvent event) {
    String nama = nameField.getText();
    String username = usernameField.getText();
    String email = emailField.getText();
    String phone = phoneField.getText();
    String addres = addresField.getText();

    if (nama.isEmpty() || username.isEmpty() || email.isEmpty()||phone.isEmpty()||addres.isEmpty()) {
      showAlert(AlertType.ERROR, null, "Please fill in all fields.");
      return;
    }

    boolean userExists = false;
    for (UserData user : userData) {
      if (user.getUsername().equals(currentUsername)) {
        user.setNama(nama);
        user.setUsername(username);
        user.setEmail(email);

        userExists = true;
        break;
      }
    }

    if (!userExists) {
      UserData newUser = new UserData(nama, username, email, phone, addres);
      userData.add(newUser);
    }
    saveXML();

    showAlert(AlertType.INFORMATION, null, "Data has been saved!");
  }

  private void loadUserProfile() {
    if (currentUsername == null) {
      showAlert(AlertType.ERROR, null, "Current username is not set.");
      return;
    }

    loadUser();

    for (UserData user : userData) {
      if (user.getUsername().equals(currentUsername)) {
        nameField.setText(user.getNama());
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        // passwordField.setText(user.getPassword());
        break;
      }
    }
  }

  void saveXML() {
    String xml = xstream.toXML(userData);
    FileOutputStream f = null;
    try {
      f = new FileOutputStream("profile.xml");
      byte[] bytes = xml.getBytes("UTF-8");
      f.write(bytes);
    } catch (Exception e) {
      System.err.println("Warning: " + e.getMessage());
    } finally {
      if (f != null) {
        try {
          f.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  void loadUser() {
    FileInputStream fileInput = null;
    try {
      fileInput = new FileInputStream("profile.xml");

      int isi;
      char c;
      StringBuilder s = new StringBuilder();

      while ((isi = fileInput.read()) != -1) {
        c = (char) isi;
        s.append(c);
      }

      userData = (ArrayList<UserData>) xstream.fromXML(s.toString());
    } catch (Exception e) {
      System.err.println("Warning: " + e.getMessage());
    } finally {
      if (fileInput != null) {
        try {
          fileInput.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
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
    loadUser();
  }

}
