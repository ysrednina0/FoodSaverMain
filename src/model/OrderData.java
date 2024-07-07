package model;

public class OrderData {
  private String namaProduk;
  private int hargaProduk, jumlahBeli, totalHarga;
  
  public OrderData(String namaProduk, int hargaProduk, int jumlahBeli, int totalHarga) {
    this.namaProduk = namaProduk;
    this.hargaProduk = hargaProduk;
    this.jumlahBeli = jumlahBeli;
    this.totalHarga = totalHarga;
  }
     
  public OrderData(String namaProduk){
    this.namaProduk = namaProduk;
  }
  
  // nama produk
  public String getNamaProduk() {
    return namaProduk;
  }
  
  public void setNamaProduk(String namaProduk) {
    this.namaProduk = namaProduk;
  }

  // harga produk
  public int getHargaProduk() {
    return hargaProduk;
  }

  public void setHargaProduk(int hargaProduk) {
    this.hargaProduk = hargaProduk;
  }

  // jumlah pesanan
  public int getJumlahBeli() {
    return jumlahBeli;
  }

  public void setJumlahBeli(int jumlahBeli) {
    this.jumlahBeli = jumlahBeli;
  }

  // total harga
  public int getTotalHarga() {
    return totalHarga;
  }

  public void setTotalHarga(int totalHarga) {
    this.totalHarga = totalHarga;
  }

  public void hitungTotal(){
    this.totalHarga = this.hargaProduk * this.jumlahBeli;
  }
}
