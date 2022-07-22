package jdbc.conf;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class WherehousedCredentials {
  private static WherehousedCredentials instance;
  private String url;
  private String username;
  private String password;
  
  private WherehousedCredentials() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      // Read from app.properties
      try (InputStream input = WherehousedCredentials.class.getClassLoader().getResourceAsStream("app.properties")) {
        
        Properties props = new Properties();
        props.load(input);
        
        // grab the keys to the car
        this.url = props.getProperty("db.url");
        this.username = props.getProperty("db.username");
        this.password = props.getProperty("db.password");
        
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public static WherehousedCredentials getInstance() {
    if (instance == null) {
      instance = new WherehousedCredentials();
    }
    return instance;
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);
  }
}
