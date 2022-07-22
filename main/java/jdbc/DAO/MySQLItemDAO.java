package jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.conf.WherehousedCredentials;
import jdbc.models.Item;

public class MySQLItemDAO implements ItemDAO {

  @Override //various gets
  public List<Item> findAll() {
    String sql = "SELECT * FROM Item";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareCall(sql);
      ResultSet rs = ps.executeQuery();
      LinkedList<Item> items = new LinkedList<>();
      
      while (rs.next()) {
        Item i = new Item(rs.getInt("iID"), rs.getString("name"), rs.getInt("size"), rs.getInt("quantity"));
        items.add(i);
      }
      return items;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Item findById(int id) {
    String sql = "SELECT * FROM Item WHERE iID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        return new Item(rs.getInt("iID"), rs.getString("name"), rs.getInt("size"), rs.getInt("quantity"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Item addNew(Item item) {
    // POST
    String sql = "INSERT INTO Item (name, size, quantity) VALUES (?, ?, ?)";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, item.getName());
      ps.setInt(2, item.getSize());
      ps.setInt(3, item.getQuantity());
      
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected != 0) {
        conn.commit();
        return item;
      }
      else {
        conn.rollback();
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void ship(Item item) {
    // DEL
    String sql = "DELETE FROM Item WHERE iID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, item.getiID());
      
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
  public void ship(List<Item> items) {
    // DEL many
    for (Item i : items) {
      ship(i);
    }
  }
  
}