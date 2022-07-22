package jdbc.DAO;

import java.util.List;
import jdbc.models.Warehouse;

// Warehouse queries
public interface WarehouseDAO {
  
  public List<Warehouse> findAll();
  public Warehouse findById(int id);
  public Warehouse findByDes(String designation);
  public Warehouse findWarehouse(Warehouse w);
  
  // potentially "void" operation should probably come back
  // with whID (impossible to know with auto-increment)
  // or (more is more approach) return the Warehouse itself
  public Warehouse addNew(Warehouse whouse);
  public void updateWarehouse(Warehouse whouse); // contains the ID and updates entry
  public void updateWarehouse(int id);
  public void delWarehouse(Warehouse whouse);
  public void delWarehouse(int id); // overloaded for completeness but we probably only need this one
}
