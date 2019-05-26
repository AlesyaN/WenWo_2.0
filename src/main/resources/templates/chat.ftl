<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/chat.js"></script>
</head>
<body id="chat-body">
    <input id="partner" value="${partner}" hidden>
    <div id="main" class="container">
        <form class="form-style-2" method="get" action="/search">
            <input class="input-field" type="text" id="search" name="search-text">
            <input type="submit" class="button" value="Search">
        </form>
        <a class="button" href="/feed">Feed</a>
        <a class="button" href="/profile">My profile</a>
        <a class="button" href="/chat">Messages</a>
        <br>
        <br>
        <h1>Chat with ${partner}</h1>
        <form class="form-inline">
            <div class="form-group">
                <input type="text" id="message" class="form-control" placeholder="Your message here...">
            </div>
            <button id="send" class="btn btn-default" type="submit">Send</button>
        </form>
        <div class="row">
            <div class="col-md-12">
                <table id="conversation" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Messages</th>
                    </tr>
                    </thead>
                    <tbody id="messages">
                    <#list messages as message>
                        <tr>
                            <td>
                                <a href="/profile/${message.sender.login}">${message.sender.login}</a>
                            </td>
                            <td>
                                ${message.text}
                            </td>
                            <td>
                                ${message.date}
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>