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