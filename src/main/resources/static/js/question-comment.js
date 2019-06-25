function addComment(event) {
    var questionId = event.target.dataset.questionid;
    var commentInput = document.getElementById("commentinput" + questionId);
    if (commentInput.value.trim() === "") return;
    $.ajax({
        url: "/api/addQuestionComment",
        method: "post",
        contentType: 'application/json',
        data: JSON.stringify({
            "postId": +questionId,
            "text": commentInput.value
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
    var commentId = event.target.dataset.commentId;
    var comment = document.getElementById("comment" + commentId);
    $.ajax({
        url: "/api/deleteQuestionComment",
        method: "post",
        data: {
            commentId: commentId
        },
        success: function (msg) {
            comment.remove();
        },
        error: function(msg) {
            alert(msg);
        }
    })
}