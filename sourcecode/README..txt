How to Run:

1)Import the files into the IDE(Eclipse IDE has been used for the development).
2)Import the database(MySql is used to design the database
	mysql -u root -p <database_name> < airline_reservation_final_submission.sql

3)Run the Airline_Reservation project from eclipse on the server.(Apache 2.49 and Tomcat v7.0 is used for Server Configuration)
	
Technologies used: Java, Servelet, JSP, HTML, JS, MySql, Apache 2.4, Tomcat v7, Eclipse IDE.


Queries:

1)Displaying the Flight Information given Departure Airport and Arrival Airport

If the Direct flight exists between the Departure Airport and Arrival Airport the flights are shown
else the user is asked for maximum no of connecting flights up to 2.
Even then if the flights are not found, error message is displayed saying no data found.
It is observed that there are very high set of results for multiple connecting flights of size 2. Sometimes the browser has crashed due
to high volume of data.

2)Displaying available Seats given Flight number and Data
If the flight exists in the "flight_instance" table for the given flight number and date, available seats are shown 
else "data not found" message is displayed. Date should be in "YYYY-MM-DD" format

3)Displaying fare Information given flight number
Fare information is provided for the flights found in "fare" table else error message is shown.

4) Display passenger List given Flight Number and Date
Passenger list is shown for all the passengers present in the seat_reservation table for the given date.
Date should be in YYYY-MM-DD format.

5)Light of Flight for the given passenger Name
Flight details are shown for the passenger present in "seat_reservation" table.