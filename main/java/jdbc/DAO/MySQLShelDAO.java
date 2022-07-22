package jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.conf.WherehousedCredentials;
import jdbc.models.Shelf;

public class MySQLShelDAO implements ShelfDAO {

  
  @Override // various gets
  public List<Shelf> findAll() {
    String sql = "SELECT * FROM Shelf";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      LinkedList<Shelf> shelves = new LinkedList<>();
      
      while (rs.next()) {
        Shelf sh = new Shelf(rs.getInt("shID"), rs.getInt("whID"), rs.getInt("capacity"));
        shelves.add(sh);
      }
      
      return shelves;
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Shelf> findByWid(int id) {
    String sql = "SELECT * FROM Shelf WHERE whID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      LinkedList<Shelf> shelves = new LinkedList<>();
      
      while (rs.next()) {
        Shelf sh = new Shelf(rs.getInt("shID"), rs.getInt("whID"), rs.getInt("capacity"));
        shelves.add(sh);
      }
      return shelves;
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Shelf> findByCap(int cap) {
    String sql = "SELECT * FROM Shelf WHERE capacity >= ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, cap);
      ResultSet rs = ps.executeQuery();
      LinkedList<Shelf> shelves = new LinkedList<>();
      
      while (rs.next()) {
        Shelf sh = new Shelf(rs.getInt("shID"), rs.getInt("whID"), rs.getInt("capacity"));
        shelves.add(sh);
      }
      return shelves;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Shelf findById(int id) {
    String sql = "SELECT * FROM Shelf WHERE shID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        return new Shelf(rs.getInt("shID"), rs.getInt("whID"), rs.getInt("capacity"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Shelf updateCapacity(Shelf shelf, int itemSize) {
    String sql = "UPDATE Shelf SET capacity = ? WHERE shID = ?";
    try (Connection conn = WherehousedCredentials.getInstance().getConnection()) {
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, shelf.getShID());
      shelf.setCapacity(shelf.getCapacity() - itemSize);
      ps.setInt(2, shelf.getCapacity());
      
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected != 0) {
        conn.commit();
        return shelf;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
}