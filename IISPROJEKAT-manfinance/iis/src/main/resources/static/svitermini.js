if(localStorage.getItem("role") === "POHADJACOBRAZOVNIHPROG"){
    $(document).ready(function () {
        let url = new URL('http://localhost:8011/api/termini/svitermini1');



        url.searchParams.append('korisnik', localStorage.getItem("id"));
        url.searchParams.append('role', localStorage.getItem("role"));


        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",

            success: function (res) {

                for(let i =0 ; i<res.length; i++){



                    let row = "<tr>";
                    row += "<td>" + res[i].datum + "</td>";
                    row += "<td>" + res[i].cena + "</td>";
                    row += "<td>" + res[i].brojprijavljenihclanova + "</td>"
                    row += "<td>" + res[i].oznaka + "</td>";
                    row += "<td>" + res[i].ime + "</td>";
                    row += "<td>" + res[i].prezime + "</td>";
                    row += "<td>" + res[i].opis + "</td>";
                    row += "<td>" + res[i].tip + "</td>";
                    if(localStorage.getItem("role") === "POHADJACOBRAZOVNIHPROG") {
                        row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"zakazimesto\"  value=" + res[i].id + "  />" + "</td>";
                    }

                    row += "</tr>";
                    $('#svitermini').append(row);


                }
            },
            error: function () {
                alert("Nemate pristup ovim podacima!");
            }

        });
    });}

else {
    if (localStorage.getItem("role") === "MEMBER") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "member.html";
    } else if (localStorage.getItem("role") === "PROFESOR") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "pohadjacobrazovnihprograma.html";
    }
    else if (localStorage.getItem("role") === "PARTICIPANT") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "participant.html";
    }
    else {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "login.html";
    }

}


$(document).on("click", '#zakazimesto', function (event) {

    if(localStorage.getItem('role')=== "POHADJACOBRAZOVNIHPROG") {

        var role = localStorage.getItem("role");
        var termin = $("input[name=zakazimesto]:checked").val();
        // var korisnik = localStorage.getItem("id");


        if(typeof termin === 'undefined' || termin === null){
            alert("Niste odabrali termin!");
            window.location.reload(true);

        }

        else{

            let url = new URL('http://localhost:8011/api/termini/zakazimesto');

            url.searchParams.append('role', role);
            url.searchParams.append('termin', termin);
            url.searchParams.append('korisnik', localStorage.getItem("id"));


            $.ajax({
                type: "GET",
                url: url,
                dataType: "json",

                success: function (res) {

                    alert("Uspesno ste zakazali mesto u odabranom terminu!");
                    $('#svitermini').empty();


                    for(let i =0 ; i<res.length; i++){



                        let row = "<tr>";
                        row += "<td>" + res[i].datum + "</td>";
                        row += "<td>" + res[i].cena + "</td>";
                        row += "<td>" + res[i].brojprijavljenihclanova + "</td>"
                        row += "<td>" + res[i].oznaka + "</td>";
                        row += "<td>" + res[i].ime + "</td>";
                        row += "<td>" + res[i].prezime + "</td>";
                        row += "<td>" + res[i].opis + "</td>";
                        row += "<td>" + res[i].tip + "</td>";
                        if(localStorage.getItem("role") === "PROFESOR") {
                            row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"otkazitermin\"  value=" + res[i].id + "  />" + "</td>";
                        }

                        row += "</tr>";
                        $('#svitermini').append(row);


                    }
                    window.location.reload(true);
                },
                error: function () {
                    alert("Niste uspeli da zakazete mesto u odabranom terminu!");
                }

            });}}



    else {
        if (localStorage.getItem("role") === "MEMBER") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        } else if (localStorage.getItem("role") === "PROFESOR") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "pohadjacobrazovnihprograma.html";
        }
        else if (localStorage.getItem("role") === "PARTICIPANT") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        }
        else {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "login.html";
        }

    }
});



