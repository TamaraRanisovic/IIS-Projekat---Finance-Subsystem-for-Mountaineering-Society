if(localStorage.getItem("role")=== "MANTOURS") {
    $(document).ready(function () {
        alert("Pocetna stranica menadžera tura");
    });}
    else {
        if (localStorage.getItem("role") === "MEMBER") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "member.html"
        } else if (localStorage.getItem("role") === "ADMINISTRATOR") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "administrator.html";
        } else if (localStorage.getItem("role") === "PARTICIPANT") {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "participant.html";
        } else {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "login.html"
        }
    }

$(document).on("click","#kreiraj",function (event){
    event.preventDefault();

    window.location.href = "kreiranjeture.html";
});


$(document).on("click","#pregled",function (event){

    event.preventDefault();

    window.location.href = "raspolozivetureMT.html";
});
    

$(document).on("click","#arhivirane",function (event){

    event.preventDefault();

    window.location.href = "arhtureMT.html";
});



$(document).on("click","#logout",function (){

    if(localStorage.getItem("role") === "MANTOURS") {
        localStorage.clear();
        window.location.href = "login.html"

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