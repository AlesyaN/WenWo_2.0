<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<div class="form-style-2">
    <form class="form-style-2" method="get" action="/search">
        <input class="input-field" type="text" id="search" name="search-text">
        <input type="submit" class="button" value="Search">
    </form>
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/profile">My profile</a>
    <a class="button" href="/chat">Messages</a>
    <br>
    <br>
    <table id="conversation" class="table table-striped">
        <thead>
        <tr>
            <th>Partner</th>
            <th>Message</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
            <#list chats as chat>
                <#if chat.sender.id == currentUserId>
                    <#assign partnerLogin = chat.receiver.login>
                <#else>
                    <#assign partnerLogin = chat.sender.login>
                </#if>
            <tr data-login="${partnerLogin}" onclick="openChat(event)">
                <td>
                    <a href="/profile/${partnerLogin}">${partnerLogin}</a>
                </td>
                <td>
                    ${chat.sender.login}<br>
                    ${chat.text}
                </td>
                <td>
                    ${chat.date}
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<script type="application/javascript" src="/js/chats.js"></script>
</body>
</html>