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
    var unansweredQuestions = document.getElementById("unansweredQuestions");
    var textarea = document.getElementById("textarea");
    event.preventDefault();
    if (textarea.value === "") return;
    $.ajax({
        url: "/api/ask",
        type: "post",
        data: {
            "login": document.getElementById("login").innerHTML,
            "question": textarea.value
        },
        success: function (id) {
            unansweredQuestionsBar.style.display = "block";
            numOfUnansweredQuestions.innerHTML = +numOfUnansweredQuestions.innerHTML + 1;
            var newQuestion = document.createElement("div");
            newQuestion.innerHTML = textarea.value;

            var deleteButton = document.createElement("button");
            deleteButton.className = "button delete";
            deleteButton.innerHTML = "Delete";
            deleteButton.dataset.questionid = id;
            deleteButton.onclick = deleteUnansweredQuestion;

            newQuestion.appendChild(deleteButton);
            unansweredQuestions.appendChild(document.createElement("br"));
            unansweredQuestions.appendChild(newQuestion);

            textarea.value = "";
        },
        error: function (msg) {
            alert("error");
        }
    });
}

function deleteUnansweredQuestion(event) {
    var id = event.target.dataset.questionid;
    var numOfUnansweredQuestions = document.getElementById("num-of-unanswered-questions");
    var question = document.getElementById("question" + id);
    $.ajax({
        url: "/api/deleteQuestion",
        type: "post",
        data: {
            "id": id
        },
        success(msg) {
            question.remove();
            numOfUnansweredQuestions.innerHTML = +numOfUnansweredQuestions.innerHTML - 1;
        },
        error(msg) {
            alert(error);
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
        success: function (msg) {
            closeEditAnswerField(event);
            document.getElementById("answer" + questionId).innerHTML = answer;
        }
    });

}

function openUnansweredQuestions(event) {
    var questionsList = document.getElementById("unansweredQuestions");
    questionsList.style.display = "block";
    event.target.onclick = closeUnansweredQuestions;
}

function closeUnansweredQuestions(event) {
    var questionsList = document.getElementById("unansweredQuestions");
    questionsList.style.display = "none";
    event.target.onclick = openUnansweredQuestions;

}