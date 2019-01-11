$( document ).ready(function() {
        var loginuser=Cookies.get("email");
        if(loginuser==null){
            window.location.href="login.html";
        }else{
            $('#loginuser').html(loginuser);
        }
        $('#logout').click(function() {
            Cookies.remove("email");
            Cookies.remove("isAdmin");
            window.location.href="login.html";
        });
	$('.submit').click(function() {
		$('.result-box-inner').hide();
		var source=$('#source').val();
		var destination=$('#destination').val();
		var from=$('#from').val();
		var passengers=$('#passengers').val();
		
		//Add validation
		if(source == null || source==''){
			alert('Enter source');
			return false; 
		}
		
		if(from == null || from==''){
			alert('Enter from');
			return false;
		}
		
		if(passengers == null || passengers==''){
			alert('Enter passengers');
			return false;
		}
		
		var url="api/flightinfo/"+source+"/";
		if(destination!=null && destination!=''){
			url=url+destination+"/";
		}
		var date=new Date(from);
		date.setHours(0, -date.getTimezoneOffset(), 0, 0); //removing the timezone offset.
		url=url+date.toISOString()+"/"+passengers;
		
		$.ajax({
		    url: url,
		    type: 'GET',
		    success: function(data){ 
		        console.log(data);
		        if(data.flights != null){
		        	$("[id^='result_']").remove();
		        	$.each(data.flights, function(i, item) {
		        		var resultDiv=$('#placeholder_div').clone();
			        	resultDiv.attr('id','result_'+i);
			        	$(".names",resultDiv).html(data.airline);
			        	$(".source",resultDiv).html(item.origin);
			        	$(".destinationName",resultDiv).html(item.destination);
			        	$(".travel-time",resultDiv).html(item.travelTime + " Min");
			        	$(".price-pax",resultDiv).html("$"+item.totalPrice);
			        	
			        	var depDate=new Date(item.date);
			        	$(".starttime",resultDiv).html(formatAMPM(depDate));
			        	$(".bookbutton",resultDiv).attr("id",item.flightId);
			        	//$(".bookbutton",resultDiv).on("click", book(item.flightId));
			        	resultDiv.show();
			        	resultDiv.insertAfter('div.resultblock:last');
		        	});
		        	
		        	$('.result-box-inner').show();
		        }
		    },
		    error: function(data) {
		    	console.log(data);
		    	var response=$.parseJSON(data.responseText);
		    	alert(response.message);
		    }
		});
		
	});
	
	function formatAMPM(date) {
	  var hours = date.getHours();
	  var minutes = date.getMinutes();
	  var ampm = hours >= 12 ? 'pm' : 'am';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = hours + ':' + minutes + ' ' + ampm;
	  return strTime;
	}
});

function book(button) {
	var id=button.getAttribute('id');
	var noOfSeats=$('#passengers').val();
	Cookies.set("id",id);
	Cookies.set("seats",noOfSeats);
	window.location.href = "book.html";
}