package model;

public class CheckoutData {
  private String order, lokasi;

  public CheckoutData(String order, String lokasi) {
    this.order = order;
    this.lokasi = lokasi;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getLokasi() {
    return lokasi;
  }

  public void setLokasi(String lokasi) {
    this.lokasi = lokasi;
  }
  
}
