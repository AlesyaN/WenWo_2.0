<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Feed
    </div>
    <a class="button" href="/profile">My profile</a>
    <#list feed as question>
        <ul>
            <a href="/profile/${question.receiver.id}">${question.receiver.login}</a>
            <br>
            <b>${question.text}</b>
            <br>
            <i>${question.sender.login}  ${question.date}</i>
            <br>
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
                    data-questionid="${question.id}" onclick="like(event)">&hearts;</button>
            <i id="likes${question.id}">${question.likes?size}</i>
        </ul>
    <br>
    </#list>

</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
</body>
</html>