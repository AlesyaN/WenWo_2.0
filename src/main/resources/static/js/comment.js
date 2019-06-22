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

            var author = document.createElement("a");
            author.href = "/profile/" + comment.authorId;
            author.innerHTML = comment.authorLogin;

            var deleteButton = document.createElement("button");
            deleteButton.className = "button delete";
            deleteButton.dataset.commentId = comment.id;
            deleteButton.innerHTML = "Delete";
            deleteButton.onclick = deleteComment;

            var text = document.createElement("b");
            text.innerHTML = usertags(hashtags(comment.text));

            var dateElement = document.createElement("i");
            var date = new Date(comment.date);
            dateElement.innerHTML = ('0' + date.getDate()).slice(-2) + "."
                                    + ('0' + (1 + +date.getMonth())).slice(-2) + "."
                                    + date.getUTCFullYear() + " "
                                    + date.getHours() + ":" +
                                    + date.getMinutes() + ":" +
                                    + date.getSeconds();

            var br = document.createElement("br");

            commentElement.id = "comment" + comment.id;
            commentElement.appendChild(author);
            commentElement.appendChild(deleteButton);
            commentElement.appendChild(br);
            commentElement.appendChild(text);
            commentElement.appendChild(br.cloneNode());
            commentElement.appendChild(dateElement);
            commentElement.appendChild(br.cloneNode());

            commentsList.appendChild(commentElement);
            commentsList.appendChild(br.cloneNode());

            commentInput.value = "";
        },
        error: function () {
            alert("error");
        }
    })
}

function deleteComment(event) {
    var id = event.target.dataset.questionCommentId;
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
