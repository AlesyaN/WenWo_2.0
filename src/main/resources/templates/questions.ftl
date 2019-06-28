<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<br>
<div class="container col-sm-8 after-header">
    <h4 class="text-center">You have <span id="num-of-unanswered-questions">${questions?size}</span> unanswered questions</h4>
    <br>
    <div class="list-group list-group-flush">
        <#list questions as question>
            <div id="question${question.id}" class="list-group-item flex-column align-items-start">

                <div class="d-flex w-100 justify-content-between">
                <#if question.anonymous>
                    <h5 class="mb-1">anonymous</h5>
                <#else>
                <h5 class="mb-1"><a href="/profile/${question.sender.login}">${question.sender.login}</a></h5>
                </#if>
                    <small>${question.date}</small>
                </div>
                <br>
                <h5 class="mb-1" data-contain-user-tags data-contain-hashtags>${question.text}</h5>
                <textarea id="textarea${question.id}" class="form-control" placeholder="enter your answer"></textarea>
                <br>
                <div class="text-right">
                    <button class="btn btn-outline-dark" onclick="answer(event)" data-questionId="${question.id}">Answer</button>
                    <button class="btn btn-dark" onclick="deleteQuestion(event)" data-questionId="${question.id}">Delete
                    </button>
                </div>
            </div>
            <br>

        </#list>

    </div>
</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/questions.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>

</body>
</html>