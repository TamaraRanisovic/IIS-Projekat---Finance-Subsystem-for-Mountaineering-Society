$(document).on("click", '#novaprostorija1', function () {

    if(localStorage.getItem('role') === "PROFESOR"){
        var kapacitet = $("#kapacitet").val();
        var oznaka = $("#oznaka").val();
        let urlParams = new URLSearchParams(window.location.search);


        if(!kapacitet || !oznaka){
            alert("Morate uneti sve podatke!");
            return;
        }


        else{



            var novaprostorija ={
                kapacitet,
                oznaka
            }


            var role = localStorage.getItem('role');

            let url = new URL('http://localhost:8011/api/termini/novaprostorija');


            url.searchParams.append('role', role);


            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(novaprostorija),
                success: function (res) {
                    alert("Uspesno ste dodali novu prostoriju!");

                },
                error: function () {
                    alert("Greška!");
                }
            });}}

    else{
        if(localStorage.getItem("role") === "MEMBER"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        }
        else if(localStorage.getItem("role") === "PARTICIPANT"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        }
        else if(localStorage.getItem("role") === "POHADJACOBRAZOVNIHPROG"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "pohadjacobrazovnihprog.html";
        }
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }


});
