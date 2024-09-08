$(document).ready(function (){
    if(localStorage.getItem('role') === "MANRACE") {

        let urlParams = new URLSearchParams(window.location.search);
        let competition = urlParams.get('id');

        if( competition === 'undefined' || competition === null ){
            alert("Niste odabrali takmicenje");
            window.location.href = "allcompetitions.html";
        }



        else{

            var role = localStorage.getItem('role');


            let url = new URL('http://localhost:8011/api/competitions/one');

            url.searchParams.append('user', user);
            url.searchParams.append('role', role);



            $.ajax({
                type: "GET",
                url: url,
                dataType: "json",

                success: function (res) {




                    $("input#type").val(res.type);
                    $("input#description").val(res.description);
                    $("input#location").val(res.location);
                    $("input#numberAvailable").val(res.numberAvailable);




                },
                error: function () {
                    alert("Greska!");
                    window.location.href = "allcompetitions.html";
                }

            });}}

    else{
        if(localStorage.getItem("role") === "MEMBER"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        }
        else if(localStorage.getItem("role") === "PARTICIPANT") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        }

    }





});



$(document).on("click", '#competition1', function () {


    if(localStorage.getItem('role') === "MANRACE") {

        let urlParams = new URLSearchParams(window.location.search);
        let user = urlParams.get('id');

        var type = $("#type").val();
        var description = $("#description").val();
        var location = $("#location").val();
        var numberAvailable = $("#numberAvailable").val();


        var competition1 = {
            type,
            description,
            location,
            numberAvailable
        }

        if(!type || !description || !location || !numberAvailable){
            alert("Morate popuniti sve podatke!");
            return;
        }




        if(competition === 'undefined' || competition === null){
            alert("Niste odabrali takmicenje");
            window.location.href = "allcompetitions.html";
        }




        else{

            var role = localStorage.getItem('role');

            let url = new URL('http://localhost:8011/api/competitions/update');

            url.searchParams.append('user', user);
            url.searchParams.append('role', role);


            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(user1),
                success: function (res) {
                    alert("Uspesno ste izmenili takmicenje!");
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
        }

    }




});