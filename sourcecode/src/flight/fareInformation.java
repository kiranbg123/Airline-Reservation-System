package flight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class fareInformation
 */
@WebServlet("/fareInformation")
public class fareInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fareInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String db="jdbc:mysql://localhost:3306/airline_reservation";
		HttpSession session=request.getSession();
		String flightNumber=request.getParameter("flightNumber");
		ArrayList<String> flightNumbers = new ArrayList<>();
		ArrayList<String> fares = new ArrayList<>();
		ArrayList<String> restrictions = new ArrayList<>();
		ArrayList total = new ArrayList();
		 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 System.out.println("connection done!!!");

			 String query  = new String();
				if(flightNumber.equals("") || flightNumber.equals(null)) 
				{
					/*System.out.println("Entering null value check condition");
					 JOptionPane.showMessageDialog(null,"Please enter the detials correctly!");
		                response.sendRedirect("index.jsp");*/
					System.out.println("Entering null value or blank value check");
					//Select fares of all the flights
					
					//	 response.sendRedirect("index.jsp");
					 
				}
				else
				{
					query = "select * from fare where flight_number = '"+flightNumber+"'";
				
				
				Connection con = DriverManager.getConnection(db, "root", "password");
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				System.out.println("Flight Number is " + flightNumber);
				
				//String query = "select * from flight_instance where flight_number = '2419' ";
				//String query = "select * from flight_instance";
				java.sql.PreparedStatement ps = null;
				ps = con.prepareStatement(query);
				java.sql.ResultSet rs = ps.executeQuery();
				System.out.println("connection done!!!");
				
				//Store the result of the query into appropriate arrayList
				String flight_number = new String();
				String amount = new String();
				String restriction = new String();
				//String seat = new String();
				if(!rs.next())
				{
					System.out.println("No flights available");
					response.sendRedirect("error.jsp");
				}
				else
				{
					rs.beforeFirst();
				while(rs.next())
				{
					flight_number= rs.getString(1);
					//dateOfFlight = rs.getString(2);
					restriction = rs.getString(4);
					amount = rs.getString(3);
					
					//Add them into arrayList
					flightNumbers.add(flight_number);
					fares.add(amount);
					restrictions.add(restriction);
				
					
					
				}
				
				total.add(flightNumbers);
				total.add(fares);
				total.add(restrictions);
				session.setAttribute("sessionInformation", total);
				System.out.println("redirecting to available fareInfo.jsp");
				response.sendRedirect("fareInfo.jsp");
				}
				}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
