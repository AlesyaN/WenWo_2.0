<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
</head>
<body>
<div class="form-style-2">
    <form class="form-style-2" method="get" action="/search">
        <input class="input-field" type="text" id="search" name="search-text">
        <input type="submit" class="button" value="Search">
    </form>
    <a class="button" href="/feed">Feed</a>
    <a class="button" href="/profile">My profile</a>
    <br><br>
<#if 0 < result?size>
<div class="form-style-2-heading">Searching results:</div>
<br>
    <#list result as user>
        <#if user.photo_path??>
            <img style="height: 50px; width: 50px" src="${user.photo_path}">
        </#if>
        <a href="/profile/${user.id}">${user.login}</a>
        <b>${user.getFullName()}</b>
        <br>
        <br>
    </#list>
<#else>
    <div>No results</div>
</#if>
</div>
</body>
</html>