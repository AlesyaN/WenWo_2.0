document.addEventListener("DOMContentLoaded", function (ev) {
    var elems = document.body.querySelectorAll("[data-contain-user-tags]");
    for (var i = 0; i < elems.length; i++) {
        elems[i].innerHTML = usertags(elems[i].innerHTML);
    }
});

function usertags(text) {
    var regex = /@[A-Za-z-]+/g;
    var tags = text.match(regex);
    for (var i = 0; tags != null && i < tags.length; i++) {
         $.ajax({
            async: false,
            url: "/api/userExists",
            method: "get",
            data: {
                "login": tags[i].slice(1)
            },
            success: function (login) {
                if (login !== "") {
                    text = text.replace("@" + login,
                        "<a href='/profile/" + login + "'>@" + login + "</a>");
                }
            },
            error: function (msg) {
                alert(msg);
            }
        });
    }
    return text;
}

