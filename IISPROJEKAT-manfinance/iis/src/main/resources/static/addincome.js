$(document).on("click", '#save', function () {

    if(localStorage.getItem('role') === "MANFINANCE") {

        let urlParams = new URLSearchParams(window.location.search);

        var incomeSource = $("#incomeSource").val();
        var description = $("#description").val();
        var amount = $("#amount").val();
        var incomeCategory = $("#incomeCategory").val();
        var date = $("#incomeDateTime").val();


        var income = {
            incomeSource,
            description,
            amount,
            incomeCategory,
            date
        }

        if(!incomeSource || !description || !amount || !incomeCategory || !date){
            alert("Morate popuniti sve podatke!");
            return;
        }

        var role = localStorage.getItem('role');

        let url = new URL('http://localhost:8011/api/income/add');

        url.searchParams.append('role', role);

        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(income),
            success: function (res) {
                alert("Uspešno ste sačuvali prihod!");
                window.location.reload(true);
            },
            error: function () {
                alert("Greška!");
            }
        });

    } else {
        alert("Nemate pristup ovoj stranici!");
        window.location.href ="login.html";
    }
});