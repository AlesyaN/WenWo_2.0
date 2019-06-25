function like(event) {
    var id = event.currentTarget.dataset.questionid;
    console.log(id);
    var button = event.target;
    var numOfLikes = document.getElementById("likes" + id);
    console.log(numOfLikes);
    $.ajax({
        url : "/api/like",
        method: "post",
        data: {
            "id" : id
        },
        success: function (msg) {
            if (msg === true) {
                numOfLikes.innerHTML = +numOfLikes.innerHTML + 1;
                document.getElementById("likesHeart").className = "fa fa-heart";
            } else if (msg === false) {
                numOfLikes.innerHTML = +numOfLikes.innerHTML - 1;
                document.getElementById("likesHeart").className = "fa fa-heart-o";
            }
        },
        error: function (msg) {
            alert("error");
        }
    });
}