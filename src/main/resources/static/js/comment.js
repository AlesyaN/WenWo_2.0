function addComment(event) {
    var questionId = event.target.dataset.questionid;
    var commentInput = document.getElementById("comment" + questionId);
    $.ajax({
        url: "/api/addComment",
        method: "post",
        contentType: 'application/json',
        data: JSON.stringify({
            "questionId": +questionId,
            "text": commentInput.value
        }),
        success: function (comment) {
            var commentsList = document.getElementById("comments");
            var commentElement = document.createElement("div");

            var author = document.createElement("a");
            author.href = "/profile/" + comment.authorId;
            author.innerHTML = comment.authorLogin;

            var text = document.createElement("b");
            text.innerHTML = comment.text;

            var dateElement = document.createElement("i");
            var date = new Date(comment.date);
            dateElement.innerHTML = ('0' + date.getDate()).slice(-2) + "."
                                    + ('0' + (1 + +date.getMonth())).slice(-2) + "."
                                    + date.getUTCFullYear() + " "
                                    + date.getHours() + ":" +
                                    + date.getMinutes() + ":" +
                                    + date.getSeconds();

            var br = document.createElement("br");

            commentElement.appendChild(author);
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