package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class FXMLChartController implements Initializable {
  XYChart.Series data = new XYChart.Series<>();


  @FXML
  private LineChart lcSales;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    data.getData().add(new XYChart.Data("Januari", 30));
    data.getData().add(new XYChart.Data("Februari", 27));
    data.getData().add(new XYChart.Data("Maret", 50));
    data.getData().add(new XYChart.Data("April", 53));
    data.getData().add(new XYChart.Data("Mei", 46));
    data.getData().add(new XYChart.Data("Juni", 60));

    lcSales.getData().addAll(data);
  }

}
