document.addEventListener("DOMContentLoaded", connect);
var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/user/queue/chat',
            function (message) {
                addMessage(JSON.parse(message.body));
            },
            function (message) {
                console.log(message);
            });
    });
}

function addMessage(message) {
    var tr = document.createElement("tr");

    var name = document.createElement("td");
    var link = document.createElement("a");
    link.href = "/profile/" + message.senderLogin;
    link.innerHTML = message.senderLogin;
    name.appendChild(link);

    var text = document.createElement("td");
    text.innerHTML = message.text;

    var dateElement = document.createElement("td");
    var date = new Date(message.date);
    dateElement.innerHTML = ('0' + date.getDate()).slice(-2) + "."
        + ('0' + (1 + +date.getMonth())).slice(-2) + "."
        + date.getUTCFullYear() + " "
        + date.getHours() + ":" +
        + date.getMinutes() + ":" +
        + date.getSeconds();

    tr.appendChild(name);
    tr.appendChild(text);
    tr.appendChild(dateElement);

    document.getElementById("messages").appendChild(tr);

}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({
        'receiverLogin': $("#partner").val(),
        'text': $("#message").val()
    }));
    document.getElementById("message").value = "";
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendMessage(); });
});