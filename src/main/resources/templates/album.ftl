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