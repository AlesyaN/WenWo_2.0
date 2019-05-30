function addAlbum(event) {
    var name = document.getElementById("albumName").value;
    if (name.trim() !== "") {
        var albums = document.getElementById("albums");
        $.ajax({
            url: "/api/addAlbum",
            method: "post",
            data: {
                "name": name
            },
            success: function (album) {
                toggleNewAlbumForm();
                var images = document.getElementById("images");
                var tdImg = document.createElement("td");
                var img = document.createElement("img");
                img.style.height = "200px";
                img.src = album.cover.photoPath;
                tdImg.appendChild(img);
                images.appendChild(tdImg);

                var links = document.getElementById("links");
                var tdLink = document.createElement("td");
                var a = document.createElement("a");
                a.href = "/albums/" + album.id;
                a.innerHTML = album.name;
                tdLink.appendChild(a);
                links.appendChild(tdLink);
            }
        });
    }
}

function toggleNewAlbumForm(event) {
    var form = document.getElementById("newAlbumForm");
    if (form.style.display === "block") {
        form.style.display = "none";
    } else {
        form.style.display = "block";
    }
}


function toggleEditAlbumNameForm() {
    var form = document.getElementById("edit-name-form");
    if (form.style.display === "block") {
        form.style.display = "none";
    } else {
        form.style.display = "block";
    }
}

function editAlbumName() {
    var newAlbumName = document.getElementById("new-album-name").value;
    var pathname = window.location.pathname;
    var albumId = pathname.slice(pathname.lastIndexOf("/") + 1);
    if (newAlbumName.trim() !== "") {
        $.ajax({
            url: "/api/editAlbumName",
            method: "post",
            data: {
                "album-id": albumId,
                "new-name": newAlbumName
            },
            success: function () {
                document.getElementById("albumName").innerHTML = newAlbumName;
                toggleEditAlbumNameForm();
            }
        });
    }
}