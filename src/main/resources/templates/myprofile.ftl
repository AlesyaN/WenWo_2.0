<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<#if user??>
<div class="form-style-2">
    <form class="form-style-2" method="get" action="/search">
        <input class="input-field" type="text" id="search" name="search-text">
        <input type="submit" class="button" value="Search">
    </form>
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/profile">My profile</a>
    <a class="button" href="/chat">Messages</a>
    <a class="button" href="/questions">You have ${user.unansweredQuestions?size} unanswered questions</a>
    <br>
    <br>
    <div class="form-style-2-heading">
        ${user.fullName}
    </div>
    <br>
    <a href="/editProfile">Edit</a>
    <br>
    <p>
        <b>Followers: </b>
    <div id="followers">${user.followers?size}</div>
    <br>
    <b>Followings: </b> ${user.followings?size}
    </p>
    <#if user.photoPath??>
        <img style="width: 30%; height: 30%" src="${user.photoPath}">
    </#if>
    <br>
    <#if user.gender??>
        <label for="gender">Gender:
            <div id="gender">${user.gender}</div>
        </label>
        <br>
    </#if>

    <label for="login">Login:
        <div id="login">${user.login}</div>
    </label>
    <br>

    <label for="email">Email:
        <div id="email">${user.email}</div>
    </label>
    <br>

    <#if user.city??>
        <label for="city">City:
            <div id="city">${user.city}</div>
        </label>
        <br>
    </#if>

    <#if user.dateOfBirth??>
        <label for="dateOfBirth">Date of birth:
            <div id="dateOfBirth">${user.dateOfBirth}</div>
        </label>
        <br>
    </#if>

    <div class="form-style-2-heading">Albums</div>
    <button id="photomapbtn" data-login="${user.login}" class="button">
        Show photo map
    </button>
    <div id="photo-map" class="map" style="display: none"></div>

    <button class="button" onclick="toggleNewAlbumForm(event)">+ new album</button>
    <div style="display: none" id="newAlbumForm">
        <label for="name">Album name:
            <input class="input-field" type="text" id="albumName" name="name"></label>
        <button class="button" onclick="addAlbum(event)">Save</button>
    </div>
    <table id="albums">
        <tr id="images">
                <#list user.albums as album>
                    <td>
                        <img style="height: 200px" src="${album.cover.photoPath}">
                    </td>
                </#list>
        </tr>
        <tr id="links">
                <#list user.albums as album>
                    <td>
                        <a href="/albums/${album.id}">${album.name}</a>
                    </td>
                </#list>
        </tr>
    </table>
    <br>
    <a href="/logout">Log out</a>
    <br>
    <br>
    <div class="form-style-2-heading">Questions</div>
    <list>
    <#list user.answeredQuestions as question>
        <div id="${question.id}">
            <a href="/profile/${question.receiver.login}">${question.receiver.login}</a>
            <br>
            <b>
                <div data-contain-user-tags data-contain-hashtags>${question.text}</div>
            </b>
            <br>
            <i>
                <#if question.anonymous>
                    anonymous
                <#else>
                    <a href="/profile/${question.sender.login}">${question.sender.login}</a>
                </#if>
                ${question.date}
            </i>
            <br>
                    <#if question.answer??>
                        <p data-contain-user-tags data-contain-hashtags id="answer${question.id}">${question.answer}</p>
                    </#if>
            <#assign liked = false>
            <#list question.likes as like>
                <#if like.user.login == user.login>
                    <#assign liked = true>
                </#if>
            </#list>
            <button
                    <#if liked == true>
                        class="pressed-button"
                    <#elseif liked == false>
                        class="button"
                    </#if>
                        data-questionid="${question.id}" onclick="like(event)">&hearts;
            </button>
            <i id="likes${question.id}">${question.likes?size}</i>
            <button class="button delete" onclick="deleteQuestion(event)" data-questionId="${question.id}">Delete
            </button>
            <button class="button edit" onclick="openEditAnswerField(event)" data-questionId="${question.id}">Edit
            </button>
        </div>
        <br>
        <div class="form-style-2" id="comments${question.id}">
            <h3 class="form-style-2-heading">Comments:</h3>
                <#list question.comments as comment>
                    <div id="comment${comment.id}">
                        <a href="/profile/${comment.author.login}">${comment.author.login}</a>
                        <#if comment.author.login == user.login>
                            <button class="button delete" data-comment-id="${comment.id}"
                                    onclick="deleteComment(event)">Delete
                            </button>
                        </#if><br>
                        <b>
                            <div data-contain-user-tags data-contain-hashtags>${comment.text}</div>
                        </b><br>
                        <i>${comment.date}</i><br>
                    </div>
                    <br>
                </#list>
        </div>
        <div class="form-style-2">
            <input class="input-field" id="commentinput${question.id}"
                   placeholder="Your comment">
            <br><br>
            <button class="button" data-questionId="${question.id}" onclick="addComment(event)">Send</button>
        </div>
        <br>
    </#list>
    </list>

</div>

<#else>
<div class="form-style-2-heading">
    No such user
</div>
</#if>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/question-comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=ba0d1ebb-c4a5-4a00-af89-2882eb642996" type="text/javascript"></script>
<script type="application/javascript" src="/js/photo-map.js"></script>
</body>
</html>