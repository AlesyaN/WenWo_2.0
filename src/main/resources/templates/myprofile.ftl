<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
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
                    <p><strong>Login: </strong> ${user.login}</p>
                    <p><strong>Email: </strong> ${user.email} </p>
                        <#if user.gender??>
                            <p><strong>Gender: </strong> ${user.gender} </p>
                        </#if>
                        <#if user.city??>
                        <p><strong>City: </strong> ${user.city} </p>
                        </#if>
                        <#if user.dateOfBirth??>
                        <p><strong>Date of birth: </strong> ${user.dateOfBirth} </p>
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
                    <h2><strong>${user.followers?size}</strong></h2>
                    <p>
                        <small>Followers</small>
                    </p>
                    <form action="/editProfile">
                        <button class="btn btn-outline-dark btn-block"><span
                                class="fa fa-pencil"></span> Edit
                        </button>
                    </form>

                </div>
                <div class="col-sm-6 subscriptions" data-users="user.followings">
                    <h2><strong>${user.followings?size}</strong></h2>
                    <p>
                        <small>Following</small>
                    </p>
                    <form action="/logout">
                        <button class="btn btn-outline-dark btn-block"><span class="fa fa-sign-out"></span>
                            Logout
                        </button>
                    </form>

                </div>

            </div>
            <br>
            <div class="col-sm-12">
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
            <p class="lead text-muted"><#if user.albums?has_content>Update your albums regularly and share your life
                with your friends!<#else>You don't have photo albums yet. <br> Create a new album right now!</#if></p>
            <p>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newAlbumModal">Add new
                    Album
                </button>
                <button id="photomapbtn" data-login="${user.login}" class="btn btn-secondary my-2">Show photos map
                </button>
            </p>
            <div id="photo-map" class="map" style="display: none; margin: auto"></div>
            <div class="bd-example">
                <div class="modal fade" id="newAlbumModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="exampleModalLabel">New album</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="albumName" name="name" maxlength="25"
                                           placeholder="enter album name">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal"
                                        onclick="addAlbum(event)">Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row" id="albums">
                <#list user.albums as album>
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <img class="card-img-top" style="object-fit: cover; height: 200px"
                                 <#if album.cover.photopath??>
                                    src="${album.cover.photoPath}"
                                 <#else>
                                    src="/images/album_default.jpg"
                                 </#if>
                                 alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text">${album.name}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-secondary"
                                                onclick="location.href = '/albums/${album.id}'">View
                                        </button>
                                        <button class="btn btn-sm btn-outline-secondary"
                                                onclick="location.href = '/albums/${album.id}'">Edit
                                        </button>
                                    </div>
                                    <small class="text-muted">${album.photosNumber} photos</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</main>

<section class="jumbotron text-center bg-white">
    <div class="container">
        <h1 class="jumbotron-heading">Questions</h1>
        <p class="lead text-muted"><#if user.answeredQuestions?has_content>Here are the questions you have already
            answered. Leave
            comments, like, edit and delete questions as you want.<#else>You haven't answered on any question yet.</#if>
            <br>Don't forget to check your unanswered questions page!</p>

        <form action="/questions">
            <button type="submit" class="btn btn-outline-warning">See my unanswered questions</button>
        </form>
    </div>
</section>

<div class="bg-light">
    <div class="container">
    <#list user.answeredQuestions as question>
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
                    <li class="nav-item">
                        <a class="nav-link" id="edit-tab" data-toggle="tab" href="#edit" role="tab"
                           aria-controls="edit" aria-selected="true">Edit answer</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="questionTabContent">
                <div class="tab-pane fade show active" id="answer" role="tabpanel" aria-labelledby="answer-tab">

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
                        <#assign liked = false>
            <#list question.likes as like>
                <#if like.user.login == user.login>
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
                                <button class="btn btn-outline-dark" onclick="deleteQuestion(event)"
                                        data-questionId="${question.id}">Delete question
                                </button>
                            </div>

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
                            <#if comment.author.login == user.login>
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
                <div class="tab-pane fade" id="edit" role="tabpanel" aria-labelledby="edit-tab">
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Enter new answer"
                                   aria-describedby="basic-addon2" id="answerField${question.id}">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" data-questionId="${question.id}"
                                        onclick="editAnswer(event)" type="button">Edit
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </#list>
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
<script type="application/javascript" src="/js/question-comment.js"></script>
<script type="application/javascript" src="/js/hashtags.js"></script>
<script type="application/javascript" src="/js/user-tags.js"></script>
<script type="application/javascript" src="/js/albums.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=ba0d1ebb-c4a5-4a00-af89-2882eb642996"
        type="text/javascript"></script>
<script type="application/javascript" src="/js/photo-map.js"></script>
</body>
</html>