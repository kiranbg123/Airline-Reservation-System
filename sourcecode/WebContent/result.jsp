<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p> </p>
<br><br>
<form action="index.jsp" method="get">
        <input name="home" type="submit" value="Home">
        </form>
        <table style="border: 1px solid; ">
            <tr>
                <td style="border: 1px solid;">FlightNumber</td>
                <td style="border: 1px solid;">Weekdays</td>
                <td style="border: 1px solid;">DepartureAirportCode</td>
            <td style="border: 1px solid;">ArrivalAirportCode</td>
            </tr>
        <%
            ArrayList totalInformation=(ArrayList)(session.getAttribute("sessionInformation"));
            ArrayList flightNumbers=(ArrayList) totalInformation.get(0);
            ArrayList weekdays=(ArrayList) totalInformation.get(1);
            ArrayList departureAirportCodes = (ArrayList) totalInformation.get(2);
            ArrayList arrivalAirportCodes = (ArrayList) totalInformation.get(3);
            for(int i=0;i<departureAirportCodes.size();i++)
            {%> 
            <tr>
            <td style="border: 1px solid;"><% out.print((String)flightNumbers.get(i)); %></td> 
            <td style="border: 1px solid;"><% out.print("     "+(String)weekdays.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)departureAirportCodes.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)arrivalAirportCodes.get(i)); %></td>
            </tr><%
            }
        %>
        </table>   <br>
        

</body>
</html>