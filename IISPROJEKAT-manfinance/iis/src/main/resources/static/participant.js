if(localStorage.getItem("role")=== "PARTICIPANT") {
    $(document).ready(function () {
        alert("Pocetna stranica ucesnika");

    });}
else {
    if (localStorage.getItem("role") === "MEMBER") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "member.html"
    } else if (localStorage.getItem("role") === "ADMINISTRATOR") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "administrator.html";
    } else {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "login.html"
    }
}

$(document).on("click","#profil", function(event){
    event.preventDefault();

    window.location.href = "profil.html";





});



$(document).on("click","#logout",function (){

    if(localStorage.getItem("role") === "PARTICIPANT") {
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
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }
});