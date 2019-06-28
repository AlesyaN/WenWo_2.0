<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <meta charset="utf-8">
</head>
<body>
<br>
<br>
<div class="container col-sm-6 after-header">
    <div class="text-center">
        <h2>
            Your conversations:
        </h2>
    </div>
    <br>

    <input id="currentUserLogin" value="${currentUser.login}" hidden>
    <table id="conversation" class="table table-hover text-center">
        <thead>
        <tr>
            <th class="text-center">Date</th>
            <th class="text-center">Last message</th>
            <th class="text-center">Partner</th>
        </tr>
        </thead>
        <tbody id="chats">
        <#list chats as chat>
                <#if chat.sender.login == currentUser.login>
                    <#assign partnerLogin = chat.receiver.login>
                <#else>
                    <#assign partnerLogin = chat.sender.login>
                </#if>
        <tr id="${partnerLogin}" onclick="openChat(event)">
            <td>
                ${chat.date}
            </td>
            <td>
                ${chat.sender.login}:<br>
                ${chat.text}
            </td>
            <td>
                <a href="/profile/${partnerLogin}">${partnerLogin}</a>
            </td>

        </tr>
        </#list>
        </tbody>
    </table>
</div>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script type="application/javascript" src="/js/chats.js"></script>
</body>
</html>