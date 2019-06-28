<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <link>
</head>
<body>
<br>
<br>
<div class="container after-header">
    <div class="d-flex justify-content-between align-items-baseline">
        <h4>Album <i id="albumName">${album.name}</i></h4>
        <#if currentUserId?? && album.owner.id == currentUserId>
        <div class="form-inline">
            <button type="submit" class="btn btn-outline-dark" data-toggle="modal" data-target="#editNameModal">Edit
                name
            </button>
            &nbsp;
            <div class="bd-example">
                <div class="modal fade" id="editNameModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="exampleModalLabel">Edit album name</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="new-album-name"
                                           placeholder="enter new album name">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal"
                                        onclick="editAlbumName()">Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            &nbsp;
            <form method="post" action="/albums/${album.id}/delete">
                <input type="submit" class="btn btn-dark" value="Delete album">
            </form>
        </div>
        </#if>
    </div>
    <hr>
    <#if currentUserId?? && album.owner.id == currentUserId>
        <form class="d-flex justify-content-between align-items-center" action="/albums/${album.id}" method="post"
              enctype="multipart/form-data">

            <input type="text" name="description" class="form-control col-sm-7" placeholder="Photo description">
            &nbsp;
            <input type="text" hidden id="x" name="x">
            <input type="text" hidden id="y" name="y">
            <input type="file" name="photo">
            <input type="submit" class="btn btn-outline-secondary" value="Upload photo">

        </form>
        <#if error??>
                    You should add file first
        </#if>
    </#if>
    <br>
    <br>
<div <#if album.photos?size<4>class="card-deck"<#else>class="card-columns" </#if>>
        <#list album.photos as photo>
            <div id="photo${photo.id}" class="card">

                <img class="card-img-top" src="${photo.photoPath}" alt="Card image cap">

                <div class="card-body">
                    <p id="description${photo.id}" class="card-text">${photo.description}</p>

                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <#if currentUserId?? && album.owner.id == currentUserId>
                                <button onclick="deletePhoto(event)" data-photo-id="${photo.id}"
                                        class="btn btn-sm btn-outline-secondary">Delete
                                </button>
                                <button class="btn btn-sm btn-outline-secondary" data-toggle="modal"
                                        data-target="#editDescriptionModal">Edit
                                </button>
                                <div class="bd-example">
                                    <div class="modal fade" id="editDescriptionModal" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="exampleModalLabel">Edit photo
                                                        description</h4>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <input type="text" class="form-control" id="newDescription"
                                                               placeholder="enter new description">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                                                            onclick="editDescription(event)"
                                                            data-photo-id="${photo.id}">Save
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </#if>
                                <#if currentUserId??>
                                    <#assign liked = false>
                                    <#list photo.likes as like>
                                        <#if like.user.id == currentUserId>
                                            <#assign liked = true>
                                        </#if>
                                    </#list>

                                    <button onclick="like(event)" class="btn btn-sm btn-outline-danger"
                                            data-photoid="${photo.id}">
                                        <span id="likes${photo.id}">${photo.likes?size}</span>
                                        <span id="likesHeart${photo.id}"
                                              class="fa <#if liked == true> fa-heart <#elseif liked == false> fa-heart-o </#if>"></span>
                                    </button>
                                <#else>
                                    <form action="/login">
                                        <button type="submit" class="btn btn-sm btn-outline-danger">
                                            <span id="likes${photo.id}">${photo.likes?size}</span>
                                            <span id="likesHeart${photo.id}" class="fa fa-heart-o"></span>
                                        </button>
                                    </form>
                                </#if>

                        </div>

                        <small class="text-muted">${photo.date}</small>
                    </div>
                    <#if photo.coordinateX?has_content && photo.coordinateY?has_content>
                    <br>
                    <div class="text-center">

                            <button onclick="showOnMap(event)" data-id="${photo.id}" data-x="${photo.coordinateX}"
                                    data-y="${photo.coordinateY}" class="btn btn-sm btn-outline-dark">Show on map
                            </button>


                    </div>

                        <div style="display: none; margin: auto; width: 270px; height: 200px" id="map${photo.id}"
                             class="map">
                            <br>
                        </div>
                    </#if>

                    <#--<br>
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
                    </#if>-->
                </div>
            </div>
        </#list>
        </div>





</div>


<script src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=ba0d1ebb-c4a5-4a00-af89-2882eb642996"
        type="text/javascript"></script>
<script type="application/javascript" src="/js/maps.js"></script>
</body>
</html>