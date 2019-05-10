function follow() {
    $.ajax({
        url: "/api/follow",
        type: "post",
        data: {
            "login": document.getElementById("login").innerHTML
        },
        success: function (msg) {
            var followBtn = document.getElementById("follow-btn");
            var followersElement = document.getElementById("followers");
            var followers = followersElement.innerHTML;
            if (msg === true) {
                followers = +followers + 1;
                followBtn.innerHTML = "Not follow";
            } else if (msg === false) {
                followers = +followers - 1;
                followBtn.innerHTML = "Follow";
            }
            followersElement.innerHTML = followers;
        },
        error: function (msg) {
            alert("error");
        }
    });
}

function ask(event) {
    var numOfUnansweredQuestions = document.getElementById("num-of-unanswered-questions");
    var unansweredQuestionsBar = document.getElementById("unanswered-questions-bar");
    var textarea =  document.getElementById("textarea");
    event.preventDefault();
    $.ajax({
        url: "/api/ask",
        type: "post",
        data: {
            "login": document.getElementById("login").innerHTML,
            "question": document.getElementById("textarea").value
        },
        success: function (msg) {
            textarea.value = "";
            if (+msg > 0) {
                unansweredQuestionsBar.style.display = "block";
                numOfUnansweredQuestions.innerHTML = msg;
            }
        },
        error: function (msg) {
            alert("error");
        }
    });
}

function deleteQuestion(event) {
    var id = event.target.dataset.questionid;
    var question = document.getElementById(id);
    $.ajax({
        url: "/api/deleteQuestion",
        type: "post",
        data: {
            "id": id
        },
        success(msg) {
            question.remove();
        },
        error(msg) {
            alert(error);
        }
    });
}

function openEditAnswerField(event) {
    var id = event.target.dataset.questionid;
    event.target.remove();
    var question = document.getElementById(id);

    var div = document.createElement("div");

    var textarea = document.createElement("textarea");
    textarea.className = "textarea-field";
    textarea.id = "answerField" + id;
    textarea.value = document.getElementById("answer" + id).innerHTML;

    var editButton = document.createElement("button");
    editButton.className = "button edit";
    editButton.dataset.questionid = id;
    editButton.onclick = editAnswer;
    editButton.innerHTML = "Edit";

    var closeButton = document.createElement("button");
    closeButton.className = "button delete";
    closeButton.innerHTML = "Close";
    closeButton.onclick = closeEditAnswerField;

    div.appendChild(document.createElement("br"));
    div.appendChild(textarea);
    div.appendChild(document.createElement("br"));
    div.appendChild(editButton);
    div.appendChild(closeButton);

    question.appendChild(div);
}

function closeEditAnswerField(event) {
    var question = event.target.parentElement.parentElement;
    event.target.parentElement.remove();

    var button = document.createElement("button");
    button.className = "button edit";
    button.innerHTML = "Edit";
    button.dataset.questionid = question.id;
    button.onclick = openEditAnswerField;

    question.appendChild(button);
}

function editAnswer(event) {
    var questionId = event.target.dataset.questionid;
    var answer = document.getElementById("answerField" + questionId).value;
    $.ajax({
        url: "/api/editAnswer",
        type: "post",
        data: {
            "questionId": questionId,
            "answer": answer
        },
        success: function(msg) {
            closeEditAnswerField(event);
            document.getElementById("answer" + questionId).innerHTML = answer;
        }
    });
}