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
            <i>Likes: ${question.likes?size}</i>
        </ul>
    <br>
    </#list>

</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
</body>
</html>