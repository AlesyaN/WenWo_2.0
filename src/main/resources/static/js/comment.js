function addComment(event) {
    var id = event.target.dataset.questionid;
    var type;
    if (id !== null && id !== undefined) {
        type = "question";
    } else {
        id = event.target.dataset.photoid;
        type = "photo";
    }
    var commentInput = document.getElementById("commentinput" + id);
    if (commentInput.value.trim() === "") return;
    $.ajax({
        url: "/api/addComment",
        method: "post",
        contentType: 'application/json',
        data: JSON.stringify({
            "postId": +id,
            "text": commentInput.value,
            "type": type
        }),
        success: function (comment) {
            var commentsList = document.getElementById("comments" + comment.postId);
            var commentElement = document.createElement("div");

            var dateInDays = new Date(comment.date);
            var  date = ('0' + dateInDays.getDate()).slice(-2) + "."
                + ('0' + (1 + +dateInDays.getMonth())).slice(-2) + "."
                + dateInDays.getUTCFullYear() + " "
                + dateInDays.getHours() + ":" +
                + dateInDays.getMinutes() + ":" +
                + dateInDays.getSeconds();

            commentElement.id = "comment" + comment.id;
            commentElement.className = "list-group-item flex-column align-items-start";
            commentElement.innerHTML = "<div class=\"d-flex w-100 justify-content-between\">\n" +
                "                                <h5 class=\"mb-1\"><a href=\"/profile/"+comment.authorLogin+"\">"+comment.authorLogin+"</a></h5>\n" +
                "                                <small>"+date+"</small>\n" +
                "                            </div>\n" +
                "                            <p class=\"mb-1\"  data-contain-user-tags data-contain-hashtags>"+usertags(hashtags(comment.text))+"</p>\n" +
                "              <div class=\"text-right\"><button class=\"btn btn-dark\" data-comment-id=\""+comment.id+"\"\n" +
                "                                    onclick=\"deleteComment(event)\">Delete\n" +
                "                            </button></div>\n";

            commentsList.appendChild(commentElement);
            commentInput.value = "";
        },
        error: function () {
            alert("error");
        }
    })
}

function deleteComment(event) {
    var id = event.target.dataset.commentId;
    var type;
    if (id !== null && id !== undefined) {
        type = "question";
    } else {
        id = event.target.dataset.photoCommentId;
        type = "photo";
    }
    var comment = document.getElementById("comment" + id);
    console.log(id);
    console.log(type);
    console.log(comment);
    $.ajax({
        url: "/api/deleteComment",
        type: "type",
        method: "post",
        data: {
            commentId: id,
            type: type
        },
        success: function (msg) {
            comment.remove();
        },
        error: function(msg) {
            alert(msg);
        }
    })
}
