$(document).ready(function (){
    if(localStorage.getItem('role') === "MANFINANCE"){
        var role = localStorage.getItem("role");

        let url = new URL('http://localhost:8011/api/expense/all');

        url.searchParams.append('role', role);

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            success: function (res) {
                var dateOptions = {
                    day: '2-digit',
                    month: '2-digit',
                    year: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit'
                };

                // Function to filter table rows based on search input and category
                function filterRows() {
                    var searchValue = $("#searchInput").val().toLowerCase();
                    var categoryValue = $("#categoryFilter").val().toLowerCase();

                    $("#allexpenses tr").each(function() {
                        var dateText = $(this).find("td:nth-child(1)").text().toLowerCase();
                        var expenseSourceText = $(this).find("td:nth-child(2)").text().toLowerCase();
                        var descriptionText = $(this).find("td:nth-child(3)").text().toLowerCase();
                        var categoryText = $(this).find("td:nth-child(4)").text().toLowerCase();
                        var amountText = $(this).find("td:nth-child(5)").text().toLowerCase();

                        var matchesSearch = dateText.includes(searchValue) || expenseSourceText.includes(searchValue) || descriptionText.includes(searchValue) || categoryText.includes(searchValue) || amountText.includes(searchValue);
                        var matchesCategory = categoryValue === "" || categoryText === categoryValue;

                        $(this).toggle(matchesSearch && matchesCategory);
                    });
                }

                // Event listeners for search input and category filter dropdown
                $("#searchInput").on("keyup", filterRows);
                $("#categoryFilter").on("change", filterRows);

                // Sorting functionality for "Iznos" column
                $("#allexpenses th:nth-child(5)").click(function() {
                    var table = $(this).closest("table");
                    var rows = table.find("tr:gt(0)").toArray().sort(compareValues(4));
                    this.asc = !this.asc;
                    if (!this.asc) { rows = rows.reverse(); }
                    for (var i = 0; i < rows.length; i++) { table.append(rows[i]); }
                });

                // Sorting functionality for "Datum i vreme" column
                $("#allexpenses th:nth-child(1)").click(function() {
                    var table = $(this).closest("table");
                    var rows = table.find("tr:gt(0)").toArray().sort(compareValues(0));
                    this.asc = !this.asc;
                    if (!this.asc) { rows = rows.reverse(); }
                    for (var i = 0; i < rows.length; i++) { table.append(rows[i]); }
                });

                function compareValues(index) {
                    return function(a, b) {
                        var valA = getCellValue(a, index);
                        var valB = getCellValue(b, index);
                        return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB);
                    };
                }

                function getCellValue(row, index) {
                    return $(row).children("td").eq(index).text();
                }

                for ( let i = 0; i < res.length; i++) {
                    let row = "<tr>";

                    var date = new Date(res[i].date);
                    date = date.toLocaleString('en-GB', dateOptions);
                    row += "<td>" + date + "</td>";

                    row += "<td>" + res[i].expenseSource + "</td>";
                    row += "<td>" + res[i].description + "</td>";

                    if (res[i].expenseCategory === "MATERIALCOST") {
                        res[i].expenseCategory = "Materijalni troškovi";
                    } else if (res[i].expenseCategory === "NONMATERIALCOST") {
                        res[i].expenseCategory = "Nematerijalni troškovi";
                    } else if (res[i].expenseCategory === "COSTOFGOODS") {
                        res[i].expenseCategory = "Troškovi robe";
                    } else if (res[i].expenseCategory === "SALARY") {
                        res[i].expenseCategory = "Plate";
                    } else {
                        res[i].expenseCategory = "Ostalo";
                    }

                    row += "<td>" + res[i].expenseCategory + "</td>";
                    row += "<td>" + res[i].amount + "</td>";
                    row += "</tr>";
                    $('#allexpenses').append(row);
                }
            },
            error: function () {
                alert("Greska!");
            }
        });
    } else {
        alert("Nemate pristup ovoj stranici!");
        window.location.href ="login.html";
    }
});