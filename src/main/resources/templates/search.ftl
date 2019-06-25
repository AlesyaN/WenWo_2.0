<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="/js/popper.min.js"></script>


    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<br>
<br>
<div class="container col-sm-8 after-header">
    <form class="" method="get" action="/search">
        <div class="form-row">
            <input id="search" name="search-text" class="form-control col-sm-9" type="text"
                   placeholder="use #hashtags, @usertags to search">
            <div class="col-sm-3">
                <button type="submit" class="btn btn-outline-warning col-sm-12">Search</button>
            </div>
        </div>
    </form>
    <br></br>
    <div>
        <h3>
            Searching results:
        </h3>
    </div>
    <#if users?? && 0 < users?size>
    <div>
        <h4>Users</h4>
        <hr>
    </div>
    <div class="album py-5">
        <div class="container">
            <div class="row justify-content-center" id="foundUsers">
                <#list users as user>
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <img class="card-img-top" style="object-fit: cover; height: 200px"
                                 <#if user.photo_path??>
                                 src="${user.photo_path}"
                                 <#else>
                                 src="/images/default_user_photo.png"
                                 </#if>
                                 alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text">${user.getFullName()}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-dark"
                                                onclick="location.href = '/profile/${user.login}'">See more
                                        </button>
                                    </div>
                                    <small class="text-muted">${user.login}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
    </#if>




    <#if questions?? && 0 < questions?size>
        <div>
            <h4>Questions</h4>
            <hr>
        </div>
    <div class="container">
    <#list questions as question>
        <br>
        <div id="${question.id}" class="card">
            <div class="card-header">
                <ul class="nav nav-tabs card-header-tabs" id="questionTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="answer-tab" data-toggle="tab" href="#answer" role="tab"
                           aria-controls="answer" aria-selected="true">Answer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="comments-tab" data-toggle="tab" href="#comments" role="tab"
                           aria-controls="comments" aria-selected="true">Comments</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="questionTabContent">
                <div class="tab-pane fade show active" id="answer" role="tabpanel" aria-labelledby="answer-tab">

                    <div class="card-body">
                        <div class="list-group">
                            <i class="text-muted"><#if question.anonymous>
                    anonymous
                            <#else>
                    <a href="/profile/${question.sender.login}">${question.sender.login}</a>
                            </#if> ${question.date}</i>
                        </div>
                        <h5 class="card-title" data-contain-user-tags data-contain-hashtags>${question.text}</h5>
                        <p class="card-text" data-contain-user-tags data-contain-hashtags
                           <#if question.answer??>id="answer${question.id}">
                        ${question.answer}<#else> >no answer</#if></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="list-group">
                                <i class="text-muted"><#if question.anonymous>
                                    anonymous
                                <#else>
                    <a href="/profile/${question.receiver.login}">${question.receiver.login}</a>
                                </#if></i>
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
                                    <span id="likesHeart"
                                          class="fa <#if liked == true> fa-heart <#elseif liked == false> fa-heart-o </#if>"></span>


                                </button>
                            </div>
                         <#else>
                            <div class="btn-group">
                                <form action="/login">
                                    <button type="submit" class="btn btn-outline-danger">
                                        <span id="likes${question.id}">${question.likes?size}</span>
                                        <span id="likesHeart" class="fa fa-heart-o"></span>
                                    </button>
                                </form>
                            </div>
                         </#if>

                        </div>

                    </div>
                </div>
                <div class="tab-pane fade" id="comments" role="tabpanel" aria-labelledby="comments-tab">
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <input id="commentinput${question.id}" type="text" class="form-control"
                                   placeholder="Leave your comment"
                                   aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" data-questionId="${question.id}"
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

    </#if>
</div>

</div>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
</body>
</html>