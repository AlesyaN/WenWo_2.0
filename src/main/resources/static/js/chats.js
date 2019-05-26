function openChat(event) {
    var target = event.target;
    while (target.tagName !== "TR") {
        target = target.parentNode;
    }
    window.location.assign("/chat?login=" + target.dataset.login);
}