package jdbc.models;

public class Warehouse {
  // ID auto-increments
  private int whID;
  private String designation;
  private String address;
  
  public Warehouse() {
    
  }
  
  public Warehouse(int i, String d, String a) {
    this.whID = i;
    this.designation = d;
    this.address = a;
  }
  
  public int getWHiD() {
    return whID;
  }
    
  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String toString() {
    return designation;
    
  }
}
