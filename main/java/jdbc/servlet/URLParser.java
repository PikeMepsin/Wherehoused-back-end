package jdbc.servlet;

public class URLParser {
  public int extractIdFromURL(String url) {
    // break up URL for pathing to db
    String[] splitString = url.split("/");
    
    try {
      return Integer.parseInt(splitString[2]);
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }
  
  public String extractPathOnly(String url) {
    String[] sS = url.split("/");
    String newRL = sS[0] + "/" + sS[1] + "/" + sS[2] + "/" + sS[3];
    return newRL;
  }
}
