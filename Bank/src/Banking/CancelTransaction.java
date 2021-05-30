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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CancelTransaction
 */
@WebServlet("/CancelTransaction")
public class CancelTransaction extends HttpServlet {
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
	 * @throws ParseException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
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
            int ID =Integer.parseInt(request.getParameter("val"));
            boolean flag=false;
            int index=0;
            BankTransaction newTran=new BankTransaction();
            int size=currentCustomer.getAccount().getTransactions().size();
            for(int i=0;i<size;i++)
            	if(ID==currentCustomer.getAccount().getTransaction(i).getID()) {
            		newTran=currentCustomer.getAccount().getTransaction(i);
            		index=i;
            		break;}
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(newTran.getCreationDate());
            Date now  = new Date ();
            java.sql.Date sqlDate = new java.sql.Date(now.getTime());
            long diffInMillies = Math.abs(now.getTime() - date1.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff<1)
            	flag=true;
            if(flag)
            {
            	String SQL="delete from banktransactions where BankTransactionID=?;";
        		PreparedStatement statement = Con.prepareStatement(SQL);
        		statement.setInt(1, ID);
        		statement.executeUpdate();
        		currentCustomer.getAccount().updateBalance(newTran.getAmount(), "Recieve");
        		 String SQL2="update bankaccounts set BACurrentBalance = BACurrentBalance + ? where BankAccountID=?;";
                 PreparedStatement statement2 = Con.prepareStatement(SQL2);
                 statement2.setDouble(1,newTran.getAmount());
                 statement2.setInt(2,currentCustomer.getAccount().getBankAccountNumber() );
                 statement2.executeUpdate();
                 String SQL3="update bankaccounts set BACurrentBalance = BACurrentBalance - ? where BankAccountID=?;";
                 PreparedStatement statement3 = Con.prepareStatement(SQL3);
                 statement3.setDouble(1,newTran.getAmount());
                 statement3.setInt(2,newTran.getToAccount());
                 statement3.executeUpdate();
                 currentCustomer.getAccount().transactions.remove(index);
                 newTran.setAmount(0);
                 response.sendRedirect("transactions");
            }
            else
            {
            	String errorMessage = "You Can Only Cancel within One Day , Sorry.";
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
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
