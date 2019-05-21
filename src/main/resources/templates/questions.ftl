<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        You have <span id="num-of-unanswered-questions">${questions?size}</span> unanswered questions
    </div>
    <a class="button" href="/profile">My profile</a>
    <br>
    <br>
    <#list questions as question>
        <div id="question${question.id}">
            <#if question.anonymous>
                <b>anonymous</b>
            <#else>
                <b><a href="/profile/${question.sender.login}">${question.sender.login}</a></b>
            </#if>
            <br>
            <b data-contain-hashtags data-contain-user-tags>${question.text}</b>
            <br>
            <i>${question.date}</i>
            <br>
            <br>
            <textarea class="textarea-field" id="textarea${question.id}"></textarea><br>
            <button class="button" onclick="answer(event)" data-questionId="${question.id}">Answer</button>
            <button class="button delete" onclick="deleteQuestion(event)" data-questionId="${question.id}">Delete
            </button>
        </div>
        <br>
        <br>
    </#list>
</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/questions.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>

</body>
</html>