package flight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class flightInformation
 */
@WebServlet("/flightInformation")
public class flightInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public flightInformation() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		
		HttpSession session=request.getSession();	
		String db="jdbc:mysql://localhost:3306/airline_reservation";
		String departureAirport =request.getParameter("departureAirport");
		String arrivalAirport = request.getParameter("arrivalAirport");
		ArrayList<String> flightNumbers = new ArrayList<>();
		ArrayList<String> weekdays = new ArrayList<>();
		ArrayList<String> departureAirportCodes = new ArrayList<>();
		ArrayList<String> arrivalAirportCodes = new ArrayList<>();
		ArrayList total = new ArrayList();
		String passengerName = new String();
		String passengerEmail = new String();
		Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
      
			 con = DriverManager.getConnection(db, "root", "password");
		
        System.out.println("connection done!!!");
        //.showMessageDialog(null,"please enter ALL the details!");
        String query = new String();
        
        if((departureAirport.equals("") || departureAirport.equals(null)) || (arrivalAirport.equals("") || arrivalAirport.equals(null) ) )
		{
			/*System.out.println("Entering null value check condition");
			 JOptionPane.showMessageDialog(null,"Please enter the detials correctly!");
                response.sendRedirect("index.jsp");*/
			System.out.println("Entering null value or blank value check");
			//Select fares of all the flights
			 query =  "select flight_number, weekdays, departure_airport_code, arrival_airport_code from flight";
			/* JOptionPane.showConfirmDialog(null,
                     "Please enter all details.",
                     "Inane warning",
                     JOptionPane.WARNING_MESSAGE);
				 response.sendRedirect("index.jsp"); */
		}
	
        else
        {
        	query = "select flight_number, weekdays, departure_airport_code, arrival_airport_code from flight where departure_airport_code ='"+departureAirport+"' and arrival_airport_code= '"+arrivalAirport+"'";
   
            PreparedStatement ps = null;
			ps = con.prepareStatement(query);
			
			String flightNumber = new String();
			String weekday = new String();
			String departureAirportCode  = new String();
			String arrivalAirportCode = new String();
		
        // System.out.println(q);
			ResultSet rs=ps.executeQuery();
			if(!rs.next())
			{
				System.out.println("Redirecting to resultMultopleFlights.jsp");
				session.setAttribute("arrivalAirportCode", arrivalAirport);
				session.setAttribute("departureAirportCode", departureAirport);
				
				response.sendRedirect("resultMultipleFlights.jsp");
			}
			else{
				rs.beforeFirst();
         while(rs.next())
         {
        	 flightNumber = Integer.toString(rs.getInt(1));
        	 weekday = rs.getString(2);
        	 departureAirportCode = rs.getString(3);
        	 arrivalAirportCode = rs.getString(4);
         
        	 System.out.println("flightNumber: " + flightNumber);
        	 flightNumbers.add(flightNumber);
        	 weekdays.add(weekday);
        	 departureAirportCodes.add(departureAirportCode);
        	 arrivalAirportCodes.add(arrivalAirportCode);
        	 
         }
         total.add(flightNumbers);
         total.add(weekdays);
         total.add(departureAirportCodes);
         total.add(arrivalAirportCodes);
         System.out.println("done!!!!");
         System.out.println("Result form DB :"); 
         session.setAttribute("sessionInformation",total); 
         System.out.println("redirecting to available result.jsp");
        // JOptionPane.showMessageDialog(null, "joptn done!");
        response.sendRedirect("result.jsp");
			}
		}
		}
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
