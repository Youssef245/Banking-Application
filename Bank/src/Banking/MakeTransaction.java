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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MakeTransaction
 */
@WebServlet("/MakeTransaction")
public class MakeTransaction extends HttpServlet {
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
            boolean flag2=false;
            ResultSet RS = null;
            HttpSession session=request.getSession(); 
            
            
            Con=DriverManager.getConnection(url, user, password);
            Stmt=Con.createStatement();
            Customer currentCustomer = (Customer) session.getAttribute("SessionCustomer");
            double amount = Double.parseDouble(request.getParameter("Amount"));
            int toacc = Integer.parseInt(request.getParameter("ToAcc"));
            if(toacc!=currentCustomer.getAccount().getBankAccountNumber())
            {
	            String SQL = "insert into banktransactions (BTCreationDate,BTAmount,BTFromAccount,BTTOAccount) "
	                    + "VALUES(?,?,?,?)";
	            PreparedStatement statement = Con.prepareStatement(SQL);
	            Date now = new Date();
	            java.sql.Date sqlDate = new java.sql.Date(now.getTime());
	            statement.setDate(1, sqlDate);
	            statement.setDouble(2, amount);
	            statement.setInt(3, currentCustomer.getAccount().getBankAccountNumber());
	            statement.setInt(4, toacc);
	            statement.executeUpdate();
	            RS = Stmt.executeQuery("Select * from bankaccounts");
	            while(RS.next())
	            {
	            	if(toacc==RS.getInt("BankAccountID")) {
	            		flag=true;
	            		break;
	            		}
	            }
	            
	            flag2 = currentCustomer.getAccount().updateBalance(amount, "Transfer");
            }
            
            if(flag&&flag2) {
            	Double newBalance=RS.getDouble("BACurrentBalance")+amount;
            	Double newBalance2=currentCustomer.getAccount().getBalance();
                String SQL2="update bankaccounts set BACurrentBalance = ? where BankAccountID=?;";
                PreparedStatement statement2 = Con.prepareStatement(SQL2);
                statement2.setDouble(1, newBalance);
                statement2.setInt(2, toacc);
                statement2.executeUpdate();
                statement2.setDouble(1, newBalance2);
                statement2.setInt(2, currentCustomer.getAccount().getBankAccountNumber());
                statement2.executeUpdate();
                	
                
                amount=0;
            	response.sendRedirect("transactions");       	
            }
            else
            {
            	String errorMessage = "Wrong Amount or Bank Account Number , Please Enter Right Information.";
                request.getRequestDispatcher("transactions.jsp").include(request, response);
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
