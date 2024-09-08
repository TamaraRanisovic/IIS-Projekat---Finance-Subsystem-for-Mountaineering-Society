$(document).ready(function (){
    if(localStorage.getItem('role') === "ADMINISTRATOR"){
        var role = localStorage.getItem("role");

        let url = new URL('http://localhost:8011/api/users/all');

        url.searchParams.append('role', role);

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",

            success: function (res) {

                for ( let i = 0; i < res.length; i++) {


                    let row = "<tr>";
                    row += "<td>" + res[i].firstname + "</td>";
                    row += "<td>" + res[i].lastname + "</td>";
                    row += "<td>" + res[i].phonenumber + "</td>"
                    row += "<td>" + res[i].email + "</td>";
                    row += "<td>" + res[i].dateofbirth + "</td>"
                    row += "<td>" + res[i].role+ "</td>";

                    if(localStorage.getItem("role") === "ADMINISTRATOR") {
                        row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"user\"  value=" + res[i].id + "  />" + "</td>";
                    }
                    row += "</tr>";
                    $('#sviuseri').append(row);
                }
            },
            error: function () {
                alert("Greska!");
            }

        });}
    else{
        if(localStorage.getItem("role") === "MEMBER"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html";
        }
        else if(localStorage.getItem("role") === "PARTICIPANT"){
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        }
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }


});



$(document).on("click", '#izmenikorisnika', function(){
    if(localStorage.getItem('role') === "ADMINISTRATOR") {
        var user1 = $("input[name=user]:checked").val();
        if (typeof user1 === 'undefined') {
            alert("Niste izabrali korisnika");

        }else{


            window.location.href = "izmeniusera.html?id=" + user1;
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
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }
});