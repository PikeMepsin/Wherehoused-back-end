package jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.conf.WherehousedCredentials;
import jdbc.models.shelfItem;

public class MySQLshItemsDAO implements shelfItemsDAO {

  @Override // various gets
  public List<shelfItem> getAll() {
    String sql = "SELECT * FROM shelfItems";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      LinkedList<shelfItem> shItems = new LinkedList<>();
      
      while (rs.next()) {
        shelfItem si = new shelfItem(rs.getInt("shID"), rs.getInt("iID"));
        shItems.add(si);
      }
      
      return shItems;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<shelfItem> getShelf(int id) {
    String sql = "SELECT * FROM shelfItems WHERE shID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      List<shelfItem> items = new LinkedList<>();
      
      while (rs.next()) {
         shelfItem si = new shelfItem(rs.getInt("shID"), rs.getInt("iID"));
         items.add(si);
      }
      return items;
      
    } 
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public shelfItem findBy(int shID, int iID) {
    String sql = "SELECT * FROM shelfItems WHERE shID = ? AND iID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, shID);
      ps.setInt(2, iID);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new shelfItem(rs.getInt("shID"), rs.getInt("iID"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public shelfItem putOnShelf(shelfItem si) {
    // POST
    String sql = "INSERT INTO shelfItems (shID, iID) VALUES (?, ?)";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, si.getShID());
      ps.setInt(2, si.getiID());
      
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected != 0) {
        conn.commit();
        return si;
      }
      else {
        conn.rollback();
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    
    return null;
  }

  @Override
  public void takeOffShelf(shelfItem si) {
    // DEL
    String sql = "DELETE FROM shelfItems WHERE shID = ? AND iID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, si.getShID());
      ps.setInt(2, si.getiID());
      
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected != 0) {
        conn.commit();
      }
      else {
        conn.rollback();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public shelfItem moveItem(int target, shelfItem si) {
    // PUT
    String sql = "UPDATE shelfItems SET shID = ? WHERE shID = ? AND iID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, target);
      ps.setInt(2, si.getShID());
      ps.setInt(3, si.getiID());
      
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected != 0) {
        conn.commit();
        return new shelfItem(target, si.getiID());
      }
      else {
        conn.rollback();
      }
      return null;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return si;
    
  }
  
}
