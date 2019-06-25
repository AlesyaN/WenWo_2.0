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
                followBtn.innerHTML = "<span class=\"fa fa-user-times\"></span> Not follow";
            } else if (msg === false) {
                followers = +followers - 1;
                followBtn.innerHTML = "<span class=\"fa fa-user-plus\"></span> Follow";
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
    var question = document.getElementById("newQuestion");
    var checkbox = document.getElementById("anonymous");
    event.preventDefault();
    if (question.value.trim() === "") return;
    $.ajax({
        url: "/api/ask",
        type: "post",
        data: {
            "login": document.getElementById("login").innerHTML,
            "question": question.value,
            "anonymous": checkbox.checked
        },
        success: function (id) {
            numOfUnansweredQuestions.innerHTML = +numOfUnansweredQuestions.innerHTML + 1;
            var newQuestion = document.createElement("div");
            newQuestion.id = "question"+id;
            newQuestion.className = "list-group-item flex-column align-items-start";
            newQuestion.innerHTML = "<p class=\"mb-1\" data-contain-user-tags data-contain-hashtags>"+usertags(hashtags(question.value))+"</p>\n" +
                "                                <button class=\"btn btn-sm btn-dark\" onclick=\"deleteUnansweredQuestion(event)\"\n" +
                "                                        data-questionId=\""+id+"\">Delete\n" +
                "                                </button>";




            unansweredQuestions.appendChild(newQuestion);

            checkbox.checked = false;
            question.value = "";
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
    textarea.value = removetags(document.getElementById("answer" + id).innerHTML);

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
            document.getElementById("answer" + questionId).innerHTML = usertags(hashtags(answer));
            document.getElementById("answerField"+questionId).value = '';
        }
    });

}

function openUnansweredQuestions(event) {
    var questionsList = document.getElementById("unanswered-questions-bar");
    questionsList.style.display = "block";
    event.target.onclick = closeUnansweredQuestions;
}

function closeUnansweredQuestions(event) {
    var questionsList = document.getElementById("unanswered-questions-bar");
    questionsList.style.display = "none";
    event.target.onclick = openUnansweredQuestions;
}