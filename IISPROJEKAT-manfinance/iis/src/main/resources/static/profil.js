if(localStorage.getItem('role')===("PARTICIPANT") || localStorage.getItem('role')===("MANFINANCE")) {
    $(document).ready(function () {
        var user = localStorage.getItem('id');
        var role = localStorage.getItem('role');

        let url = new URL('http://localhost:8011/api/users/profile');

        url.searchParams.append('role', role);

        url.searchParams.append('user', user);

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",

            success: function (res) {

                $("input#pass").val(res.password);
                $("input#email").val(res.email);
                $("input#dateofbirth").val(res.dateofbirth);
                $("input#firstname").val(res.firstname);
                $("input#lastname").val(res.lastname);
                $("input#phonenumber").val(res.phonenumber);

            },
            error: function () {
                alert("Greska!");
            }

        });

    });
}
else{
    if(localStorage.getItem("role") === "MEMBER"){
        alert("Nemate pristup ovoj stranici!");

        window.location.href = "member.html";
    }
    else if(localStorage.getItem("role") === "ADMINISTRATOR"){
        alert("Nemate pristup ovoj stranici!");

        window.location.href = "administrator.html";
    }
    else{
        alert("Nemate pristup ovoj stranici!");

        window.location.href ="login.html";
    }

}


if(localStorage.getItem('role')===("PARTICIPANT") || localStorage.getItem('role')===("MANFINANCE")) {
    $(document).on("submit", "#profilkorisnika", function (event) {
        event.preventDefault();

        var password = $("#pass").val();
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var phonenumber = $("#phonenumber").val();
        var email = $("#email").val();
        var dateofbirth = $("#dateofbirth").val();
        var role = localStorage.getItem('role');

        let url = new URL('http://localhost:8011/api/users/updateparticipant');

        url.searchParams.append('role', role);

        url.searchParams.append('user', localStorage.getItem("id"));


        var izmenjeniucesnik = {
            firstname,
            lastname,
            email,
            password,
            dateofbirth,
            phonenumber,
            role
        }

        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(izmenjeniucesnik),
            success: function (res) {

                alert("Uspesno ste izmenili podatke!");

            },
            error: function () {
                alert("Niste uspeli da izmenite podatke!");
            }
        });});}
else{
    if(localStorage.getItem("role") === "TRENER"){
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "trener.html";
    }
    else if(localStorage.getItem("role") === "ADMINISTRATOR"){
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "administrator.html";
    }
    else{
        alert("Nemate pristup ovoj stranici!");
        window.location.href ="login.html";
    }
}