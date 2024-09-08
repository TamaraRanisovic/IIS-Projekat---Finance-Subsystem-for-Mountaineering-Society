$(document).ready(function () {
    if (localStorage.getItem('role') === "PROFESOR") {
            let url = new URL('http://localhost:8011/api/termini/nadjiprostorije1');

            url.searchParams.append('role', localStorage.getItem('role'));

            $.ajax({
                type: "GET",
                url: url,
                dataType: "json",
                success: function (res) {
                    console.log(res);
                    for (let i = 0; i < res.length; i++) {


                        let row = "<tr>";
                        row += "<td>" + res[i].oznaka + "</td>";
                        row += "<td>" + res[i].kapacitet + "</td>";

                        row += "<td>" + "    <input type=\"radio\" name=\"prostorija\"  value=" + res[i].id + "  />" + "</td>";


                        row += "</tr>";
                        $('#prostorija1').append(row);
                    }
                },
                error: function () {
                    alert("Greška!");
                }
            });}







    else {
        if (localStorage.getItem("role") === "MEMBER") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        } else if (localStorage.getItem("role") === "POHADJACOBRAZOVNIHPROGRAMA") {
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




$(document).on("click", '#novitermin2', function(){
    if(localStorage.getItem('role') === "PROFESOR") {
        var prostorija = $("input[name=prostorija]:checked").val();
        var tiptermina = $("input[name=tag1]:checked").val();

        console.log($("input[name=tag1]:checked").val());


        var korisnik = localStorage.getItem('id');

        let datum = $('#datumivreme').val();
        let cena = $('#cena').val();
        let opis = $('#opis').val();

        console.log(cena);
        console.log(opis);
        console.log(datum);

        if(!datum || !cena){
            alert("Morate popuniti sve podatke!");
            return;
        }



        if( typeof prostorija === 'undefined' || prostorija === null ){
            alert("Niste odabrali prostoriju !");
            window.location.reload(true);

        }





        else{




            var termin = {
                datum,
                cena,
                opis,
                tiptermina
            }

            let url = new URL('http://localhost:8011/api/termini/novitermin1');


            url.searchParams.append('role', localStorage.getItem('role'));
            url.searchParams.append('prostorija', prostorija);
            url.searchParams.append('korisnik', korisnik);

            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(termin),
                success: function (res) {
                    alert("Uspesno ste napravili novi termin!");
                    window.location.reload(true);

                },
                error: function () {
                    alert("Greška!");
                }
            });}}






    else {
        if (localStorage.getItem("role") === "ADMINISTRATOR") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "administrator.html";
        } else if (localStorage.getItem("role") === "CLANFITNESCENTRA") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "clanfitnescentra.html";
        } else {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "login.html";
        }

    }




});