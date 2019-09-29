<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="/css/bootstrap.min.css" type="text/css">
    <link href="/css/util.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" id="bootstrap-css"
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" rel="stylesheet">
    <meta charset="utf-8">
</head>
<style>
    :root {
        --jumbotron-padding-y: 3rem;
    }

    .jumbotron {
        padding-top: var(--jumbotron-padding-y);
        padding-bottom: var(--jumbotron-padding-y);
        margin-bottom: 0;
        background-color: #fff;
    }

    @media (min-width: 768px) {
        .jumbotron {
            padding-top: calc(var(--jumbotron-padding-y) * 2);
            padding-bottom: calc(var(--jumbotron-padding-y) * 2);
        }
    }

    .jumbotron p:last-child {
        margin-bottom: 0;
    }

    .jumbotron-heading {
        font-weight: 300;
    }

    .jumbotron .container {
        max-width: 40rem;
    }

    footer {
        padding-top: 3rem;
        padding-bottom: 3rem;
    }

    footer p {
        margin-bottom: .25rem;
    }

    @font-face {
        font-family: Poppins-Regular;
        src: url('/fonts/poppins/Poppins-Regular.ttf');
    }

    body {
        font-family: Poppins-Regular;
    }

    .profile {
        max-height: 600px;
        max-width: 800px;

        text-align: center;
        margin: auto;
    }

    figcaption.ratings {
        margin-top: 20px;
    }

    figcaption.ratings a {
        color: #f1c40f;
        font-size: 11px;
    }

    figcaption.ratings a:hover {
        color: #f39c12;
        text-decoration: none;
    }

    .divider {
        border-top: 1px solid lightgrey;
        border-bottom: 1px solid lightgray;
    }

    .subscriptions:hover {
        cursor: pointer;

    }

    .emphasis h2 {
        margin-bottom: 0;
    }

    span.tags {
        background: #F1D85D;
        border-radius: 2px;
        color: #f5f5f5;
        font-weight: bold;
        padding: 2px 4px;
    }

    .dropdown-menu {
        background-color: #34495e;
        box-shadow: none;
        -webkit-box-shadow: none;
        width: 250px;
        margin-left: -125px;
        left: 50%;
    }

    .dropdown-menu .divider {
        background: none;
    }

    .dropdown-menu > li > a {
        color: #f5f5f5;
    }

    .dropup .dropdown-menu {
        margin-bottom: 10px;
    }

    .dropup .dropdown-menu:before {
        content: "";
        border-top: 10px solid #34495e;
        border-right: 10px solid transparent;
        border-left: 10px solid transparent;
        position: absolute;
        bottom: -10px;
        left: 50%;
        margin-left: -10px;
        z-index: 10;
    }

    .frame {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
        min-height: 300px;
    }
</style>
<body class="bg-light">
<br>
<#if user??>
<div class="container after-header">
    <div class="row">
        <div class="card card-body profile">
            <div class="col-sm-12 frame">
                <div class="col-sm-6 m-t-15">
                    <h2>${user.fullName}</h2>
                    <br>
                    <p><strong>Login: </strong> <span id="login">${user.login}</span></p>
                    <p id="email"><strong>Email: </strong> ${user.email} </p>
                        <#if user.gender??>
                            <p id="gender"><strong>Gender: </strong> ${user.gender} </p>
                        </#if>
                        <#if user.city??>
                        <p id="city"><strong>City: </strong> ${user.city} </p>
                        </#if>
                        <#if user.dateOfBirth??>
                        <p id="dateOfBirth"><strong>Date of birth: </strong> ${user.dateOfBirth} </p>
                        </#if>
                </div>

                <div class="col-sm-6 text-center ">

                    <img <#if user.photoPath??> src="${user.photoPath}"<#else>
                                                src="/images/default_user_photo.png" </#if> alt="" class="img-fluid"
                                                style="vertical-align: middle; max-height: 300px">

                </div>


            </div>
            <div class="col-sm-12 divider text-center form-inline p-t-20 p-b-20">
                <div class="col-sm-6 subscriptions" data-users="user.followers">
                    <h2 id="followers">${user.followers?size}</h2>
                    <p>
                        <small>Followers</small>
                    </p>
                    <#if currentUserId??>
                    <button onclick="follow()" id="follow-btn" class="btn btn-outline-dark btn-block">
                        <#assign followedByCurrentUser = false>
                        <#list user.followers as follower>
                            <#if follower.id == currentUserId>
                                <#assign followedByCurrentUser = true>
                            </#if>
                        </#list>
                        <#if followedByCurrentUser>
                            <span class="fa fa-user-times"></span> Not follow
                        <#else>
                            <span class="fa fa-user-plus"></span> Follow
                        </#if>
                    </button>
                    <#else>
                    <form action="/login">
                        <button class="btn btn-outline-dark btn-block">
                            <span class="fa fa-user-plus"></span> Follow
                        </button>
                    </form>
                    </#if>
                </div>
                <div class="col-sm-6 subscriptions" data-users="user.followings">
                    <h2>${user.followings?size}</h2>
                    <p>
                        <small>Following</small>
                    </p>
                    <#if currentUserId??>

                        <button onclick="location.href = '/chat?login=${user.login}'"
                                class="btn btn-outline-dark btn-block">
                            <span class="fa fa-comments-o"></span> Chat
                        </button>

                    <#else>
                        <button onclick="location.href = '/login" class="btn btn-outline-dark btn-block">
                            <span class="fa fa-comments-o"></span> Chat
                        </button>

                    </#if>

                </div>

            </div>
            <br>
            <div class=" col-sm-12">
                <form class="m-l-15 m-r-15" method="get" action="/search">
                    <div class="form-row ">
                        <div class="col-sm-9">
                            <input type="text" id="search" name="search-text" class="form-control col-sm-12"
                                   placeholder="Enter your friend's nick">
                        </div>
                        <div class="col-sm-3">
                            <button type="submit" class="btn btn-dark col-sm-12">Search</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

    </div>
</div>
<br>
<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Albums</h1>
            <#if user.albums?has_content>
            <p class="lead text-muted">Here are ${user.login}'s
                photo albums</p>
            <p>
                <button id="photomapbtn" data-login="${user.login}" class="btn btn-secondary my-2">Show photos map
                </button>
            </p>
            <div id="photo-map" class="map" style="display: none; margin: auto; width: 600px; height: 400px"></div>
            <#else>
                <p class="lead text-muted">${user.login} didn't create any photo albums yet</p>
            <p>
            </#if>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center" id="albums">
                <#if user.albums?has_content>
                <#list user.albums as album>
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <img class="card-img-top" style="object-fit: cover; height: 200px"
                                 src="${album.cover.photoPath}" alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text">${album.name}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-secondary"
                                                onclick="location.href = '/albums/${album.id}'">View
                                        </button>
                                    </div>
                                    <small class="text-muted">${album.photosNumber} photos</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
                <#else>
                        <div id="emptyAlbums" style="margin: auto">
                            <h4 class="text-center">${user.login} doesn't have photo albums yet</h4>
                        </div>
                </#if>
            </div>

        </div>
    </div>

</main>

<section class="jumbotron text-center bg-white">
    <div class="container">
        <h1 class="jumbotron-heading">Questions</h1>
        <p class="lead text-muted">Here are the ${user.login}'s already answered questions. Leave
            comments and like questions which you want.</p>
        <p>
            <button type="submit" class="btn btn-outline-warning" data-toggle="modal" data-target="#askModal">Ask
                <strong>${user.login}</strong> something
            </button>
            <button type="submit" class="btn btn-outline-secondary" onclick="openUnansweredQuestions(event)">Show
                unanswered questions from you
            </button>
        </p>
        <div id="unanswered-questions-bar" style="display: none">
            <p style="margin: auto" class="text-center">
                You have <span
                    id="num-of-unanswered-questions"><#if unansweredQuestions??>${unansweredQuestions?size}<#else>
                0</#if></span>
                unanswered questions for ${user.login}
            </p>
            <br>
            <div class="list-group list-group-flush" id="unansweredQuestions">
                <#if unansweredQuestions??>
                        <#list unansweredQuestions as question>
                            <div id="question${question.id}" class="list-group-item flex-column align-items-start">

                                <p class="mb-1" data-contain-user-tags data-contain-hashtags>${question.text}</p>
                                <button class="btn btn-sm btn-dark" onclick="deleteUnansweredQuestion(event)"
                                        data-questionId="${question.id}">Delete
                                </button>
                            </div>
                        </#list>
                </#if>
            </div>
        </div>
    </div>
</section>
<div class="bd-example">
    <div class="modal fade" id="askModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="exampleModalLabel">Ask something</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" class="form-control" id="newQuestion" name="question"
                               placeholder="enter your question for ${user.login}">
                    </div>
                    <label for="anonymous">
                        Ask anonymously
                        <input type="checkbox" id="anonymous">
                    </label>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                            onclick="ask(event)">Save
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="bg-light">
    <div class="container">
        <#if user.answeredQuestions?has_content>
    <#list user.answeredQuestions as question>
        <br>
        <div id="${question.id}" class="card">
            <div class="card-header">
                <ul class="nav nav-tabs card-header-tabs" id="questionTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="answer-tab" data-toggle="tab" href="#answerTab${question.id}" role="tab"
                           aria-controls="answerTab${question.id}" aria-selected="true">Answer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="comments-tab" data-toggle="tab" href="#commentsTab${question.id}" role="tab"
                           aria-controls="commentsTab${question.id}" aria-selected="true">Comments</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="questionTabContent">

                <div class="tab-pane fade show active" id="answerTab${question.id}" role="tabpanel" aria-labelledby="answer-tab">

                    <div class="card-body">
                        <h5 class="card-title" data-contain-user-tags data-contain-hashtags>${question.text}</h5>
                        <p class="card-text" data-contain-user-tags data-contain-hashtags
                           <#if question.answer??>id="answer${question.id}">
                        ${question.answer}<#else> >no answer</#if></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="list-group">
                                <i class="text-muted"><#if question.anonymous>
                    anonymous
                                <#else>
                    <a href="/profile/${question.sender.login}">${question.sender.login}</a>

                                </#if> ${question.date}</i>
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
                <div class="tab-pane fade" id="commentsTab${question.id}" role="tabpanel" aria-labelledby="comments-tab">
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <input id="commentinput${question.id}" type="text" class="form-control"
                                   placeholder="Leave your comment"
                                   aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" data-questionId="${question.id}"
                                        <#if currentUserId??>onclick="addComment(event)"<#else>
                                        onclick="location.href = '/login'"</#if>>Send
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
        <#else>
        <br>
            <div id="emptyQuestions" style="margin: auto">
                <h4 class="text-center">${user.login} hasn't answered on any question yet</h4>
            </div>
        </#if>
    </div>
</div>
<br>


<#else>
<div class="text-center after-header" style="margin: auto">
    <h1>No such user</h1>
</div>
</#if>
<script type="application/javascript" src="/js/profile.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
<script type="application/javascript" src="/js/comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=ba0d1ebb-c4a5-4a00-af89-2882eb642996"
        type="text/javascript"></script>
<script type="application/javascript" src="/js/all-photos-map.js"></script>
</body>
</html>