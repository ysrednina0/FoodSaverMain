package controller;

import java.io.FileInputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import model.UserData;

public class UserList {

  private ArrayList<UserData> userList = new ArrayList<>();

  private static UserData loginAccount;

  public UserList() {
    loadUser();
  }

  public ArrayList<UserData> getUserList() {
    return userList;
  }

  public ArrayList<UserData> loadUser() {
    XStream xStream = new XStream(new StaxDriver());
    // xStream.alias("userData", UserData.class);
    xStream.addPermission(AnyTypePermission.ANY); // Fix forbidden class exception
    FileInputStream fileInput;
    try {

      fileInput = new FileInputStream("users.xml");
 
      String s = "";
      int isi;
      char c;
      while ((isi = fileInput.read()) != -1) {
        c = (char) isi;
        s += c;
      }

      userList = (ArrayList<UserData>) xStream.fromXML(s);
      return userList;

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return userList;
    }
  }

  public static void setLoginAccount(UserData loginAccount) {
    UserList.loginAccount = loginAccount;
  }

  public static UserData getLoginAccount() {
    return loginAccount;
  }

}
