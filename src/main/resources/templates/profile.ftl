<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <#--<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>-->
    <meta charset="utf-8">
</head>
<body>
<#if user??>
<div class="form-style-2">

    <div class="form-style-2-heading">
        ${user.fullName}
    </div>

    <p>
        <b>Followers: </b> ${user.followers?size}
        <br>
        <b>Followings: </b> ${user.followings?size}
    </p>


    <#if user.gender??>
        <label for="login">Gender:
            <div id="login">${user.gender}</div>
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
        <label for="login">City:
            <div id="login">${user.city}</div>
        </label>
        <br>
    </#if>

    <#if user.dateOfBirth??>
        <label for="login">Date of birth:
            <div id="login">${user.dateOfBirth}</div>
        </label>
        <br>
    </#if>

    <a href="/logout">Log out</a>

    <div class="form-style-2-heading">Questions</div>
    <list>
    <#list user.answeredQuestions as question>
            <ul>
                    <b>${question.sender.login}</b>
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

</#if>
</body>
</html>