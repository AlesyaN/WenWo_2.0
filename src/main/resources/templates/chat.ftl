<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <link type="text/css" src="/css/util.css">
    <script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/chat.js"></script>
</head>
<body id="chat-body">
<br>
<br>
<input id="partner" value="${partner}" hidden>
<div id="main" class="container col-sm-6 after-header">
    <br>
    <h1>Chat with ${partner}</h1>

    <form>
        <div class="form-row">
            <input type="text" id="message" class="form-control col-sm-9" placeholder="Your message here...">
            <div class="col-sm-3">
                <button id="send" class="btn btn-outline-dark col-sm-12" type="submit">Send</button>
            </div>
        </div>
    </form>
    <br>
    <div class=" card ">
        <br>
        <strong>Messages</strong>
        <br>
            <table id="conversation" class="table">
                <tbody id="messages">
                <#list messages as message>
                <tr id="${message.id}">
                    <td>
                        <a href="/profile/${message.sender.login}">${message.sender.login}</a>
                    </td>
                    <td>
                        ${message.text}
                    </td>
                    <td>
                        ${message.date}
                    </td>

                        <#if message.sender.login != partner>
                        <td>
                            <button class="btn btn-outline-warning" data-id="${message.id}" onclick="deleteMessage(event)">
                                Delete
                            </button>
                        </td>
                        </#if>

                </tr>
                </#list>
                </tbody>
            </table>
    </div>
</div>

</body>
</html>