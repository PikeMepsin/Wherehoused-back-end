package jdbc.models;

public class Shelf {
  // ID auto-increments
  private int shID;
  private int whID;
  private int capacity;
  
  public Shelf() {
    
  }
  
  public Shelf(int shID, int wID, int capacity) {
    this.shID = shID;
    this.whID = wID;
    this.capacity = capacity;
  }
  
  public int getShID() {
    return shID;
  }

  public void setShID(int shID) {
    this.shID = shID;
  }

  public int getWhID() {
    return whID;
  }

  public void setWhID(int whID) {
    this.whID = whID;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

}
