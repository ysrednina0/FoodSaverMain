package model;

public class UserData {
  protected String nama, username, email, password, role, phone, addres;

  public UserData(String nama, String username, String email, String password, String role) {
    this.nama = nama;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  // public UserData(String nama, String username, String email) {
  // this.nama = nama;
  // this.username = username;
  // this.email = email;
  // }

  public UserData(String nama, String username, String email, String password, String role, String phone,
      String addres) {
    this.nama = nama;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.phone = phone;
    this.addres = addres;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddres() {
    return addres;
  }

  public void setAddres(String addres) {
    this.addres = addres;
  }
}
