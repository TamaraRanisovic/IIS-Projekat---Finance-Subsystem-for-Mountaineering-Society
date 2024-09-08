$(document).on("submit", "form", function (event) {

    event.preventDefault();

    var email = $("#email").val();
    var password = $("#password").val();


    var login = {
        email,
        password

    }
    console.log(login);


    $.ajax({
        type: "POST",
        url: "http://localhost:8011/api/users/login",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(login),
        success: function (res) {
            console.log(res);
            var id1 = res.id;

            if(res.role === "MEMBER"){


                    localStorage.setItem("id", res.id);
                    localStorage.setItem("role", "MEMBER");
                    window.location.href = "member.html"

            }else if(res.role === "ADMINISTRATOR"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "ADMINISTRATOR");
                window.location.href = "administrator.html"
            }else if(res.role === "MANTOURS"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "MANTOURS");
                window.location.href = "mantours.html"
            }
            else if(res.role === "POHADJACOBRAZOVNIHPROG"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "POHADJACOBRAZOVNIHPROG");
                window.location.href = "pohadjacobrazovnihprog.html"
            }
            else if(res.role === "MANFINANCE"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "MANFINANCE");
                window.location.href = "financemanager.html"
            }
            else if(res.role === "MANTOURS"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "MANTOURS");
                window.location.href = "mantours.html"
            }
            else if(res.role === "MANRACE"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "MANRACE");
                window.location.href = "managerrace.html"
            }
            else if(res.role === "PROFESOR"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("role", "PROFESOR");
                window.location.href = "profesor.html"
            }
            else
            {
                window.location.href="login.html";
            }

        },
        error: function () {
            alert("Niste uneli dobro korisnicko ime ili lozinku!");
        }

    });



});