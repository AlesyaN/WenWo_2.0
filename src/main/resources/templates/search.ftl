<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
</head>
<body>
<div class="form-style-2">
    <form class="form-style-2" method="get" action="/search">
        <input class="input-field" type="text" id="search" name="search-text">
        <input type="submit" class="button" value="Search">
    </form>
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/profile">My profile</a>
    <br><br>
    <div class="form-style-2-heading">Searching results:</div>
<#if 0 < users?size || 0 < questions?size>
    <#if 0 < users?size>
    <br>
        <div class="form-style-2-heading">Users</div>
        <#list users as user>
            <#if user.photo_path??>
                <img style="height: 50px; width: 50px" src="${user.photo_path}">
            </#if>
            <a href="/profile/${user.id}">${user.login}</a>
            <b>${user.getFullName()}</b>
            <br>
            <br>
        </#list>
    </#if>
    <#if 0 < questions?size>
        <br>
        <div class="form-style-2-heading">Questions</div>
        <#list questions as question>
              <a href="/profile/${question.receiver.id}">${question.receiver.login}</a>
            <br>
            <b>${question.text}</b>
            <br>
            <i>
                <#if question.anonymous>
                        anonymous
                <#else>
                        <a href="/profile/${question.sender.id}">${question.sender.login}</a>
                </#if>
                ${question.date}
            </i>
            <br>
            <#if currentUserId??>
                <#if question.answer??>
                            <p>${question.answer}</p>
                </#if>
                <#assign liked = false>
                <#list question.likes as like>
                    <#if like.user.id == currentUserId>
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
            <#else>
                <br>
                <a class="button" href="/login">&hearts;</a>
            </#if>
            <i id="likes${question.id}">${question.likes?size}</i>
            <br>
            <div class="form-style-2" id="comments">
                <h3 class="form-style-2-heading">Comments:</h3>
                    <#list question.comments as comment>
                        <div id="comment${comment.id}">
                            <a href="/profile/${comment.author.id}">${comment.author.login}</a>
                            <#if currentUserId?? && comment.author.id == currentUserId>
                                <button class="button delete" data-comment-id="${comment.id}"
                                        onclick="deleteComment(event)">Delete
                                </button>
                            </#if><br>
                            <b>${comment.text}</b><br>
                            <i>${comment.date}</i><br>
                        </div>
                        <br>
                    </#list>
            </div>
            <#if currentUserId??>
            <div class="form-style-2">
                <input class="input-field" id="comment${question.id}"
                       placeholder="Your comment">
                <br><br>
                <button class="button" data-questionId="${question.id}" onclick="addComment(event)">Send</button>
            </div>
            </#if>
            <br>
        </#list>
    </#if>
<#else>
    <div>No results</div>
</#if>
</div>
</body>
</html>