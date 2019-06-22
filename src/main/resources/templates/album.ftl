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
        <input type="text" hidden id="x" name="x">
        <input type="text" hidden id="y" name="y">
        <input type="text" name="description" class="input-field" placeholder="Description"><br>
        <input type="submit">
    </form>
    </#if>
    <#list album.photos as photo>
         <div id="photo${photo.id}">
             <img style="height: 300px" src="${photo.photoPath}"><br>
             <div id="description${photo.id}">${photo.description}</div>
             <br>
             <i>${photo.date}</i>
             <br>
             <#if photo.coordinateX?has_content && photo.coordinateY?has_content>
                 <button onclick="showOnMap(event)" data-id="${photo.id}" data-x="${photo.coordinateX}" data-y="${photo.coordinateY}" class="button">Show on map</button>
                 <div style="display: none" id="map${photo.id}" class="map"></div>
             </#if>
             <br>
             <#if currentUserId?? && album.owner.id == currentUserId>
                 <button onclick="deletePhoto(event)" data-photo-id="${photo.id}" class="button delete">Delete</button>
                 <button onclick="toggleEditDescriptionForm(event)" class="button edit">Edit</button>
                 <div id="edit-description-form" style="display:none">
                     <input type="text" id="newDescription" class="input-field"><br>
                     <button class="button" onclick="editDescription(event)" data-photo-id="${photo.id}">Save</button>
                 </div>
             </#if>

             <div class="form-style-2" id="comments${photo.id}">
                 <h3 class="form-style-2-heading">Comments:</h3>
                <#list photo.comments as comment>
                    <div id="comment${comment.id}">
                        <a href="/profile/${comment.author.login}">${comment.author.login}</a>
                        <#if currentUserId?? && comment.author.id == currentUserId>
                            <button class="button delete" data-photo-comment-id="${comment.id}"
                                    onclick="deleteComment(event)">Delete
                            </button>
                        </#if><br>
                        <b>
                            <div data-contain-hashtags data-contain-user-tags>${comment.text}</div>
                        </b><br>
                        <i>${comment.date}</i><br>
                    </div>
                    <br>
                </#list>
             </div>

            <#if currentUserId??>
            <div class="form-style-2">
                <input class="input-field" id="commentinput${photo.id}"
                       placeholder="Your comment">
                <br><br>
                <button class="button" data-photoId="${photo.id}" onclick="addComment(event)">Send</button>
            </div>
            </#if>

             <br>
             <br>
             <br>
             <br>
         </div>
    </#list>
</div>
<script src="/webjars/jquery/jquery.min.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=ba0d1ebb-c4a5-4a00-af89-2882eb642996" type="text/javascript"></script>
<script type="application/javascript" src="/js/maps.js"></script>
</body>
</html>