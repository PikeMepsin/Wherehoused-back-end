package jdbc.DAO;

import java.util.List;
import jdbc.models.Item;

// Item queries
public interface ItemDAO {
  
  public List<Item> findAll();
  
  public Item findById(int id);
  
  // potentially "void" operation should probably come back
  // with Item (impossible to know with auto-increment)
  // or (more is more approach) return the Item itself
  public Item addNew(Item item);
  
  // Ship (delete) one or many
  public void ship(Item item);
  public void ship(List<Item> items);
}
