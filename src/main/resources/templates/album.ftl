<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<div class="form-style-2">
    <form class="form-style-2" method="get" action="/search">
        <input class="input-field" type="text" id="search" name="search-text">
        <input type="submit" class="button" value="Search">
    </form>
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/profile">My profile</a>
    <a class="button" href="/chat">Messages</a>
    <br>
    <br>
    <div class="form-style-2-heading">
        Album <span id="albumName">${album.name}</span>
    </div>
    <#if currentUserId?? && album.owner.id == currentUserId>
    <form method="post" action="/albums/${album.id}/delete">
        <input type="submit" class="button" value="Delete album">
    </form>
        <button onclick="toggleEditAlbumNameForm()" class="button edit">Edit name</button>
        <div id="edit-name-form" style="display: none">
            <input type="text" id="new-album-name" class="input-field"><br>
            <button class="button" onclick="editAlbumName()">Save</button>
        </div>
    <form class="form-style-2" action="/albums/${album.id}" method="post" enctype="multipart/form-data">
        <div class="form-style-2-heading">Upload photos</div>
        <input type="file" name="photo">
        <div>
        <#if error??>
            You should add file first
        </#if>
        </div>
        <br>
        <input type="text" name="description" class="input-field" placeholder="Description"><br>
        <input type="submit">
    </form>
    </#if>
    <#list album.photos as photo>
         <div id="photo${photo.id}">
             <img style="height: 300px" src="${photo.photoPath}"><br>
             <div>${photo.description}</div>
             <br>
             <i>${photo.date}</i>
             <br>
             <button onclick="deletePhoto(event)" data-photo-id="${photo.id}" class="button delete">Delete</button>
             <br>
             <br>
             <br>
         </div>
    </#list>
</div>
<script src="/webjars/jquery/jquery.min.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
</body>
</html>