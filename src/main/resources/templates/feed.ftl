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
    <div class="form-style-2-heading">
        Feed
    </div>

    <#list feed as question>
        <ul>
            <a href="/profile/${question.receiver.login}">${question.receiver.login}</a>
            <br>
            <b data-contain-user-tags data-contain-hashtags>${question.text}</b>
            <br>
            <i>
            <#if question.anonymous>
                anonymous
            <#else>
                <a href="/profile/${question.sender.login}">${question.sender.login}</a>
            </#if>
                ${question.date}</i>
            <br>
                    <#if question.answer??>
                        <p data-contain-user-tags data-contain-hashtags>${question.answer}</p>
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
            <i id="likes${question.id}">${question.likes?size}</i>
            <br>
            <div class="form-style-2" id="comments${question.id}">
                <h3 class="form-style-2-heading">Comments:</h3>
                <#list question.comments as comment>
                    <div id="comment${comment.id}">
                        <a href="/profile/${comment.author.login}">${comment.author.login}</a>
                        <#if comment.author.id == currentUserId>
                            <button class="button delete" data-question-comment-id="${comment.id}"
                                    onclick="deleteComment(event)">Delete
                            </button>
                        </#if><br>
                        <b data-contain-user-tags data-contain-hashtags>${comment.text}</b><br>
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
        </ul>
    <br>
    </#list>

</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
</body>
</html>