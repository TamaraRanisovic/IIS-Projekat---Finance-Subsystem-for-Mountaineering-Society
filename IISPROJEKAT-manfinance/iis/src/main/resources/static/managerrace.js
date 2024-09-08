$(document).on("click","#allcompetitions",function (){

    if(localStorage.getItem("role") === "MANRACE") {

        window.location.href = "allcompetitions.html"

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

$(document).on("click","#createrace",function (){

    if(localStorage.getItem("role") === "MANRACE") {

        window.location.href = "createrace.html"

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

    if(localStorage.getItem("role") === "MANRACE") {
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