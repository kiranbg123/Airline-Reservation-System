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
        <table style="border: 1px solid; ">
            <tr>
                <td style="border: 1px solid;">Flight</td>
            <td style="border: 1px solid;">Price</td>
            <td style="border: 1px solid;">Restriction</td>
            
            </tr>
        <%
            ArrayList totalInformation=(ArrayList)(session.getAttribute("sessionInformation"));
            ArrayList flightNumbers=(ArrayList) totalInformation.get(0);
            ArrayList fares=(ArrayList) totalInformation.get(1);
            ArrayList restrictions=(ArrayList) totalInformation.get(2);
            
            for(int i=0;i<flightNumbers.size();i++)
            {%> 
            <tr>
            <td style="border: 1px solid;"><% out.print((String)flightNumbers.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)fares.get(i)); %></td>
             <td style="border: 1px solid;"><% out.print("     "+(String)restrictions.get(i)); %></td>
            </tr><%
            }
        %>
        </table>   <br>
        <form action="index.jsp" method="get">
        <input name="home" type="submit" value="Home">
        </form>
</body>
</html>