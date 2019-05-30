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

function openNewAlbumForm(event) {
    document.getElementById("newAlbumForm").style.display = "block";
}

function closeNewAlbumForm() {
    document.getElementById("newAlbumForm").style.display = "none";
}