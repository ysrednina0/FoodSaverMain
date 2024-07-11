package etc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FoodSaver extends Application{

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
    Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLHomePage.fxml"));

    
    Scene scene = new Scene(root);

    primaryStage.setTitle("FoodSaver");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
 