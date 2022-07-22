package jdbc.models;

public class shelfItem {
  private int shID;
  private int iID;
  
  public shelfItem() {
    
  }
  
  public shelfItem(int shID, int iID) {
    super();
    this.shID = shID;
    this.iID = iID;
  }

  public int getShID() {
    return shID;
  }

  public void setShID(int shID) {
    this.shID = shID;
  }
  
  public int getiID() {
    return iID;
  }

  public void setiID(int iID) {
    this.iID = iID;
  }
   
  
}
