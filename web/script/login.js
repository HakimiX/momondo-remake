$( document ).ready(function() {
    $('#loginbutton').click(function() {
        var empty = $(this).parent().find("input").filter(function() {
        return this.value === "";
        });
        if(empty.length) {
            alert("Enter email or password");
            return false;
        }
        
        // All information is filled
        var email=$('#email').val();
        var password=$('#password').val();
        var loginRequest={};
        loginRequest['email']=email;
        loginRequest['password']=password;
        $.ajax({
            url: "api/login",
            type: 'POST',
            data: JSON.stringify(loginRequest),
            contentType: 'application/json',
            success: function(data){ 
                console.log(data);
                Cookies.set("email",data.email);
                Cookies.set("isAdmin",data.isAdmin);
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