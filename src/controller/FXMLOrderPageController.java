package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import model.CheckoutData;
import model.OrderData;
import model.ProductData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import etc.OpenScene;

public class FXMLOrderPageController implements Initializable {
    // ObservableList<String> list = FXCollections.observableArrayList();
    OpenScene openScene = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    private ChoiceBox pilihanProduk;

    @FXML
    private ChoiceBox tujuan;

    @FXML
    private RadioButton rbOrder;

    @FXML
    private RadioButton rbDonate;

    @FXML
    private TextField jumlah;

    @FXML
    private Button orderButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<OrderData> orderTable;

    @FXML
    private TableColumn namaProduk;

    @FXML
    private TableColumn hargaProduk;

    @FXML
    private TableColumn jumlahProduk;

    @FXML
    private TableColumn totalHarga;

    @FXML
    private ToggleGroup order1;

    OrderData order = new OrderData("", 0, 0, 0);
    ProductData product = new ProductData("", 15000);
    CheckoutData checkout = new CheckoutData("", "");

    ObservableList<OrderData> od = FXCollections.observableArrayList(new OrderData("", 0, 0, 0));
    ObservableList<ProductData> pd = FXCollections.observableArrayList(new ProductData("", 0));
    ObservableList<CheckoutData> cod = FXCollections.observableArrayList(new CheckoutData("", ""));
    // Button

    @FXML
    void handleButtonAdd(ActionEvent event) {
        if (pilihanProduk.getValue() == null || jumlah.getText().isEmpty()) { // check
            showAlert(AlertType.ERROR, null, "Product data is not complete!");
        } else {
            String tempProd = pilihanProduk.getValue().toString();
            int tempPrc = product.getHarga();
            order.setHargaProduk(tempPrc);

            int tempAmnt = Integer.parseInt(jumlah.getText());
            order.setJumlahBeli(tempAmnt);
            order.hitungTotal();

            int tempTotal = order.getTotalHarga();

            // String tempType = jenisOrderan.getValue().toString();

            od.add(new OrderData(tempProd, tempPrc, tempAmnt, tempTotal));

            pilihanProduk.setValue(null);
            jumlah.setText("");

            saveXML();

            showAlert(AlertType.INFORMATION, null, "Order added successfully ");
        }
    }

    @FXML
    void handleCheckoutButton(ActionEvent event) {
        if (orderTable.getColumns().isEmpty() || order1.getSelectedToggle() == null || tujuan.getValue() == null) {
            showAlert(AlertType.ERROR, null, "Please fill the order table, toggle, and location");
        } else {
            String tempOrder = order1.getSelectedToggle().toString();
            String tempLoc = tujuan.getValue().toString();
            // checkout.setOrder(tempOrder);
            // checkout.setLokasi(tempLoc);

            cod.add(new CheckoutData(tempOrder, tempLoc));
            // checkout.setLokasi(tempLoc);

            order1.setUserData(null);
            tujuan.setValue(null);

            saveXMLCO(cod);
            showAlert(AlertType.INFORMATION, null, "Your order is successfully!");
        }
    }

    @FXML
    void handleDeleteButton(ActionEvent event) {
        int picked = orderTable.getSelectionModel().getSelectedIndex();
        if (picked >= 0) {
            orderTable.getItems().remove(picked);
        }
        saveXML();

        showAlert(AlertType.INFORMATION, null, "Order delete successfully");
    }

    @FXML
    void onEditName(TableColumn.CellEditEvent<OrderData, String> nameCellEditEvent) {
        order = orderTable.getSelectionModel().getSelectedItem();
        order.setNamaProduk(nameCellEditEvent.getNewValue());

        saveXML();
    }

    @FXML
    void onEditAmount(TableColumn.CellEditEvent<OrderData, Integer> amountCellEditEvent) {
        order = orderTable.getSelectionModel().getSelectedItem();
        order.setJumlahBeli(amountCellEditEvent.getNewValue());

        saveXML();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    XStream xstream = new XStream(new StaxDriver());

    void loadOrder() {
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream("order.xml");

            int isi;
            char c;
            String s = "";

            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                s += c;
            }

            od = (ObservableList) xstream.fromXML(s);
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

            pd = (ObservableList) xstream.fromXML(s);

            for (ProductData i : pd) {
                pilihanProduk.getItems().add(i.getNama());
            }
        } catch (Exception e) {
            System.err.println("Warning: " + e.getMessage());
        } 
        finally {
            if (fileInput != null) {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void loadCheckout() {
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream("checkout.xml");

            int isi;
            char c;
            String s = "";

            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                s += c;
            }

             cod = (ObservableList) xstream.fromXML(s);
        } catch (Exception e) {
            System.err.println("Warning: " + e.getMessage());
        } 
        finally {
            if (fileInput != null) {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void saveXML() {
        String xml = xstream.toXML(od);
        FileOutputStream f = null;
        try {
            f = new FileOutputStream("order.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            f.write(bytes);
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
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

    void saveXMLCO(ObservableList<CheckoutData> checkoutList) {
        
        String xml = xstream.toXML(checkoutList);
        FileOutputStream f = null;
        try {
            f = new FileOutputStream("checkout.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            f.write(bytes);
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
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

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("FXML Register Controller initialized");
        loadOrder();
        loadProduct();
        loadCheckout();

        namaProduk.setCellValueFactory(new PropertyValueFactory<OrderData, String>("NamaProduk"));
        hargaProduk.setCellValueFactory(new PropertyValueFactory<OrderData, String>("HargaProduk"));
        jumlahProduk.setCellValueFactory(new PropertyValueFactory<OrderData, String>("JumlahBeli"));
        totalHarga.setCellValueFactory(new PropertyValueFactory<OrderData, String>("TotalHarga"));
        orderTable.setItems(od);

        namaProduk.setCellFactory(TextFieldTableCell.forTableColumn());
        jumlahProduk.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        orderTable.setEditable(true);

        // pilihanProduk.getItems().addAll("Original Katsu", "Spicy Katsu", "Curry Katsu", "Teriyaki Katsu");
        tujuan.getItems().addAll("A", "B", "C", "D", "E");
    }

}
