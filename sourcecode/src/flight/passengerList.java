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
 * Servlet implementation class passengerList
 */
@WebServlet("/passengerList")
public class passengerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public passengerList() {
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
        String date = request.getParameter("date");
        ArrayList<String> flights = new ArrayList<>();
		ArrayList<String> dates = new ArrayList<>();
		ArrayList<String> seats = new ArrayList<>();
        
        ArrayList<String> customerNames = new ArrayList<>();
        ArrayList total = new ArrayList();
		 try {
			 if(flightNumber.equals("") || date.equals("")) 
				{
					System.out.println("Entering null value check condition");
					 JOptionPane.showMessageDialog(null,"Please enter the detials correctly!");
		                response.sendRedirect("index.jsp");
				}
				else 
				{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection con = DriverManager.getConnection(db, "root", "password");
				
				//System.out.println("Flight Number is " + flightNumber + " Date is "+ date);
				String query = "select flight_number,date, seat_number,customer_name from seat_reservation where flight_number = '"+flightNumber+"' and date= '"+date+"'";
				//String query = "select * from flight_instance where flight_number = '2419' ";
				//String query = "select * from flight_instance";
				java.sql.PreparedStatement ps = null;
				ps = con.prepareStatement(query);
				java.sql.ResultSet rs = ps.executeQuery();
				System.out.println("connection done!!!");
				
				//Store the result of the query into appropriate arrayList
				String flightName = new String();
				String dateOfFlight = new String();
				String customerName = new String();
				String seat = new String();
				if(!rs.next())
				{
					System.out.println("No flights available");
					response.sendRedirect("error.jsp");
				}
				else
				{
					// Should not loose the first row
					rs.beforeFirst();
				while(rs.next())
				{
					flightName = rs.getString(1);
					dateOfFlight = rs.getString(2);
					seat = rs.getString(3);
					customerName = rs.getString(4);
					
					//Add them into arrayList
					flights.add(flightName);
					dates.add(dateOfFlight);
					seats.add(seat);
					customerNames.add(customerName);
					
				}
				
				total.add(flights);
				total.add(dates);
				total.add(seats);
				total.add(customerNames);
				session.setAttribute("sessionInformation", total);
				System.out.println("redirecting to available passengerList.jsp");
				response.sendRedirect("passengerList.jsp");
				}
				}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         catch (SQLException e) {
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
