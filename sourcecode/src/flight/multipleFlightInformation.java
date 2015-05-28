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

/**
 * Servlet implementation class multipleFlights
 */
@WebServlet("/multipleFlightInformation")
public class multipleFlightInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public multipleFlightInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();	
		String db="jdbc:mysql://localhost:3306/airline_reservation";
		String departureAirport =request.getParameter("departureAirport");
		String arrivalAirport = request.getParameter("arrivalAirport");
		String maxConnection = request.getParameter("maxConnection");
		ArrayList<String> flightNumbers1 = new ArrayList<>();
		ArrayList<String> flightNumbers2 = new ArrayList<>();
		ArrayList<String> flightNumbers3 = new ArrayList<>();
		ArrayList<String> weekdays1 = new ArrayList<>();
		ArrayList<String> weekdays2 = new ArrayList<>();
		ArrayList<String> departureAirportCodes1 = new ArrayList<>();
		ArrayList<String> arrivalAirportCodes1 = new ArrayList<>();
		ArrayList<String> departureAirportCodes2 = new ArrayList<>();
		ArrayList<String> arrivalAirportCodes2 = new ArrayList<>();
		ArrayList<String> departureAirportCodes3 = new ArrayList<>();
		ArrayList<String> arrivalAirportCodes3 = new ArrayList<>();
		ArrayList<String> timeDifferences1 = new ArrayList<>();
		ArrayList<String> timeDifferences2 = new ArrayList<>();
		ArrayList<String> timeDifferences3 = new ArrayList<>();
		ArrayList total = new ArrayList();
		String passengerName = new String();
		String passengerEmail = new String();
		Connection con = null;
		String query = new String();
			try {
				System.out.println("Parameters: " + departureAirport + " " + arrivalAirport + " " + maxConnection);
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(db, "root", "password");
				PreparedStatement ps = null;
				String flightNumber1 = new String();
				String flightNumber2 = new String();
				String flightNumber3 = new String();
				String weekday1 = new String();
				String weekday2 = new String();
				String departureAirportCode1  = new String();
				String timeDifference1 = new String();
				String timeDiffrence2 = new String();
				String timeDiffernce3 = new String();
				String arrivalAirportCode1 = new String();
				String departureAirportCode2  = new String();
				String arrivalAirportCode2 = new String();
				String departureAirportCode3  = new String();
				String arrivalAirportCode3 = new String();
				if(maxConnection.equals("1"))
				{

	        	query =  " select f1.`Flight_number`,f2.`Flight_number`,f1.`departure_airport_code`,f2.`Departure_airport_code`,f2.`arrival_airport_code`,f1.`Weekdays`,f2.`Weekdays`,\n"
	        			+ "timediff(f2.`Scheduled_departure_time`,f1.`Scheduled_arrival_time`) from flight f1 join flight f2 on f1.`Arrival_airport_code`= f2.`Departure_airport_code` and f2.`Arrival_airport_code` = '"+arrivalAirport+"' where f1.`Departure_airport_code` = '"+departureAirport+"'  and timediff(f2.`Scheduled_departure_time`,f1.`Scheduled_arrival_time`)>'01:00:00'\n"
	        	 +"and ((f1.`Weekdays` LIKE '%Mon%' and f2.`Weekdays` LIKE '%Mon%') or (f1.`Weekdays` LIKE '%Tue%' and f2.`Weekdays` LIKE '%Tue%') or\n" 
	        	+"(f1.`Weekdays` LIKE '%Wed%' and f2.`Weekdays` LIKE '%Wed%') or\n" 
	        	+ "(f1.`Weekdays` LIKE '%Thu%' and f2.`Weekdays` LIKE '%Thu%') or\n"
	        	+"(f1.`Weekdays` LIKE '%Fri%' and f2.`Weekdays` LIKE '%Fri%') or\n" 
	        	+ "(f1.`Weekdays` LIKE '%Sat%' and f2.`Weekdays` LIKE '%Sat%') or\n" 
	        	+ "(f1.`Weekdays` LIKE '%Sun%' and f2.`Weekdays` LIKE '%Sun%'));";
				}
				else if(maxConnection.equals("2"))
				{
				/*	query =  " select f1.`Flight_number`,f2.`Flight_number`,f1.`departure_airport_code`,f2.`Departure_airport_code`,f2.`arrival_airport_code`,f1.`Weekdays`,f2.`Weekdays`,\n"
		        			+ "timediff(f2.`Scheduled_departure_time`,f1.`Scheduled_arrival_time`), f3.flight_number,f3.arrival_airport_code,timediff(f3.`Scheduled_departure_time`,f2.`Scheduled_arrival_time`) from flight f1 join flight f2 on f1.`Arrival_airport_code`= f2.`Departure_airport_code`\n"
							+ "join flight f3 on f2.`Arrival_airport_code`= f3.`Departure_airport_code` and f3.`Arrival_airport_code` = '"+arrivalAirport+"' where f1.`Departure_airport_code` = '"+departureAirport+"' and f2.`Arrival_airport_code` != '"+arrivalAirport+"' and f3.`Departure_airport_code` != '"+departureAirport+"' and \n"
		        			+"timediff(f2.`Scheduled_departure_time`,f1.`Scheduled_arrival_time`)>'01:00:00'\n"
		        	 +"and ((f1.`Weekdays` LIKE '%Mon%' and f2.`Weekdays` LIKE '%Mon%' and f3.`Weekdays` LIKE '%Mon%') or\n"
		        	+"(f1.`Weekdays` LIKE '%Tue%' and f2.`Weekdays` LIKE '%Tue%' and f3.`Weekdays` LIKE '%Tue%') or\n" 
		        	+"(f1.`Weekdays` LIKE '%Wed%' and f2.`Weekdays` LIKE '%Wed%' and f3.`Weekdays` LIKE '%Wed%') or\n" 
		        	+ "(f1.`Weekdays` LIKE '%Thu%' and f2.`Weekdays` LIKE '%Thu%' and f3.`Weekdays` LIKE '%Thu%') or\n"
		        	+"(f1.`Weekdays` LIKE '%Fri%' and f2.`Weekdays` LIKE '%Fri%' and  f3.`Weekdays` LIKE '%Fri%') or\n" 
		        	+ "(f1.`Weekdays` LIKE '%Sat%' and f2.`Weekdays` LIKE '%Sat%' and f3.`Weekdays` LIKE '%Sat%') or\n" 
		        	+ "(f1.`Weekdays` LIKE '%Sun%' and f2.`Weekdays` LIKE '%Sun%' and f3.`Weekdays` LIKE '%Sun%' ));";
				*/
					
				query =  "select f1.`Flight_number`,f2.`Flight_number`,f1.`departure_airport_code`,f2.`Departure_airport_code`,f2.`arrival_airport_code`,f1.`Weekdays`,f2.`Weekdays`,\n"
						 +"timediff(f2.`Scheduled_departure_time`,f1.`Scheduled_arrival_time`), timediff(f3.`Scheduled_departure_time`,f2.`Scheduled_arrival_time`),f3.`Flight_number`,f3.`arrival_airport_code` from flight f1 join flight f2 on f1.`Arrival_airport_code`= f2.`Departure_airport_code`\n"
						 + "join flight f3 on f2.`Arrival_airport_code`= f3.`Departure_airport_code`\n"
						 + "and f3.`Arrival_airport_code` = '"+arrivalAirport+"' where f1.`Departure_airport_code` = '"+departureAirport+"'  and f2.`Arrival_airport_code` != '"+arrivalAirport+"' and f1.`Arrival_airport_code` != '"+arrivalAirport+"' and f3.`Departure_airport_code` != '"+departureAirport+"'and\n"
						+ "timediff(f2.`Scheduled_departure_time`,f1.`Scheduled_arrival_time`)>'01:00:00' and timediff(f3.`Scheduled_departure_time`,f2.`Scheduled_arrival_time`)>'01:00:00'\n"
						+" and\n" 
						+"((f1.`Weekdays` LIKE '%Mon%' and f2.`Weekdays` LIKE '%Mon%' and f3.`Weekdays` LIKE '%Mon%') or\n" 
						+"(f1.`Weekdays` LIKE '%Tue%' and f2.`Weekdays` LIKE '%Tue%' and f3.`Weekdays` LIKE '%Tue%') or\n" 
						+"(f1.`Weekdays` LIKE '%Wed%' and f2.`Weekdays` LIKE '%Wed%' and f3.`Weekdays` LIKE '%Wed%') or\n" 
						+"(f1.`Weekdays` LIKE '%Thu%' and f2.`Weekdays` LIKE '%Thu%' and f3.`Weekdays` LIKE '%Thu%') or\n" 
						+"(f1.`Weekdays` LIKE '%Fri%' and f2.`Weekdays` LIKE '%Fri%' and f3.`Weekdays` LIKE '%fri%') or\n" 
						+"(f1.`Weekdays` LIKE '%Sat%' and f2.`Weekdays` LIKE '%Sat%' and f3.`Weekdays` LIKE '%Sat%') or\n" 
						+"(f1.`Weekdays` LIKE '%Sun%' and f2.`Weekdays` LIKE '%Sun%' and f3.`Weekdays` LIKE '%Sun%'));";
				}
				
	            
				ps = con.prepareStatement(query);
				
				
			
	        // System.out.println(q);
				ResultSet rs=ps.executeQuery();
				if(!rs.next())
				{
					System.out.println("Redirecting to resultMultopleFlights.jsp");
					session.setAttribute("arrivalAirportCode", arrivalAirport);
					session.setAttribute("departureAirportCode", departureAirport);
					
					response.sendRedirect("error.jsp");
				}
				else
				{
					rs.beforeFirst();
	         while(rs.next())
	         {
	        	 flightNumber1 = Integer.toString(rs.getInt(1));
	        	 flightNumber2 = Integer.toString(rs.getInt(2));
	        	 weekday1 = rs.getString(6);
	        	 weekday2 = rs.getString(7);
	        	 departureAirportCode1 = rs.getString(3);
	        	 //arrivalAirportCode1 = rs.getString();
	        	 timeDifference1 = rs.getString(8);
	        	 departureAirportCode2 = rs.getString(4);
	        	 arrivalAirportCode2 = rs.getString(5);
	        	
	        	 
	        	 if(maxConnection.equals("2"))
	        	 {
	        		 
	        		 timeDiffernce3 = rs.getString(9);
	        		 flightNumber3 =rs.getString(10);
	        		 arrivalAirportCode3 = rs.getString(11);
	        	 }
	         
	        	// System.out.println("flightNumber: " + flightNumber);
	        	 flightNumbers1.add(flightNumber1);
	        	 
	        	 weekdays1.add(weekday1);
	        	 departureAirportCodes1.add(departureAirportCode1);
	        	 //arrivalAirportCodes1.add(arrivalAirportCode1);
	        	 departureAirportCodes2.add(departureAirportCode2);
	        	 arrivalAirportCodes2.add(arrivalAirportCode2);
	        	 timeDifferences1.add(timeDifference1);
	        	 flightNumbers2.add(flightNumber2);
	        	 //timeDifferences2.add(timeDiffrence2);
	        	 if(maxConnection.equals("2"))
	        	 {
	        		 
	        		 arrivalAirportCodes3.add(arrivalAirportCode3);
	        		 flightNumbers3.add(flightNumber3);
	        		 timeDifferences2.add(timeDiffernce3);
	        		 
	        	 }
	        	 
	        	 
	         }
	         total.add(flightNumbers1);
	         total.add(weekdays1);
	         total.add(departureAirportCodes1);
	         total.add(arrivalAirportCodes1);
	         total.add(departureAirportCodes2);
	         total.add(arrivalAirportCodes2);
	         total.add(timeDifferences1);
	         total.add(flightNumbers2);
	        // total.add(timeDifferences2);
	         if(maxConnection.contains("2"))
	         {
	        	 total.add(arrivalAirportCodes3);
	        	 total.add(flightNumbers3);
	        	 total.add(timeDifferences2);
	         }
	         System.out.println("done!!!!");
	         System.out.println("Result form DB :"); 
	         session.setAttribute("sessionInformation",total); 
	         System.out.println("redirecting to available result.jsp");
	         session.setAttribute("sessionInformation",total); 
	        // JOptionPane.showMessageDialog(null, "joptn done!");
	         if(maxConnection.contains("1"))
	        		 {
	        	 response.sendRedirect("multipleFlightInformation1.jsp");
	        		 }
	         else if(maxConnection.contains("2"))
	         {
	        	 response.sendRedirect("multipleFlightInformation2.jsp");
	         }
				
			}
			}
			
			 catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
