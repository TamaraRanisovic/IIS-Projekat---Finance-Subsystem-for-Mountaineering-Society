if(localStorage.getItem("role")=== "MANTOURS") {
    $(document).ready(function () {
        
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

$(document).on("click","#lista",function (event){
    event.preventDefault();

    window.location.href = "lisprijArhTure.html";
});
