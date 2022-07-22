package jdbc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jdbc.DAO.ItemDAO;
import jdbc.DAO.MySQLItemDAO;
import jdbc.DAO.MySQLShelDAO;
import jdbc.DAO.MySQLWareDAO;
import jdbc.DAO.MySQLshItemsDAO;
import jdbc.DAO.ShelfDAO;
import jdbc.DAO.WarehouseDAO;
import jdbc.DAO.shelfItemsDAO;
import jdbc.models.Item;
import jdbc.models.NotFound;
import jdbc.models.Shelf;
import jdbc.models.Warehouse;
import jdbc.models.shelfItem;

@WebServlet(urlPatterns = "/api/warehouse/*")
public class WarehouseServlet extends HttpServlet {

  private static final long serialVersionUID = -5963721952084691303L;
  
  WarehouseDAO wareService = new MySQLWareDAO();
  ShelfDAO shelfService = new MySQLShelDAO();
  shelfItemsDAO sItemsService = new MySQLshItemsDAO();
  ItemDAO itemService = new MySQLItemDAO();
  
  
  ObjectMapper mapper = new ObjectMapper();
  URLParser parser = new URLParser();
  
  
  /**
   * Servlet lifecycle
   */
  
  @Override
  public void init() throws ServletException {
    super.init();
  }
  
  @Override
  public void destroy() {
    super.destroy();
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pathInfo = req.getPathInfo();
    String path = parser.extractPathOnly(req.getRequestURI().substring(req.getContextPath().length()));
    System.out.println(parser.extractIdFromURL(pathInfo));
    //System.out.println(path);
    int id = parser.extractIdFromURL(pathInfo);
    
    //api paths
    switch(path) {
      case "/api/warehouse/warehouse":
          // tries to grab 1 if specified
          if (id != 0) {
            System.out.println(id);
            Warehouse w = wareService.findById(id);
            if (w != null) {
              resp.setContentType("application/json");
              resp.getWriter().print(mapper.writeValueAsString(w));
            } else {
              resp.setStatus(404);
              resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
            }
          } else {
            List<Warehouse> wHouses = wareService.findAll();
            System.out.println(wHouses);
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(wHouses));
          }
          break;
              
      case "/api/warehouse/shelf":
        if (id != 0) {
          System.out.println(id);
          List<Shelf> s = shelfService.findByWid(id);
          if (s != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(s));
          } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
          }
        } else {
          List<Shelf> wShelves = shelfService.findAll();
          System.out.println(wShelves);
          resp.setContentType("application/json");
          resp.getWriter().print(mapper.writeValueAsString(wShelves));
        }
        break;
        
      case "/api/warehouse/shelfItems":
        if (id != 0) {
          System.out.println(id);
          List<shelfItem> sI = sItemsService.getShelf(id);
          if (sI != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(sI));
          } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
          }
        } else {
          List<shelfItem> sIs = sItemsService.getAll();
          System.out.println(sIs);
          resp.setContentType("application/json");
          resp.getWriter().print(mapper.writeValueAsString(sIs));
        }
        break;
        
      case "/api/warehouse/item":
        if (id != 0) {
          System.out.println(id);
          Item i = itemService.findById(id);
          if (i != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(i));
          } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
          }
        } else {
          List<Item> i_s = itemService.findAll();
          System.out.println(i_s);
          resp.setContentType("application/json");
          resp.getWriter().print(mapper.writeValueAsString(i_s));
        }
        break;
        /**
        try { // tries to grab 1 if specified
          int id = parser.extractIdFromURL(path);
          List<Shelf> s = shelfService.findByWid(id);
          if (s != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(s));
          } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
          }
        } catch (Exception e) {
          List<Shelf> wShelves = shelfService.findAll();
          System.out.println(wShelves);
          resp.setContentType("application/json");
          resp.getWriter().print(mapper.writeValueAsString(wShelves));
        }
      
      case "/api/warehouse/shelfItems":
        try { // tries to grab 1 if specified
          int id = parser.extractIdFromURL(path);
          List<shelfItem> sI = sItemsService.getShelf(id);
          if (sI != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(sI));
          } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
          }
        } catch (Exception e) {
          List<shelfItem> sIs = sItemsService.getAll();
          System.out.println(sIs);
          resp.setContentType("application/json");
          resp.getWriter().print(mapper.writeValueAsString(sIs));
        }
      
      case "/api/warehouse/item":
        try { // tries to grab 1 if specified
          int id = parser.extractIdFromURL(path);
          Item i = itemService.findById(id);
          if (i != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(i));
          } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with this ID")));
          }
        } catch (Exception e) {
          List<Item> i_s = itemService.findAll();
          System.out.println(i_s);
          resp.setContentType("application/json");
          resp.getWriter().print(mapper.writeValueAsString(i_s));
        } */
      default:
        System.out.println("No paths resolved");
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String pathInfo = req.getPathInfo();
    String path = parser.extractPathOnly(req.getRequestURI().substring(req.getContextPath().length()));
    System.out.println(parser.extractIdFromURL(pathInfo));
    //System.out.println(path);
    int id = parser.extractIdFromURL(pathInfo);
    
    switch(path) {
      case "/api/warehouse/warehouse":
        
      case "/api/warehouse/shelf":
      
      case "/api/warehouse/shelfItems":
        
      case "/api/warehouse/item":
        
      default:
        System.out.println("No paths resolved");
    }
  }
  
}
