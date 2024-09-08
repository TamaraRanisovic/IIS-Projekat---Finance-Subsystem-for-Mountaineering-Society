$(document).on("click","#sviuseri",function (){

    if(localStorage.getItem("role") === "ADMINISTRATOR") {

        window.location.href = "sviuseri.html"

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
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }
});





$(document).on("click","#logout",function (){

    if(localStorage.getItem("role") === "ADMINISTRATOR") {
        localStorage.clear();
        window.location.href = "login.html"

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
        else{
            alert("Nemate pristup ovoj stranici!");
            window.location.href ="login.html";
        }

    }
});