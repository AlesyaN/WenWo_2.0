function like(event) {
    var id = event.currentTarget.dataset.questionid;
    var type;
    if (id !== null && id !== undefined) {
        type = "question";
    } else {
        id = event.currentTarget.dataset.photoid;
        type = "photo";
    }
    console.log(id, type);

    var button = event.target;
    var numOfLikes = document.getElementById("likes" + id);
    console.log(numOfLikes);
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
                document.getElementById("likesHeart"+id).className = "fa fa-heart";
            } else if (msg === false) {
                numOfLikes.innerHTML = +numOfLikes.innerHTML - 1;
                document.getElementById("likesHeart"+id).className = "fa fa-heart-o";
            }
        },
        error: function (msg) {
            alert("error");
        }
    });
}