$(document).on("click", '#save', function () {

    if(localStorage.getItem('role') === "MANFINANCE") {

        let urlParams = new URLSearchParams(window.location.search);

        var expenseSource = $("#expenseSource").val();
        var description = $("#description").val();
        var amount = $("#amount").val();
        var expenseCategory = $("#expenseCategory").val();
        var date = $("#expenseDateTime").val();


        var expense = {
            expenseSource,
            description,
            amount,
            expenseCategory,
            date
        }

        if(!expenseSource || !description || !amount || !expenseCategory || !date){
            alert("Morate popuniti sve podatke!");
            return;
        }

        var role = localStorage.getItem('role');

        let url = new URL('http://localhost:8011/api/expense/add');

        url.searchParams.append('role', role);

        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(expense),
            success: function (res) {
                alert("Uspešno ste sačuvali rashod!");
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