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