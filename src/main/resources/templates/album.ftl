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
        Album ${album.name}
    </div>
    <form method="post" action="/albums/${album.id}/delete">
        <input type="submit" class="button" value="Delete album">
    </form>
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
    <#list album.photos as photo>
         <div>
             <img style="display: block; width: 30%; height: 30%" src="${photo.photoPath}"><br>
             <div>${photo.description}</div>
             <br>
             <i>${photo.date}</i>
         </div>
         <br>
         <br>
    </#list>
</div>
</body>
</html>