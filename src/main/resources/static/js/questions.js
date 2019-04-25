function answer(event) {
    var id = event.target.id;
    var text = document.getElementById("textarea" +  id).value;
    var question = document.getElementById("question" + id);
    var numOfUnansweredQuestions = document.getElementById("num-of-unanswered-questions");
    $.ajax({
        url: "api/answer",
        type: "post",
        data: {
            "id": id,
            "answer": text
        },
        success: function (msg) {
            question.remove();
            numOfUnansweredQuestions.innerHTML = +numOfUnansweredQuestions.innerHTML - 1;
        },
        error: function (msg) {
            alert("error");
        }
    });
}