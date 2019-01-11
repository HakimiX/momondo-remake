$( document ).ready(function() {
	var loginuser=Cookies.get("email");
        if(loginuser==null){
            window.location.href="login.html";
        }
	var noOfSeats=Cookies.get("seats");
	// Clone no of passengers
	for(var i=0;i<noOfSeats-1;i++){
		var resultDiv=$('.passenger_info').clone();
    	resultDiv.attr('id','passenger_info_'+(i+1));
    	resultDiv.insertAfter('div.passenger_info:last');
	}
	
	$('#bookbutton').click(function() {
            var empty = $(this).parent().find("input").filter(function() {
            return this.value === "";
	    });
	    if(empty.length) {
	        alert("Please fill all the information");
	        return false;
	    }
	    
	    // All information is filled
	    var reserveeName=$('#reserveeName').val();
	    var reserveeEmail=Cookies.get("email");
	    var reserveePhone=$('#reserveePhone').val();
	    
	    
	    var passengers=[];
	    for(var i=0;i<noOfSeats;i++){
	    	var passengerDiv=$("[id^='passenger_info_"+i+"']");
	    	var firstname=$(".firstname",passengerDiv).val();
	    	var lastname=$(".lastname",passengerDiv).val();
	    	
	    	var passenger={};
	    	passenger['firstName']=firstname;
	    	passenger['lastName']=lastname;
	    	passengers[i]=passenger;
	    }
	    
	    var reservationRequest={};
	    reservationRequest['flightID']=Cookies.get("id");
	    reservationRequest['numberOfSeats']=noOfSeats;
	    reservationRequest['reserveeName']=reserveeName;
	    reservationRequest['reservePhone']=reserveePhone;
	    reservationRequest['reserveEmail']=reserveeEmail;
	    reservationRequest['passengers']=passengers;
	    
	    console.log(reservationRequest);
	    
	    $.ajax({
		    url: "api/flightreservation",
		    type: 'POST',
		    data: JSON.stringify(reservationRequest),
		    contentType: 'application/json',
		    success: function(data){ 
		        console.log(data);
		        alert("Reservation successful");
		        Cookies.remove("id");
                        Cookies.remove("seats");
		        window.location.href = "index.html";
		    },
		    error: function(data) {
		    	console.log(data);
		    	var response=$.parseJSON(data.responseText);
		    	alert(response.message);
		    }
		});
	    
	    return false;
	});
});