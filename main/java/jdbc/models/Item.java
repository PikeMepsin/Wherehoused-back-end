package jdbc.models;

public class Item {
  // ID auto-increments
  private int iID;
  private String name;
  private int size;
  private int quantity;
  private int innerCap;
  
  public Item() {
    
  }
  
  public Item(int iID, String name, int size, int quantity) {
    this.iID = iID;
    this.name = name;
    this.size = size;
    this.quantity = quantity;
    this.innerCap = quantity * size;
  }

  public int getiID() {
    return iID;
  }

  public void setiID(int iID) {
    this.iID = iID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public int getInnerCap() {
    return innerCap;
  }
   
}
