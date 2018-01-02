import java.io.*;
import java.awt.*;
import java.lang.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddData extends HttpServlet implements ActionListener
{
float grand_value;
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
 try
{
      String product_name = request.getParameter("product_name");
      String product_code = request.getParameter("product_code");
      String product_qty = request.getParameter("product_qty");
      String product_price = request.getParameter("product_price");
      String product_gst = request.getParameter("product_gst");

      out.println(product_name);
      out.println(product_code);
      out.println(product_qty);
      out.println(product_price);
      out.println(product_gst);
          grand_value=price+((gst*price)/100);
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con=DriverManager.getConection("jdbc:oracle:thin:@localhost:1521:xe","gst","gst");
      PreparedStatement pst = con.prepareStatement("insert into product values(?,?,?,?,?,?)");
      pst.setString(1,product_name);
      pst.setString(2,product_code);
      pst.setInt(3,product_qty);
      pst.setInt(4,product_price);
      pst.setInt(5,product_gst);
      pst.setInt(6,grand_value);
      int i = pst.executeUpdate();
      if(i!=0)
      {
        out.println("<br>Product has been added");
      }
      else
      {
        out.println("failed to added the data");
      }
    }
    catch (Exception e)
       {
      System.out.println(e);
    }
  }
 public static void main(String args[])
 {
  AddData ad=new AddData();
 }
}