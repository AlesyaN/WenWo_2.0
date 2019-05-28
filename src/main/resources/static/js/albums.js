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
                closeNewAlbumForm();
                var div = document.createElement("div");
                var img = document.createElement("img");
                img.style.display = "block";
                img.style.width = "30%";
                img.style.height = "30%";
                img.src = album.cover.photoPath;

                var a = document.createElement("a");
                a.href = "/albums/" + album.id;
                a.innerHTML = album.name;

                div.appendChild(img);
                div.appendChild(a);

                albums.appendChild(div);
            }
        });
    }
}

function openNewAlbumForm(event) {
    document.getElementById("newAlbumForm").style.display = "block";
}

function closeNewAlbumForm() {
    document.getElementById("newAlbumForm").style.display = "none";
}