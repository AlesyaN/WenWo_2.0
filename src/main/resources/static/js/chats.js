document.addEventListener("DOMContentLoaded", connect);
var stompClient = null;

function openChat(event) {
    var target = event.target;
    while (target.tagName !== "TR") {
        target = target.parentNode;
    }
    window.location.assign("/chat?login=" + target.id);
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/user/queue/update',
            function (message) {
                update(JSON.parse(message.body));
            }
        );
    });
}

function update(message) {
    var currentUserLogin = document.getElementById("currentUserLogin").value;
    var partner = (message.senderLogin !== currentUserLogin) ? message.senderLogin : message.receiverLogin;
    var chat = document.getElementById(partner);
    if (chat === null || chat === undefined) {
        var chats = document.getElementById("chats");
        var tr = document.createElement("tr");

        var td1 = document.createElement("td");
        var a = document.createElement("a");
        a.innerHTML = partner;
        a.href = "/profile/" + partner;
        td1.appendChild(a);

        var td2 = document.createElement("td");
        td2.innerHTML = message.senderLogin + "<br>" + message.text;

        var td3 = document.createElement("td");
        var date = new Date(message.date);
        td3.innerHTML = ('0' + date.getDate()).slice(-2) + "."
            + ('0' + (1 + +date.getMonth())).slice(-2) + "."
            + date.getUTCFullYear() + " "
            + date.getHours() + ":" +
            +date.getMinutes() + ":" +
            +date.getSeconds();

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        chats.appendChild(tr);
    } else {
        var td = chat.getElementsByTagName("TD");
        td[1].innerHTML = message.senderLogin + "<br>" + message.text;

        var date = new Date(message.date);
        td[2].innerHTML = ('0' + date.getDate()).slice(-2) + "."
            + ('0' + (1 + +date.getMonth())).slice(-2) + "."
            + date.getUTCFullYear() + " "
            + date.getHours() + ":" +
            +date.getMinutes() + ":" +
            +date.getSeconds();
    }
}

