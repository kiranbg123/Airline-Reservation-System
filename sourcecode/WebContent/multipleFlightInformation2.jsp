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
<br><br>
<form action="index.jsp" method="get">
        <input name="home" type="submit" value="Home">
        </form>
        <br>
        <table style="border: 1px solid; ">
            <tr>
            <td style="border: 1px solid;">Flight Number</td>
                <td style="border: 1px solid;">Weekdays</td>
            <td style="border: 1px solid;">Departure Airport</td>
            <td style="border: 1px solid;">Arrival Airport</td>
            <td style="border: 1px solid;">Time Difference</td>
            <td style="border: 1px solid;">Flight Number</td>
            <td style="border: 1px solid;">Departure Airport</td>
            <td style="border: 1px solid;">Arrival Airport</td>
            <td style="border: 1px solid;">Time Difference</td>
            <td style="border: 1px solid;">Flight Number</td>
            <td style="border: 1px solid;">Departure Airport</td>
            <td style="border: 1px solid;">Arrival Airport</td>
            
            </tr>
        <%
            ArrayList totalInformation=(ArrayList)(session.getAttribute("sessionInformation"));
            
        	ArrayList flightNumbers1 =(ArrayList) totalInformation.get(0);
            ArrayList weekdays1 = (ArrayList) totalInformation.get(1);
            ArrayList departureAirportCodes1 = (ArrayList) totalInformation.get(2);
            ArrayList arrivalAirportCodes1 = (ArrayList) totalInformation.get(3);
            ArrayList arrivalAirportCodes3 = (ArrayList) totalInformation.get(8);
            ArrayList departureAirportCodes2 = (ArrayList) totalInformation.get(4);
            ArrayList arrivalAirportCodes2 =(ArrayList) totalInformation.get(5);
            ArrayList timeDiffrences1 = (ArrayList) totalInformation.get(6);
            ArrayList timeDiffrences2 = (ArrayList) totalInformation.get(10);
            ArrayList flightNumbers2 = (ArrayList) totalInformation.get(7);
            ArrayList flightNumbers3 = (ArrayList) totalInformation.get(9);
            
            
            
            for(int i=0;i<flightNumbers1.size();i++)
            { %> 
            <tr>
            <td style="border: 1px solid;"><% out.print((String)flightNumbers1.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)weekdays1.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)departureAirportCodes1.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)departureAirportCodes2.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)timeDiffrences1.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)flightNumbers2.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)departureAirportCodes2.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)arrivalAirportCodes2.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)timeDiffrences2.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)flightNumbers3.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)arrivalAirportCodes2.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)arrivalAirportCodes3.get(i)); %></td>
            </tr><%
            }
        %>
        </table>   <br>
        <form action="index.jsp" method="get">
        <input name="home" type="submit" value="Home">
        </form>
</body>
</html>