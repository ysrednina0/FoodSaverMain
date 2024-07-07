package controller;

import static javafx.collections.FXCollections.observableArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import etc.OpenScene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import model.ProductData;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;

public class FXMLStoreController implements Initializable {

    OpenScene openScene = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button addProduct;

    @FXML
    private Button deleteProduct;

    @FXML
    private TableView<ProductData> productTable;

    @FXML
    private TableColumn namaProduk;

    @FXML
    private TableColumn hargaProduk;

    @FXML
    private TextField tfNamaProduk;

    @FXML
    private TextField tfHargaProduk;

    ObservableList data = observableArrayList(
            new ProductData("Original Katsu", 15000),
            new ProductData("Spicy Katsu", 15000),
            new ProductData("Curry Katsu", 15000),
            new ProductData("Teriyaki Katsu", 15000));

    @FXML
    void handleButtonAddProduct(ActionEvent event) throws IOException {
        if (tfNamaProduk.getText().isEmpty() || tfHargaProduk.getText().isEmpty()) {
            showAlert(AlertType.ERROR, null, "Product data is not complete!");
        } else {
            String tempName = tfNamaProduk.getText();
            int tempPrice = Integer.parseInt(tfHargaProduk.getText());

            data.add(new ProductData(tempName, tempPrice));
            System.out.println(data);

            tfNamaProduk.setText("");
            tfHargaProduk.setText("");

            saveXML();

            showAlert(AlertType.INFORMATION, null, "Successfully added data!");
        }
    }

    @FXML
    void handleButtonDeleteProduct(ActionEvent event) throws IOException {
        int picked = productTable.getSelectionModel().getSelectedIndex();

        if (picked >= 0) {
            productTable.getItems().remove(picked);
            showAlert(AlertType.INFORMATION, null, "Successfully delete data!");
            saveXML();
        } else {
            showAlert(AlertType.ERROR, null, "Select the product to delete!");
        }
    }

    @FXML
    void onEditName(TableColumn.CellEditEvent<ProductData, String> nameCellEditEvent) {
        ProductData produk = productTable.getSelectionModel().getSelectedItem();
        produk.setNama(nameCellEditEvent.getNewValue());
        saveXML();
    }

    @FXML
    void onEditPrice(TableColumn.CellEditEvent<ProductData, Integer> priceCellEditEvent) {
        ProductData produk = productTable.getSelectionModel().getSelectedItem();
        produk.setHarga(priceCellEditEvent.getNewValue());
        saveXML();
    }

    @FXML
    private void logoutButton() {
        Pane page = openScene.getPane("/view/FXMLLogin.fxml");
        mainPane.setLeft(page);
        System.out.println("Logout Button is clicked!");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    XStream xstream = new XStream(new StaxDriver());

    void saveXML() {
        String xml = xstream.toXML(data);
        FileOutputStream f = null;
        try {
            f = new FileOutputStream("product.xml");
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

    void loadProduct() {
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream("product.xml");

            int isi;
            char c;
            String s = "";

            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                s += c;
            }

            data = (ObservableList) xstream.fromXML(s);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProduct();
        namaProduk.setCellValueFactory(new PropertyValueFactory<ProductData, String>("Nama"));
        hargaProduk.setCellValueFactory(new PropertyValueFactory<ProductData, String>("Harga"));
        productTable.setItems(data);

        productTable.setEditable(true);
        namaProduk.setCellFactory(TextFieldTableCell.forTableColumn());
        hargaProduk.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }
}
