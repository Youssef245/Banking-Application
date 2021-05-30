package Banking;

/**
 * Name : Youssef Mohammed Fathi Abdelrassoul
 * ID : 20180352
 * Group : IS-S3
 */


import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/validate"})
public class validate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bankingsystem";
            String user="root";
            String password="root";
            Connection Con=null;
            Statement Stmt=null;
            boolean flag=false;
            
            Con=DriverManager.getConnection(url, user, password);
            Stmt=Con.createStatement();
            Customer loggedUser =new Customer ();
            String id = request.getParameter("ID");
            String pass = request.getParameter("password");
            ResultSet RS = Stmt.executeQuery("Select * from customers");
            while(RS.next())
            {
                if(id.equalsIgnoreCase(RS.getString("CustomerID"))){
                    if(pass.equalsIgnoreCase(RS.getString("Password")))
                    {
                    	loggedUser.setName(RS.getString("CustomerName"));
                    	loggedUser.setMobile(RS.getString("CustomerMobile"));
                    	loggedUser.setAddress(RS.getString("CustomerAdderss"));
                    	loggedUser.setID(id);
                    	ResultSet BA_RS = Stmt.executeQuery("Select * from bankaccounts");
                    	while(BA_RS.next())
                    	{
                    		if(BA_RS.getString("CustomerID").equalsIgnoreCase(id))
                    		{
                    			BankAccount account=new BankAccount ();
                    			account.setBankAccountNumber(BA_RS.getInt("BankAccountID"));
                    			account.setBalance(BA_RS.getDouble("BACurrentBalance"));
                    			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
                    			String strDate = BA_RS.getDate("BACreationDate").toString();
                    			account.setCreationDate(strDate);
                    			loggedUser.setAccount(account);
                    			 break;
                    		}
                    	}
                        flag=true;
                        break;
                    }
                }
                
            }
            if(flag)
            {
            	HttpSession session = request.getSession(true);
                session.setAttribute("SessionCustomer", loggedUser);
                response.sendRedirect("Home.jsp");
                
            }
            else
            {
            	String errorMessage = "Wrong ID or Password , Please Enter Right Information.";
                request.getRequestDispatcher("index.html").include(request, response);
                out.println("<script> alert('" + errorMessage + "'); </script>");
            }
            
            
            
            
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
