$(document).ready(function (){
    if(localStorage.getItem('role') === "MANFINANCE"){


    } else {
        alert("Nemate pristup ovoj stranici!");
        window.location.href ="login.html";
    }
});
