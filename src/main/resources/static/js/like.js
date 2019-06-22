function like(event) {
    var id = event.target.dataset.questionid;
    var type;
    if (id !== null && id !== undefined) {
        type = "question";
    } else {
        id = event.target.dataset.photoid;
        type = "photo";
    }
    console.log(id, type);
    var button = event.target;
    var numOfLikes = document.getElementById("likes" + id);
    $.ajax({
        url : "/api/like",
        method: "post",
        data: {
            "id" : id,
            "type": type
        },
        success: function (msg) {
            if (msg === true) {
                numOfLikes.innerHTML = +numOfLikes.innerHTML + 1;
                button.className = "pressed-button"
            } else if (msg === false) {
                numOfLikes.innerHTML = +numOfLikes.innerHTML - 1;
                button.className = "button";
            }
        },
        error: function (msg) {
            alert("error");
        }
    });
}