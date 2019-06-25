(function ($) {
    "use strict";
    $('#newAlbumModal').on('show.bs.modal', function (event) {
        $('#albumName').trigger('focus')
    })
})(jQuery);
function addAlbum(event) {
    var name = document.getElementById("albumName").value;
    document.getElementById("albumName").value = "";
    if (name.trim() !== "") {
        var albums = document.getElementById("albums");
        $.ajax({
            url: "/api/addAlbum",
            method: "post",
            data: {
                "name": name
            },
            success: function (album) {
                var imgCard = document.createElement('div');
                imgCard.className = 'col-md-4';
                imgCard.innerHTML = "<div class=\"card mb-4 shadow-sm\">\n" +
                    "                            <img class=\"card-img-top\"  style=\"object-fit: cover; height: 200px\" src=\""+album.cover.photoPath+"\" alt=\"Card image cap\">\n" +
                    "                            <div class=\"card-body\">\n" +
                    "                                <p class=\"card-text\">"+album.name+"</p>\n" +
                    "                                <div class=\"d-flex justify-content-between align-items-center\">\n" +
                    "                                    <div class=\"btn-group\">\n" +
                    "                                        <form action=/albums/"+album.id+">\n" +
                    "                                            <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">View</button>\n" +
                    "                                        </form>\n" +
                    "                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">Edit</button>\n" +
                    "                                    </div>\n" +
                    "                                    <small class=\"text-muted\">"+album.photosNumber+" photos</small>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>";
                albums.appendChild(imgCard);
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

function deletePhoto(event) {
    var photoId = event.target.dataset.photoId;
    $.ajax({
        url: "/api/deletePhoto",
        data: {
            "photo-id": photoId
        },
        method: "post",
        success: function () {
            document.getElementById("photo" + photoId).remove();
        }
    });
}

function toggleEditDescriptionForm() {
    var form = document.getElementById("edit-description-form");
    if (form.style.display === "block") {
        form.style.display = "none";
    } else {
        form.style.display = "block";
    }
}

function editDescription(event) {
    var photoId = event.target.dataset.photoId;
    var newDescription = document.getElementById("newDescription").value;
    if (newDescription.trim() !== "") {
        $.ajax({
            url: "/api/editPhotoDescription",
            method: "post",
            data: {
                "photo-id": photoId,
                "new-description": newDescription
            },
            success: function () {
                toggleEditDescriptionForm();
                document.getElementById("description" + photoId).innerHTML = newDescription;
            }
        });
    }
}


