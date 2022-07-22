package jdbc.DAO;

import java.util.List;

import jdbc.models.shelfItem;

public interface shelfItemsDAO {
  public List<shelfItem> getAll();
  public List<shelfItem> getShelf(int id);
  public shelfItem findBy(int shID, int iID);
  
  // POST and DELETE
  public shelfItem putOnShelf(shelfItem si);
  public void takeOffShelf(shelfItem si);
  
  // PUT moves an item to a different shelf
  public shelfItem moveItem(int target, shelfItem si);
}
