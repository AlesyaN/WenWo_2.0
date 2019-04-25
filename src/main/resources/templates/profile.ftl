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
            <#if user.isFollowedByCurrentUser()>
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
        <div id="unanswered-questions-bar"
             <#if unansweredQuestions?? && unansweredQuestions?size <= 0>
             style="display: none"
             </#if>
             class="button">
            You have <span id="num-of-unanswered-questions"><#if unansweredQuestions??>${unansweredQuestions?size}</#if></span> unanswered questions for ${user.login}
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
                    <#if question.answer??>
                        <p>${question.answer}</p>
                    </#if>
        <i>${question.date}</i>
        <i>Likes: ${question.likes?size}</i>

    </#list>
    </list>
<#else>
    No such user
</div>
</#if>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
</body>
</html>