$(document).on("submit", "#registracija", function (event) {
    event.preventDefault();

    if(document.getElementById("uloga2").checked) {


        var password = $("#lozinka").val();
        var firstname = $("#ime").val();
        var lastname = $("#prezime").val();
        var phonenumber = $("#telefon").val();
        var email = $("#email").val();
        var dateofbirth = $("#datumrodjenja").val();
        var role = "PARTICIPANT";
    }
    else if(document.getElementById("uloga1").checked){

            var password = $("#lozinka").val();
            var firstname = $("#ime").val();
            var lastname = $("#prezime").val();
            var phonenumber = $("#telefon").val();
            var email = $("#email").val();
            var dateofbirth = $("#datumrodjenja").val();
            var role = "MEMBER";

        }else if(document.getElementById("uloga3").checked){

            var password = $("#lozinka").val();
            var firstname = $("#ime").val();
            var lastname = $("#prezime").val();
            var phonenumber = $("#telefon").val();
            var email = $("#email").val();
            var dateofbirth = $("#datumrodjenja").val();
            var role = "PROFESOR";
        }
    else if(document.getElementById("uloga4").checked){

        var password = $("#lozinka").val();
        var firstname = $("#ime").val();
        var lastname = $("#prezime").val();
        var phonenumber = $("#telefon").val();
        var email = $("#email").val();
        var dateofbirth = $("#datumrodjenja").val();
        var role = "POHADJACOBRAZOVNIHPROG";
    }



        var noviucesnik = {
            email,
            password,
            firstname,
            lastname,
            phonenumber,
            dateofbirth,
            role


        }
        console.log(noviucesnik);


        $.ajax({
            type: "POST",
            url: "http://localhost:8011/api/users/user",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(noviucesnik),
            success: function (res) {
                console.log(res);
                alert("Ucesnik" + res.id + " je uspesno kreiran!");
                window.location.href = "login.html";
            },
            error: function () {
                alert("Greška!");
            }
        });

        // var noviclandrustva = {
        //     email,
        //     password,
        //     firstname,
        //     lastname,
        //     phonenumber,
        //     dateofbirth,
        //     role
        //
        // }
        // console.log(noviclandrustva);
        //
        // let url = new URL('http://localhost:8011/api/users/user' );
        //
        // $.ajax({
        //     type: "POST",
        //     url: url,
        //     dataType: "json",
        //     contentType: "application/json",
        //     data: JSON.stringify(noviclandrustva),
        //     success: function (res) {
        //
        //         console.log(res);
        //         alert("Clan drustva " + res.id + " je uspesno kreiran!");
        //         window.location.href = "login.html";
        //     },
        //     error: function () {
        //         alert("Greška!");
        //     }

        // });}

});