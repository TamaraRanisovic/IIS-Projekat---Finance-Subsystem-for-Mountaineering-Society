$(document).ready(function (){
    if(localStorage.getItem('role') === "ADMINISTRATOR") {

        let urlParams = new URLSearchParams(window.location.search);
        let user = urlParams.get('id');

        if( user === 'undefined' || user === null ){
            alert("Niste odabrali korisnika");
            window.location.href = "sviuseri.html";
        }



        else{

            var role = localStorage.getItem('role');


            let url = new URL('http://localhost:8011/api/users/one');

            url.searchParams.append('user', user);
            url.searchParams.append('role', role);



            $.ajax({
                type: "GET",
                url: url,
                dataType: "json",

                success: function (res) {




                    $("input#firstname").val(res.firstname);
                    $("input#lastname").val(res.lastname);
                    $("input#phonenumber").val(res.phonenumber);
                    $("input#email").val(res.email);




                },
                error: function () {
                    alert("Greska!");
                    window.location.href = "sviuseri.html";
                }

            });}}

    else{
        if(localStorage.getItem("role") === "MEMBER"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        } else if(localStorage.getItem("role") === "PARTICIPANT"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        } else if(localStorage.getItem("role") === "MANFINANCE"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "financemanager.html";
        } else {
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }





});



$(document).on("click", '#user1', function () {


    if(localStorage.getItem('role') === "ADMINISTRATOR") {

        let urlParams = new URLSearchParams(window.location.search);
        let user = urlParams.get('id');

        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var phonenumber = $("#phonenumber").val();
        var email = $("#email").val();


        var user1 = {
            firstname,
            lastname,
            phonenumber,
            email
        }

        if(!firstname || !lastname || !phonenumber || !email){
            alert("Morate popuniti sve podatke!");
            return;
        }




        if(user === 'undefined' || user === null){
            alert("Niste odabrali fitnes centar");
            window.location.href = "sviuseri.html";
        }




        else{

            var role = localStorage.getItem('role');

            let url = new URL('http://localhost:8011/api/users/update');

            url.searchParams.append('user', user);
            url.searchParams.append('role', role);


            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(user1),
                success: function (res) {
                    alert("Uspesno ste izmenili korisnika!");
                    window.location.reload(true);
                },
                error: function () {
                    alert("Greška!");
                }
            });}

    }


    else{
        if(localStorage.getItem("role") === "MEMBER"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        }
        else if(localStorage.getItem("role") === "PARTICIPANT"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        } else if(localStorage.getItem("role") === "MANFINANCE"){
             alert("Nemate pristup ovoj stranici!");
             window.location.href = "financemanager.html";
                 }
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }




});