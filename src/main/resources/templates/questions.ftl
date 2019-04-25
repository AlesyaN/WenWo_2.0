<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        You have ${questions?size} unanswered questions
    </div>
    <a class="button" href="/profile">My profile</a>
    <br>
    <br>
    <#list questions as question>
        <div>
            <b><a href="/profile/${question.sender.id}">${question.sender.login}</a></b>
            <br>
            <b>${question.text}</b>
            <br>
            <i>${question.date}</i>
        </div>
        <br>
    </#list>
</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
</body>
</html>