package jdbc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    System.out.println("CORSFilter HTTP Request: " + request.getMethod());
    
    // Allow all domains to access content
    ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Origin", "*");
    ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
    
    HttpServletResponse response = (HttpServletResponse) resp;
    
    // For HTTP option methods, reply ACCEPTED with status code -- CORS handshake
    if (request.getMethod().equals("OPTIONS")) {
      response.setStatus(HttpServletResponse.SC_ACCEPTED);
      return;
    }
    
    // pass request along filter chain
    chain.doFilter(request, resp);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub
    
  }

}
