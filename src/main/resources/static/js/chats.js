function openChat(event) {
    var target = event.target;
    while (target.tagName !== "TR") {
        target = target.parentNode;
    }
    window.location.assign("/chat?login=" + target.dataset.login);
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/user/queue/chat',
            function (message) {
                addMessage(JSON.parse(message.body));
            }
        );
        stompClient.subscribe('/user/queue/deleteMessage',
            function (message) {
                removeMessage(JSON.parse(message.body));
            }
        );
    });
}

function addMessage(message) {
    var partner = message.senderLogin
}