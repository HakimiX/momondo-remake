$(document).ready(function () {
    load();

    $('#logout').click(function () {
        Cookies.remove("email");
        Cookies.remove("isAdmin");
        window.location.href = "login.html";
    });

    function load() {
        var loginuser = Cookies.get("email");
        if (loginuser == null) {
            window.location.href = "login.html";
        }

        var loginRequest = {};
        loginRequest['email'] = loginuser;
        $.ajax({
            url: "api/myreservations",
            type: 'POST',
            data: JSON.stringify(loginRequest),
            contentType: 'application/json',
            beforeSend: function () {
                $(".modal").show();
            },
            complete: function () {
                $(".modal").hide();
            },
            success: function (data) {
                console.log(data);
                if (data) {
                    var tbody = $('#reservationBody');
                    tbody.html("");
                    $.each(data, function (i, item) {
                        var row = "<tr>";
                        row = row + "<td>" + item.id + "</td>";
                        row = row + "<td>" + item.instance.fliesFrom[0].iataCode + "</td>";
                        row = row + "<td>" + item.instance.destination.iataCode + "</td>";
                        row = row + "<td>" + item.price + "</td>";
                        var passengers = "";
                        var total=item.passengers.length;
                        $.each(item.passengers, function (j, passenger) {
                            passengers = passengers + passenger.firstName + " " + passenger.lastName;
                            if(j!=total-1){
                                passengers=passengers+",";
                            }
                            
                        });
                        row = row + "<td>" + passengers + "</td>";
                        row += "</tr>";
                        tbody.append(row);
                    });
                }
            },
            error: function (data) {
                console.log(data);
                var response = $.parseJSON(data.responseText);
                alert(response.message);
            }
        });
    }
});