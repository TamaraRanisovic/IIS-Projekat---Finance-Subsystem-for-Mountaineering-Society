$(document).ready(function (){
    if(localStorage.getItem('role') === "MANRACE"){
        var role = localStorage.getItem("role");

        let url = new URL('http://localhost:8011/api/competitions/all');

        url.searchParams.append('role', role);

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",

            success: function (res) {

                for ( let i = 0; i < res.length; i++) {


                    let row = "<tr>";
                    row += "<td>" + res[i].type + "</td>";
                    row += "<td>" + res[i].description + "</td>";
                    row += "<td>" + res[i].dateTime + "</td>";
                    row += "<td>" + res[i].location + "</td>";
                    row += "<td>" + res[i].numberAvailable + "</td>";

                    if(localStorage.getItem("role") === "MANRACE") {
                        row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"user\"  value=" + res[i].id + "  />" + "</td>";
                    }
                    row += "</tr>";
                    $('#allcompetitions').append(row);
                }
            },
            error: function () {
                alert("Greska!");
            }

        });}


});


$(document).on("click", '#izmenitakmicenje', function(){
    if(localStorage.getItem('role') === "MANRACE") {
        var competition1 = $("input[name=user]:checked").val();
        if (typeof competition1 === 'undefined') {
            alert("Niste izabrali takmicenje");

        }else{


            window.location.href = "izmenitakmicenje.html?id=" + competition1;
        }}

    else{
        if(localStorage.getItem("role") === "PARTICIPANT"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        }
        else if(localStorage.getItem("role") === "MEMBER"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        }

    }
});
