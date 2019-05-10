<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<div class="form-style-2">
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/profile">My profile</a>
    <br>
    <br>
<#if user??>
    <div class="form-style-2-heading">
        ${user.fullName}
    </div>
    <button class="button" onclick="follow()" id="follow-btn">
            <#assign followedByCurrentUser = false>
            <#list user.followers as follower>
                <#if follower.id == currentUserId>
                    <#assign followedByCurrentUser = true>
                </#if>
            </#list>
            <#if followedByCurrentUser>
                Not follow
            <#else>
                Follow
            </#if>
    </button>
    <p>
        <b>Followers: </b>
        <span id="followers">${user.followers?size}</span>
        <br>
        <b>Followings: </b> ${user.followings?size}
    </p>

    <form id="ask-form" onsubmit="ask(event)" class="form-style-2">
        <div class="form-style-2-heading">Ask ${user.login} anything...</div>
        <textarea id="textarea" class="textarea-field" name="question"></textarea>
        <br>
        <input type="submit" value="Ask">
        <br>
        <div class="pressed-button">
            <div id="unanswered-questions-bar" onclick="openUnansweredQuestions(event)"
             <#if unansweredQuestions?? && unansweredQuestions?size <= 0>
             style="display: none"
             </#if>
                 class="button">
                You have <span
                    id="num-of-unanswered-questions"><#if unansweredQuestions??>${unansweredQuestions?size}</#if></span>
                unanswered questions for ${user.login}
            </div>
            <br>
            <div id="unansweredQuestions" style="display: none">
                <#list unansweredQuestions as question>
                    <div id="question${question.id}">
                        ${question.text}
                        <button class="button delete" onclick="deleteUnansweredQuestion(event)"
                                data-questionId="${question.id}">Delete
                        </button>
                    </div>
                    <br>
                </#list>
            </div>

        </div>

    </form>

    <br>
    <#if user.gender??>
        <label for="gender">Gender:
            <div id="gender">${user.gender}</div>
        </label>
        <br>
    </#if>

    <label for="login">Login:
        <div id="login">${user.login}</div>
    </label>
    <br>

    <label for="email">Email:
        <div id="email">${user.email}</div>
    </label>
    <br>

    <#if user.city??>
        <label for="city">City:
            <div id="city">${user.city}</div>
        </label>
        <br>
    </#if>

    <#if user.dateOfBirth??>
        <label for="dateOfBirth">Date of birth:
            <div id="dateOfBirth">${user.dateOfBirth}</div>
        </label>
        <br>
    </#if>

    <div class="form-style-2-heading">Questions</div>
    <list>
    <#list user.answeredQuestions as question>
        <a href="/profile/${question.sender.id}">${question.sender.login}</a>
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
                        id="${question.id}" onclick="like(event)">&hearts;
        </button>
        <i id="likes${question.id}">${question.likes?size}</i>
        <br>
    </#list>
    </list>
<#else>
    No such user
</div>
</#if>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
<script type="application/javascript" src="/js/like.js"></script>
</body>
</html>