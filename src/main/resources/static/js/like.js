function like(event) {
    var id = event.target.dataset.questionid;
    var button = event.target;
    var numOfLkes = document.getElementById("likes" + id);
    $.ajax({
        url : "/api/like",
        method: "post",
        data: {
            "id" : id
        },
        success: function (msg) {
            if (msg === true) {
                numOfLkes.innerHTML = +numOfLkes.innerHTML + 1;
                button.className = "pressed-button"
            } else if (msg === false) {
                numOfLkes.innerHTML = +numOfLkes.innerHTML - 1;
                button.className = "button";
            }
        },
        error: function (msg) {
            alert("error");
        }
    });
}