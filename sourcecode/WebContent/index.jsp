<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<h3> Airline Reservation System!!!</h3>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
		
		function checkNull(obj) {
			
			if(obj.value==''){
				alert("Enter Appropriate value");
			}

		}
		
		function validateForm1() {
		    var x = document.forms["form1"]["departureAirport"].value;
		    var y = document.forms["form1"]["arrivalAirport"].value;
		    if ((x == null || x == "") || (y == null || y == "")){
		        alert("Values must not be empty");
		        return false;
		    }
		}
		
		function validateForm2() {
		    var x = document.forms["form2"]["flightNumber"].value;
		    var y = document.forms["form2"]["date"].value;
		    if ((x == null || x == "") || (y == null || y == "")){
		        alert("Values must not be empty");
		        return false;
		    }
		}
		    
		    function validateForm3() {
			    var x = document.forms["form3"]["flightNumber"].value;
			   
			    if (x == null || x == ""){
			        alert("Values must not be empty");
			        return false;
			    }
			}
		    
		    function validateForm4() {
			    var x = document.forms["form4"]["flightNumber"].value;
			    var y = document.forms["form4"]["date"].value;
			    if ((x == null || x == "") || (y == null || y == "")){
			        alert("Values must not be empty");
			        return false;
			    }
			}
		    
		    function validateForm5() {
			    var x = document.forms["form5"]["passenger"].value;
			    
			    if ((x == null || x == "")){
			        alert("Values must not be empty");
			        return false;
			    }
			}
</script>
<title>Insert title here</title>
</head>
<body>
<p> Display Flight Information </p>
<form action="flightInformation" method = "get" name = "form1" onsubmit = "return validateForm1()">  
<input type="text" name="departureAirport" id="dpAir" value="Departure Airport" onclick="this.value=''" />  
<input type="text" name="arrivalAirport"  id="arrAir" value="Arrival Airport" onclick="this.value=''" /><br/>    
<input type="submit" value="submit"/>
</form> 
<br>
<br>
<p> Display available Seats </p>
<form action="availableSeats" method = "get" name="form2" onsubmit="return validateForm2()">   
<input type="text" name="flightNumber"  value="flightNumber" onclick="this.value=''"/>
<input type="text" name="date"  value="Date(yyyy-mm-dd)" onclick="this.value=''" /><br/>    
<input type="submit" value="submit"/>
</form>
<br> 
<p> Display Fare Information </p>
<form action="fareInformation" method = "get" name="form3" onsubmit="return validateForm3()">   
<input type="text" name="flightNumber"  value="Flight Number" onclick="this.value=''" /><br>   
<input type="submit" value="submit"/>
</form>
<br> 
<p> Display Passenger List </p>
<form action="passengerList" method = "get" name="form4" onsubmit="return validateForm4()">   
<input type="text" name="flightNumber"  value="Flight Number" onclick="this.value=''" />
<input type="text" name="date"  value="Date(yyyy-mm-dd)" onclick="this.value=''" /><br/>   
<input type="submit" value="submit"/>
</form>

<br> 
<p> Display Light of Flights </p>
<form action="lightOfflight" method = "get" name="form5" onsubmit="return validateForm5()">   
<input type="text" name="passenger"  value="Passenger Name" onclick="this.value=''" /><br>   
<input type="submit" value="submit"/>
</form>
</body>
</html>