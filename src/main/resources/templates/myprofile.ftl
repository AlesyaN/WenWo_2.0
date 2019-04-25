<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<#if user??>
<div class="form-style-2">
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/questions">You have ${user.unansweredQuestions?size} unanswered questions</a>
    <br>
    <br>
    <div class="form-style-2-heading">
        ${user.fullName}
    </div>
    <br>
    <a href="/editProfile">Edit</a>
    <br>
    <p>
        <b>Followers: </b><div id="followers">${user.followers?size}</div>
    <br>
    <b>Followings: </b> ${user.followings?size}
    </p>
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

    <a href="/logout">Log out</a>
    <br>
    <br>
    <div class="form-style-2-heading">Questions</div>
    <list>
    <#list user.answeredQuestions as question>
        <ul>
            <a href="/profile/${question.sender.id}">${question.sender.login}</a>
            <br>
            <b>${question.text}</b>
            <br>
                    <#if question.answer??>
                        <p>${question.answer}</p>
                    </#if>
            <i>${question.date}</i>
            <i>Likes: ${question.likes?size}</i>
        </ul>
    </#list>
    </list>

</div>

<#else>
<div class="form-style-2-heading">
    No such user
</div>
</#if>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
</body>
</html>