<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="/js/popper.min.js"></script>


    <script src="/js/bootstrap.min.js" type="text/javascript"></script>

    <link href="/css/util.css" rel="stylesheet" type="text/css">
    <meta charset="utf-8">
</head>
<style>
    @font-face {
        font-family: Poppins-Regular;
        src: url('/fonts/poppins/Poppins-Regular.ttf');
    }

    body {
        font-family: Poppins-Regular;
    }
</style>
<body>
<br>

<div class="container after-header">
    <#list feed as question>
        <br>
        <div id="${question.id}" class="card">
            <div class="card-header">
                <ul class="nav nav-tabs card-header-tabs" id="questionTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="answer-tab" data-toggle="tab" href="#answerTab${question.id}"
                           role="tab"
                           aria-controls="answerTab${question.id}" aria-selected="true">Answer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="comments-tab" data-toggle="tab" href="#commentsTab${question.id}"
                           role="tab"
                           aria-controls="commentsTab${question.id}" aria-selected="true">Comments</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="questionTabContent">
                <div class="tab-pane fade show active" id="answerTab${question.id}" role="tabpanel"
                     aria-labelledby="answer-tab">

                    <div class="card-body">
                        <div class="d-flex justify-content-end">
                            <div class="list-group">
                                <i class="text-muted"><#if question.anonymous>
                                    anonymous
                                <#else>
                                <a href="/profile/${question.sender.login}">${question.sender.login}</a>

                                </#if></i>
                            </div>
                            &nbsp;
                            <div class="list-group">
                                <i> ${question.date}</i>
                            </div>
                        </div>

                        <h5 class="card-title" data-contain-user-tags data-contain-hashtags>${question.text}</h5>
                        <p class="card-text" data-contain-user-tags data-contain-hashtags
                           <#if question.answer??>id="answer${question.id}">
                        ${question.answer}<#else> >no answer</#if></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="list-group">
                                <i class="text-muted">
                                    to <a href="/profile/${question.receiver.login}">${question.receiver.login}</a>
                                </i>
                            </div>
                         <#if currentUserId??>
                             <#assign liked = false>
                             <#list question.likes as like>
                                 <#if like.user.id == currentUserId>
                                     <#assign liked = true>
                                 </#if>
                             </#list>
                            <div class="btn-group">
                                <button onclick="like(event)" class="btn btn-outline-danger"
                                        data-questionid="${question.id}">
                                    <span id="likes${question.id}">${question.likes?size}</span>
                                    <span id="likesHeart${question.id}"
                                          class="fa <#if liked == true> fa-heart <#elseif liked == false> fa-heart-o </#if>"></span>
                                </button>
                            </div>
                         <#else>
                            <div class="btn-group">
                                <form action="/login">
                                    <button type="submit" class="btn btn-outline-danger">
                                        <span id="likes${question.id}">${question.likes?size}</span>
                                        <span id="likesHeart${question.id}" class="fa fa-heart-o"></span>
                                    </button>
                                </form>
                            </div>
                         </#if>

                        </div>

                    </div>
                </div>
                <div class="tab-pane fade" id="commentsTab${question.id}" role="tabpanel"
                     aria-labelledby="comments-tab">
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <input id="commentinput${question.id}" type="text" class="form-control"
                                   placeholder="Leave your comment"
                                   aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button"
                                        data-questionId="${question.id}"
                                        onclick="addComment(event)">Send
                                </button>
                            </div>
                        </div>
                        <div class="list-group list-group-flush" id="comments${question.id}">
                        <#list question.comments as comment>
                            <div id="comment${comment.id}" class="list-group-item flex-column align-items-start">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1"><a
                                            href="/profile/${comment.author.login}">${comment.author.login}</a></h5>
                                    <small>${comment.date}</small>
                                </div>
                                <p class="mb-1" data-contain-user-tags data-contain-hashtags>${comment.text}</p>
                            <#if currentUserId?? && comment.author.id == currentUserId>
                            <div class="text-right">
                                <button class="btn btn-dark" data-comment-id="${comment.id}"
                                        onclick="deleteComment(event)">Delete
                                </button>
                            </div>

                            </#if>
                            </div>
                        </#list>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </#list>
    <br>
</div>

<script type="application/javascript" src="/js/profile.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=ba0d1ebb-c4a5-4a00-af89-2882eb642996"
        type="text/javascript"></script>
<script type="application/javascript" src="/js/photo-map.js"></script>

<script type="application/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
</body>
</html>