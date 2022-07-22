package jdbc.DAO;

import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.conf.WherehousedCredentials;
import jdbc.models.Warehouse;

public class MySQLWareDAO implements WarehouseDAO {

  /**
   * Steps for JDBC:
   * 1. Load the JDBC Driver into memory
   * 2. Establish a connection using said Driver object
   * 3. Create an SQL statement
   * 4. Use the connection object to execute the SQL statement
   * 5. Parse the returned ResultSet object for what we need
   * 6. CLose the connection
   */
  
  /**
   * @return list of warehouses from db, otherwise null
   */  
  @Override
  public List<Warehouse> findAll() {
    String sql = "SELECT * FROM Warehouse";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      LinkedList<Warehouse> whouses = new LinkedList<>();
      
      while (rs.next()) {
        Warehouse w = new Warehouse(rs.getInt("whID"), rs.getString("designation"), rs.getString("sAddress"));
        whouses.add(w);
      }
      
      return whouses;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Warehouse findById(int id) {
    String sql = "SELECT * FROM Warehouse WHERE whID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new Warehouse(rs.getInt("whID"), rs.getString("designation"), rs.getString("sAddress"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Warehouse findByDes(String designation) {
    String sql = "SELECT * FROM Warehouse WHERE Designation = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, designation);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new Warehouse(rs.getInt("whID"), rs.getString("designation"), rs.getString("sAddress"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Override
  public Warehouse findWarehouse(Warehouse w) {
    Warehouse newWares = findById(w.getWHiD());
    return newWares;
  }

  @Override
  public void updateWarehouse(Warehouse whouse) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updateWarehouse(int id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delWarehouse(Warehouse whouse) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delWarehouse(int id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Warehouse addNew(Warehouse whouse) {
    // TODO Auto-generated method stub
    return null;
  }
  
}