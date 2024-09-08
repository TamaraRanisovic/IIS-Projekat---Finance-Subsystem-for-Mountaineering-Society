$(document).on("submit", "#createrace", function (event) {
    event.preventDefault();

        var type = $("#type").val();
        var description = $("#description").val();
        var location = $("#location").val();
        var numberAvailable = $("#numberAvailable").val();
        var dateTime = $("#dateTime").val();

    var novoTakmicenje = {
        type,
        description,
        location,
        numberAvailable,
        dateTime

    }
    console.log(novoTakmicenje);

    $.ajax({
        type: "POST",
        url: "http://localhost:8011/api/competitions/competition",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(novoTakmicenje),
        success: function (res) {
            console.log(res);
            alert("Takmicenje" + res.id + " je uspesno kreirano!");
            window.location.href = "allcompetitions.html";
        },
        error: function () {
            alert("Greška!");
        }
    });


});