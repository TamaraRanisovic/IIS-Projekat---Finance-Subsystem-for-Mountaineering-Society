$(document).ready(function (){
    if(localStorage.getItem('role') === "MANFINANCE"){
        var role = localStorage.getItem("role"); // Read role from localStorage
            if (!role) {
                console.error("Role not found in localStorage");
                return;
            }
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "http://localhost:8011/api/availablefunds/latest?role=" + role, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        var data = JSON.parse(xhr.responseText);
                        // Format the date
                        var updateDate = new Date(data.updateDate);
                        var dateOptions = {
                            day: '2-digit',
                            month: '2-digit',
                            year: 'numeric',
                            hour: '2-digit',
                            minute: '2-digit',
                            second: '2-digit'
                        };
                        var formattedDate = updateDate.toLocaleString('en-GB', dateOptions);
                        document.getElementById("updateDate").textContent = formattedDate;
                        document.getElementById("fundsAmount").textContent = data.amount;
                    } else {
                        console.error("Error loading data: " + xhr.statusText);
                    }
                }
            };
            xhr.send();

    } else {
        alert("Nemate pristup ovoj stranici!");
        window.location.href ="login.html";
    }
});
