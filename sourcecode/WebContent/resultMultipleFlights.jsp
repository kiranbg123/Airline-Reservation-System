<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
There are no direct flight between the entered Arrival airport and Departure Airport. Let us find the multiple connecting flights for you.
<% 
String arrivalAirport = (String) session.getAttribute("arrivalAirportCode");
String departureAirport = (String) session.getAttribute("departureAirportCode");
		
%>
<form action="multipleFlightInformation" method = "get" name="form" >
<input type="text" name="departureAirport" id="dpAir" value= <% out.print(departureAirport); %> />
to  
<input type="text" name="arrivalAirport"  id="arrAir" value= <% out.print(arrivalAirport); %> /><br/>
<br> Number of connecting Flights: 
  <select name="maxConnection">
    <option value="1">1</option>
    <option value="2">2</option>
    <br>
  </select>
     <br><input type="submit" value="Submit">
  
  <br><br><br>
</form>
<form action="index.jsp" method="get">
        <input name="home" type="submit" value="Home">
        </form>
</body>
</html>