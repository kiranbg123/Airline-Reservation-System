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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class availableSeats
 */
@WebServlet("/availableSeats")
public class availableSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public availableSeats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String flightNumber=request.getParameter("flightNumber");
        String date = request.getParameter("date");
		String db="jdbc:mysql://localhost:3306/airline_reservation";
		ArrayList<String> flights = new ArrayList<>();
		ArrayList<String> dates = new ArrayList<>();
		ArrayList<String> seats = new ArrayList<>();
		
		ArrayList total = new ArrayList();
		 try {
			 
			if(flightNumber.equals("") || date.equals("")) 
			{
				
				System.out.println("Entering null value check condition");
				//request.setAttribute("msg","One of the value is null");
				// JOptionPane.showMessageDialog(null,"please enter ALL the details!");
				//out.println("alert(\"" +msg+ "\")");
				//JOptionPane.showMessageDialog(null, "Please enter the detials correctly!");
	             //System.out.println("joptionpane hit..");
				/*SwingUtilities.invokeLater(new Runnable() {
			         public void run() {
			            JOptionPane.showMessageDialog(null," Empty values can not be given as Input");
			         }
			      });*/
				
			}
			else 
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(db, "root", "password");
			
			System.out.println("Flight Number is " + flightNumber + " Date is "+ date);
			//String query = "select * from flight_instance where flight_number = '"+flightNumber+"' and date= '"+date+"'";
			String query = " Select ((select a.Total_no_of_seats from Flight_Instance f join airplane a on f.Airplane_id=a.Airplane_id  where Flight_number='"+flightNumber+"'AND Date='"+date+"')-(select COUNT(*) from Seat_reservation s where s.Flight_Number='"+flightNumber+"'AND Date='"+date+"')) as Available_seats";
			String queryTest = "select a.Total_no_of_seats from Flight_Instance f join airplane a on f.Airplane_id=a.Airplane_id  where Flight_number='"+flightNumber+"'AND Date='"+date+"'" ;
			//String query = "select * from airplane";
			//String query = "select * from flight_instance where flight_number = '2419' ";
			//String query = "select * from flight_instance";
			java.sql.PreparedStatement ps = null;
			ps = con.prepareStatement(query);
			java.sql.ResultSet rs = ps.executeQuery();
			//this is to find if the result has 0 sets
			java.sql.PreparedStatement psTest = null;
			ps = con.prepareStatement(queryTest);
			java.sql.ResultSet rsTest = ps.executeQuery();
			System.out.println("connection done!!!");
			
			//Store the result of the query into appropriate arrayList
			String flightName = new String();
					flightName = flightNumber;
			String dateOfFlight = new String();
					dateOfFlight = date;
			String seat = new String();
			if(!rsTest.next())
			{
				System.out.println("No flights available");
				response.sendRedirect("error.jsp");
				
			}
			else
			{
				//THis is not required as we are using seperate query 
				//rs.beforeFirst();
			while(rs.next())
			{
				//flightName = rs.getString(1);
				//dateOfFlight = rs.getString(2);
				seat = Integer.toString(rs.getInt(1));
				
				//Add them into arrayList
				flights.add(flightName);
				dates.add(dateOfFlight);
				seats.add(seat);
				
				
			}
			
			total.add(flights);
			total.add(dates);
			total.add(seats);
			session.setAttribute("sessionInformation", total);
			System.out.println("redirecting to available Seats.jsp");
			response.sendRedirect("availableSeats.jsp");
			}
		} 
		 }
          catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         catch (InstantiationException | IllegalAccessException
 				| ClassNotFoundException e) {
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
