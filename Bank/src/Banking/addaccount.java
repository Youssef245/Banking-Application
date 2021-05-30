package Banking;
/**
 * Name : Youssef Mohammed Fathi Abdelrassoul
 * ID : 20180352
 * Group : IS-S3
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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
 * Servlet implementation class addaccount
 */
@WebServlet("/addaccount")
public class addaccount extends HttpServlet {

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
            HttpSession session=request.getSession(); 
            
            
            Con=DriverManager.getConnection(url, user, password);
            Stmt=Con.createStatement();
            Customer currentCustomer = (Customer) session.getAttribute("SessionCustomer");
            String SQL = "insert into bankaccounts(BACreationDate,BACurrentBalance,CustomerID) "
                    + "VALUES(?,?,?)";
            PreparedStatement statement = Con.prepareStatement(SQL);  
            Date now = new Date();
            java.sql.Date sqlDate = new java.sql.Date(now.getTime());
			double balance=1000;
			String ID = currentCustomer.getID();
			statement.setDate(1, sqlDate);
			statement.setDouble(2, balance);
			statement.setString(3, ID);
			statement.executeUpdate();
			
			ResultSet RS = Stmt.executeQuery("Select * from bankaccounts where CustomerID = "+ID+" ; ");
			BankAccount newAcc = new BankAccount ();
			newAcc.setBalance(balance);
			RS.next();
			newAcc.setBankAccountNumber(RS.getInt("BankAccountID"));
			newAcc.setCreationDate(sqlDate.toString());
			currentCustomer.setAccount(newAcc);
			
			response.sendRedirect("Home.jsp");

			
			
            
            
            
            
            
            
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
