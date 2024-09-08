var comments = [
    "This is the first comment.",
    "This is the second comment.",
    "And this is the third comment."
  ];
  
  // Function to show the pop-up and display comments
  function showComments() {
    var popup = document.getElementById("popup");
    var commentsList = document.getElementById("commentsList");
    
    // Clear any existing comments
    commentsList.innerHTML = "";
    
    // Populate the pop-up with comments
    comments.forEach(function(comment) {
      var li = document.createElement("li");
      li.textContent = comment;
      commentsList.appendChild(li);
    });
    
    // Display the pop-up
    popup.style.display = "block";
  }
  
  // Function to close the pop-up
  function closePopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "none";
  }